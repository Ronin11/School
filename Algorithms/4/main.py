import dp
import recursive

str1 = "sunday"
str2 = "saturday"
print recursive.editDistance(str1, str2, len(str1), len(str2))

print dp.editDistance(str1, str2, len(str1), len(str2))

results = [[],[]]

file = open('list.txt', 'r')
for line in file:
		line = line.replace(",", "->")
		line = line.replace("\n", "")
		line = line.split("->")
		print line
		for j in range(0, len(line)-1):
			str1 = line[0]
			str2 = line[j+1]
			results.append(dp.editDistance(str1, str2, len(str1), len(str2)))
