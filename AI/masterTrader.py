from pyalgotrade import strategy
from pyalgotrade.barfeed import yahoofeed
from pyalgotrade.tools import yahoofinance
from pyalgotrade.technical import ma

import dataIO, main, changePredictors

prevPrice = 0

class masterTrader(strategy.BacktestingStrategy):
	def __init__(self, feed, instrument):
		strategy.BacktestingStrategy.__init__(self, feed, 1000)
		self.__position = None
		self.__instrument = instrument
		# We'll use adjusted close values instead of regular close values.
		self.setUseAdjustedValues(True)
		self.__sma = ma.SMA(feed[instrument].getPriceDataSeries(), smaPeriod)
		self.datamanager = dataIO.dataManager()
		self.predictor = changePredictors.randomForestChangePredictor()

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

	def onBars(self, bars):
		#data = bars.getBar()
		#self.datamanager.append(data.getDateTime(), self.getBroker().getEquity())
		#print self.predictor.predict([data.getVolume(), data.getOpen(), data.getHigh(), data.getAdjClose()])
		# Wait for enough bars to be available to calculate a SMA.
		if self.__sma[-1] is None:
			return

		bar = bars[self.__instrument]
		# If a position was not opened, check if we should enter a long position.
		if self.__position is None:
			if bar.getPrice() > self.__sma[-1]:
				# Enter a buy market order for 10 shares. The order is good till canceled.
				self.__position = self.enterLong(self.__instrument, 10, True)
		# Check if we have to exit the position.
		elif bar.getPrice() < self.__sma[-1] and not self.__position.exitActive():
			self.__position.exitMarket()


def run_strategy():
	smaPeriod = 30
	# Load the yahoo feed from the CSV file
	securities = ["orcl"]
	feed = yahoofinance.build_feed(securities, 2006, 2012, "stockdata")

	# Evaluate the strategy with the feed.
	masterTrader = MasterTrader(feed, securities)
	masterTrader.run()
	print "Final portfolio value: $%.2f" % masterTrader.getBroker().getEquity()
	masterTrader.datamanager.close()