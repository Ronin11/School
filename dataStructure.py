
class dataStructure:
	def __init__(self, *args):
		self.data = []
		temp = []
		for x in range(0, len(args[0][0])):
			temp.append(args[0][0][x])
		self.data.append(temp)

	def append(self, newVal):
		self.data.append(newVal)

	def toArray(self):
		return self.data

	def size(self):
		return len(self.data)