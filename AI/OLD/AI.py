from pybrain.tools.shortcuts import buildNetwork
from sklearn.ensemble import RandomForestRegressor

class AI:
	def __init__(self):
		self.randomForest = RandomForestRegressor(n_estimators=10)
		self.stockData = []
		self.iterator = []
		self.net = buildNetwork(2, 3, 1)
		#self.net.activate([2,1])

	def predict(self):
		print self.stockData
		self.randomForest.fit(self.stockData, self.iterator)
		return self.randomForest.predict([self.stockData[len(self.stockData)-1][0],
			self.stockData[len(self.stockData)-1][1]])

	def append(self, price, vol):
		self.stockData.append([price, vol])
		self.iterator.append(len(self.iterator))
		self.net.activate([price, vol])

	def neuralPredict(self):
		return self.net.activate(self.stockData[len(self.stockData)-1])