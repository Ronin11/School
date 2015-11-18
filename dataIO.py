import calendar, time, os

def getTimeStamp(timestamp):
		return str(int(time.mktime(time.strptime(str(timestamp), "%Y-%m-%d %H:%M:%S"))));

class dataManager:
	def __init__(self):
		self.file = open('data/data.json', 'w')
		self.file.write('[\n')

	def append(self, timestamp, value):
		self.file.write('[' + getTimeStamp(timestamp) + ',' + str(value) + '],\n')

	def close(self):
		#Remove last placed comma
		self.file.seek(-2, os.SEEK_END)
		self.file.truncate()

		self.file.write('\n]')
		self.file.close()

	
