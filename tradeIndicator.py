import changePredictors

class TradeIndicator:
	def __init__(self):
		self.size = 0
		self.experts = [changePredictors.randomForestChangePredictor(),
						changePredictors.neuralNetworkChangePredictor()]

	def indicate(self, arr):
		self.size += 1
		return self.experts[0].predict(arr)
		#return self.experts[1].predict([args])

	def readyToTrade(self):
		if self.size > 100:
			return True
		else:
			return False

if __name__ == '__main__':
	print "tradeIndicator Test"
	test = TradeIndicator()
	for x in range(10):
		print "Test: " + str(test.indicate([x*2, x]))
		#test.indicate([x*2, x, x+2])