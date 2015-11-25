from sklearn import linear_model
from pybrain.supervised.trainers import BackpropTrainer
from pybrain.datasets import SupervisedDataSet
from pybrain.tools.shortcuts import buildNetwork
from sklearn.ensemble import RandomForestRegressor
from dataStructure import dataStructure

class changePredictor:
	def __init__(self):
		self.predictor = "predictor"
		self.data = dataStructure()

class neuralNetworkChangePredictor(changePredictor):
	def __init__(self):
		self.net = buildNetwork(2,3,1)
		self.data = dataStructure()

	def predict(self, currentData):
		dataset = SupervisedDataSet(len(list(currentData[0])), 1)
		for point in self.data.toArray():
			dataset.addSample(point)
		trainer = BackpropTrainer(self.net, dataset)
		prediction = self.net.activate(list(currentData[0]))
		
		self.data.append(list(currentData[0]))
		return prediction

class randomForestChangePredictor(changePredictor):
	def __init__(self):
		#super().__init__(args)
		self.predictor = "predictor"
		self.data = dataStructure()

	def predict(self, currentData):

		if not self.data.toArray():
			self.data.append(currentData)
			return
		trainAll = []
			   
		for index in range(0, self.data.size()):
			trainAll.append(index)

		clf = RandomForestRegressor(n_estimators=10)
		clf.fit (self.data.toArray(), trainAll)
		prediction = clf.predict(currentData)
		
		self.data.append(currentData)
		print "Prediction: " + str(prediction) + "Previous: " + str(currentData[0])
		if prediction != 0: 
			prediction = (currentData[0]/prediction)-1
		return prediction