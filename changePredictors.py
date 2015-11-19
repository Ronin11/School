from sklearn import linear_model
from pybrain.supervised.trainers import BackpropTrainer
from pybrain.datasets import SupervisedDataSet
from pybrain.tools.shortcuts import buildNetwork
from sklearn.ensemble import RandomForestRegressor
from dataStructure import dataStructure

class changePredictor:
	def __init__(self):
		self.predictor = "predictor"

class neuralNetworkChangePredictor(changePredictor):
	def __init__(self, *args):
		self.net = buildNetwork(len(args[0]),3,1)
		self.data = dataStructure(args)

	def predict(self, currentData):
		dataset = SupervisedDataSet(len(list(currentData[0])), 1)
		for point in self.data.toArray():
			dataset.addSample(point)
		trainer = BackpropTrainer(self.net, dataset)
		prediction = self.net.activate(list(currentData[0]))
		
		self.data.append(list(currentData[0]))
		return prediction

class randomForestChangePredictor(changePredictor):
	def __init__(self, *args):
		self.predictor = "predictor"
		self.data = dataStructure(args)

	def predict(self, currentData):
		trainAll = []
			   
		for index in range(0, self.data.size()):
			trainAll.append(index)

		clf = RandomForestRegressor(n_estimators=10)
		clf.fit (self.data.toArray(), trainAll)
		prediction = clf.predict(list(currentData[0]))
		
		self.data.append(list(currentData[0]))
		return prediction