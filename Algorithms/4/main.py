import dp
import recursive

dpResults = []
recResults = []

file = open('list.txt', 'r')
for line in file:
		line = line.replace(",", "->")
		line = line.replace("\n", "")
		line = line.split("->")
		for j in range(0, len(line)-1):
			str1 = line[0]
			str2 = line[j+1]
			dpResults.append([dp.editDistance(str1, str2, len(str1), len(str2)), str1, str2])
			recResults.append([dp.editDistance(str1, str2, len(str1), len(str2)), str1, str2])
dpResults = sorted(dpResults,key=lambda l:l[0], reverse=True)
recResults = sorted(recResults,key=lambda l:l[0], reverse=True)


print "Lists equal each other: " + str(dpResults==recResults)

print "\nPairs with an edit distance of 5 or more: "
for item in dpResults:
	if(item[0] > 4):
		print item


table = [0] * (int(dpResults[0][0])+1)
for item in dpResults:
	table[item[0]] += 1

print "\nTable: "
for i in range(1, len(table)):
	print str(i) + ", " + str(table[i])

