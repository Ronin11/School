from yahoo_finance import Share
import getSP500, AI
import sys, h5py, os, numpy as np

DATAFILENAME = "stockData.h5"

def test():
	ai = AI.AI()
	for i in range(1, 10):
		ai.append(i,i*10)
	print ai.neuralPredict()

def run():
	data = h5py.File(DATAFILENAME, 'r')
	for level1 in data:
		for level2 in data[level1]:
			#print data[level1]['axis2']
			for level3 in data[level1][level2]:
				print level3
	#temp = data['financials']
	#print temp['block0_items'].shape

def initialize():
	print "~~ Initializing ~~"
	if  not os.path.isfile(DATAFILENAME):
		print "Fetching Data"
		getSP500.get_snp500(DATAFILENAME)
	
	#tickers = getSP500.getTickers()
	#for ticker in tickers:
#		temp = Share(ticker)
#		print ticker + ": " + temp.get_price()

def clean():
	print "~~ Cleaning Data ~~"
	if os.path.isfile(DATAFILENAME):
		os.remove(DATAFILENAME)


if __name__ == '__main__':
	if "test" in sys.argv:
		test()

	if "clean" in sys.argv:
		clean()

	if "init" in sys.argv:
		initialize()

	if "run" in sys.argv:
		run()