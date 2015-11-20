import calendar, time, os

def getTimeStamp(timestamp):
		return str(int(time.mktime(time.strptime(str(timestamp), "%Y-%m-%d %H:%M:%S")))*1000);

class dataManager:
	def __init__(self):
		self.file = open('data/data.json', 'w')
		self.file.write('[\n')

	def append(self, timestamp, value):
		self.file.write('[' + getTimeStamp(timestamp) + ',' + str(round(value, 2)) + '],\n')

	def close(self):
		#Remove last placed comma
		self.file.seek(-3, os.SEEK_END)
		self.file.truncate()

		self.file.write('\n]')
		self.file.close()

	
