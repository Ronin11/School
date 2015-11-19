from sklearn import linear_model
from sklearn.ensemble import RandomForestRegressor
from dataStructure import dataStructure

class changePredictor:
	def __init__(self):
		self.predictor = "predictor"



class randomForestChangePredictor(changePredictor):
	def __init__(self, *arg):
		self.predictor = "predictor"
		self.data = dataStructure()

	def predict(self, currentData):
		trainAll = []
			   
		for index in range(1, self.data.size):
			trainAll.append(self.data[index]) #
		clf = RandomForestRegressor(n_estimators=50)
		clf.fit (data, trainAll)
		prediction = clf.predict(currentData)
		data.append(currentData)
		return prediction