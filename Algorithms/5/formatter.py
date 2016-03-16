import os, re

#returns a list of characters from the specified file.
def format(filename):
	read = open(filename, 'r')
	data = ""
	for line in read:
		data += re.sub("(\d+)|(ORIGIN)|\s|\n", "", line).upper()

	chars = []
	for line in data:
		chars.extend(line)
	return chars