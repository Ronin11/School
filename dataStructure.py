
class dataStructure:
	def __init__(self, *arg):
		self.data = []
		for x in range(0, len(arg)):
			self.data.append(arg[x])

	def append(self, newVal):
		self.data.append(newVal)

	def toArray(self):
		return self.data