import sys, os
from pyalgotrade.tools import yahoofinance

DATAFILENAME = "stockData.csv"


def initialize():
	print "~~ Initializing ~~"
	if  not os.path.isfile(DATAFILENAME):
		print "Fetching Data"
		yahoofinance.download_daily_bars('orcl', 2000, DATAFILENAME)
	print "~~ Done Initializing ~~"
	#tickers = getSP500.getTickers()
	#for ticker in tickers:
#		temp = Share(ticker)
#		print ticker + ": " + temp.get_price()

def clean():
	print "~~ Cleaning Data ~~"
	if os.path.isfile(DATAFILENAME):
		os.remove(DATAFILENAME)
	print "~~ Done Cleaning ~~"



if __name__ == '__main__':
	if "test" in sys.argv:
		print getTimeStamp()

	if "clean" in sys.argv:
		clean()

	if "init" in sys.argv:
		initialize()

	if "run" in sys.argv:
		import pyalgotest