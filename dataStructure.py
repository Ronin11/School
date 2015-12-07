from sklearn import preprocessing

class dataStructure:
	def __init__(self):
		self.data = []

	def append(self, newVal):
		self.data.append(newVal)

	def toArray(self):
		return self.data

	def size(self):
		return len(self.data)

	def normalize(self):
		return preprocessing.scale(self.data)