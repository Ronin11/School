import sys, os, pyalgotest, masterTrader, test
from pyalgotrade.tools import yahoofinance

def initialize():
	print "~~ Initializing ~~"
	print "~~ Done Initializing ~~"

def clean():
	print "~~ Cleaning Data ~~"
	loc = "./stockdata/"
	filelist = [ f for f in os.listdir(loc) if f.endswith(".csv") ]
	for f in filelist:
		os.remove(loc + f)
	print "~~ Done Cleaning ~~"

if __name__ == '__main__':

	if "clean" in sys.argv:
		clean()

	if "init" in sys.argv:
		initialize()

	if "test" in sys.argv:
		test.run_strategy()

	if "run" in sys.argv:
		masterTrader.run_strategy()