import changePredictors

class tradeIndicator:
	def __init__(self, *args):
		self.experts = [changePredictors.randomForestChangePredictor(args),
						changePredictors.neuralNetworkChangePredictor(args)]

	def indicate(self, *args):
		return self.experts[0].predict([args])
		#return self.experts[1].predict([args])

if __name__ == '__main__':
	test = tradeIndicator(0)
	for x in range(0, 10):
		print "Should be: " + str(x+1) + ", is: " + str(test.indicate(x))