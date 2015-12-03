from pyalgotrade import strategy
from pyalgotrade.barfeed import yahoofeed
from pyalgotrade.tools import yahoofinance
from pyalgotrade.technical import ma

import dataIO, main, changePredictors, tradeIndicator

#prevPrice = 0
tradingPositions = 0


class MasterTrader(strategy.BacktestingStrategy):
	def __init__(self, feed, instrument):
		strategy.BacktestingStrategy.__init__(self, feed, 1000)
		self.__position = None
		self.__instrument = instrument
		# We'll use adjusted close values instead of regular close values.
		self.setUseAdjustedValues(True)
		self.datamanager = dataIO.dataManager()
		self.indicator = tradeIndicator.TradeIndicator()
		self.highValue = 0

	def onEnterOk(self, position):
		execInfo = position.getEntryOrder().getExecutionInfo()
		self.info("BUY at $%.2f" % (execInfo.getPrice()))

	def onEnterCanceled(self, position):
		self.__position = None

	def onExitOk(self, position):
		execInfo = position.getExitOrder().getExecutionInfo()
		self.info("SELL at $%.2f" % (execInfo.getPrice()))
		self.__position = None

	def onExitCanceled(self, position):
		# If the exit was canceled, re-submit it.
		self.__position.exitMarket()

	def Buy(self, stock, change, price):
		# Check if we have to exit the position.
		#if change < 0 and not self.__position.exitActive():
		#if not self.__position.exitActive():
		#	self.Sell(stock, change)
		
		amount = int(self.getBroker().getCash()/price)-1
		if(change > 0):
			self.__position = self.enterLong(stock, amount, True)
		else:
			self.__position = self.enterShort(stock, amount, True)

	def Sell(self, stock, change):
		self.__position.exitMarket()


	def onBars(self, bars):
		temp = bars.getBar(self.__instrument)
		data = [temp.getAdjClose(), temp.getClose(), temp.getHigh(), temp.getVolume()]
		
		self.datamanager.append(bars.getDateTime(), self.getBroker().getEquity())

		price = temp.getAdjClose()
		change = self.indicator.indicate(data)
		if change is None:
			return

		if self.highValue < self.getBroker().getEquity():
			self.highValue = self.getBroker().getEquity()

		bar = bars[self.__instrument]
		# If a position was not opened, check if we should enter a long position.
		if self.__position is None:
			#if change > 0:
				# Enter a buy market order for 10 shares. The order is good till canceled.
			self.Buy(self.__instrument, change, price)

		elif not self.__position.exitActive():
			self.Sell(self.__instrument, change)


def run_strategy():
	# Load the yahoo feed from the CSV file
	securities = ["app"]
	feed = yahoofinance.build_feed(securities, 2006, 2014, "stockdata")

	# Evaluate the strategy with the feed.
	masterTrader = MasterTrader(feed, securities[0])
	masterTrader.run()
	print "Final portfolio value: $%.2f" % masterTrader.getBroker().getEquity()
	masterTrader.datamanager.close()
	print "Highest portfolio value: $%.2f" % masterTrader.highValue