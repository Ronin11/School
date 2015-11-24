import changePredictors

class TradeIndicator:
	def __init__(self, *args):
		self.size = 0
		self.experts = [changePredictors.randomForestChangePredictor(args),
						changePredictors.neuralNetworkChangePredictor(args)]

	def indicate(self, *args):
		self.size += 1
		return self.experts[0].predict([args])
		#return self.experts[1].predict([args])

	def readyToTrade(self):
		if self.size > 100:
			return True
		else:
			return False

if __name__ == '__main__':
	print "tradeIndicator Test"
	test = TradeIndicator(200)
	for x in reversed(range(100)):
		print "Test: " + str(test.indicate(x*2))