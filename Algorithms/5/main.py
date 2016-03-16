import os, re, sys
import numpy as np
from numpy import array

import formatter, naiveAlgo, Hirschberg

#Get all of the files in the data directory

path = '/Users/nate.ashby/School/Algorithms/5/Data/'
output = '/Users/nate.ashby/School/Algorithms/5/out/'
#for filename in os.listdir(path):
#	print formatter.format(path + filename)
int_to_char = {0:'A', 1:'C', 2:'G', 3:'T'}
char_to_int = {'A':0, 'C':1, 'G':2, 'T':3, '_':4}

scoring = array([[5,-1,-2,-1,-3],
				[-1,5,-3,-2,-4],
				[-2,-3,5,-2,-2],
				[-1,-2,-2,5,-1],
				[-3,-4,-2,-1,0]])

def list_to_pairs(list1, list2):
	val = []
	for i in range(0, len(list1)):
		val.append((list1[i],list2[i]))
	return val

def test(files):
	print "Testing the following sequences scores: "
	sequences = []	
	for file in files:
		print file
		sequences.append(formatter.format(path + file))

	for seq in sequences:
		for n in range(0, len(seq)):
			seq[n] = char_to_int[seq[n]]

	for i in range(0, len(sequences)):
		for j in range(i+1, len(sequences)):
			s1 = array(sequences[i], dtype=np.int16)
			s2 = array(sequences[j], dtype=np.int16)
			aligner = naiveAlgo.AlignmentFinder(s1, s2, scoring)
			pairs = aligner.find_gobal_alignment()
			print_sequences(pairs, files[i] + ' & ' + files[j])

def conquer(files):
	print "Comparing the following sequences: "
	sequences = []	
	for file in files:
		print file
		sequences.append(formatter.format(path + file))


	for i in range(0, len(sequences)):
		for j in range(i+1, len(sequences)):
			for i, (x, y) in enumerate(zip([''.join(sequences[i])], [''.join(sequences[j])])):
				row, column, middle = Hirschberg.Hirschberge(x, y)
				print_sequences(list_to_pairs(row, column), files[i] + ' & ' + files[j])

def compare(files):
	print "Comparing the following sequences: "
	sequences = []	
	for file in files:
		print file
		sequences.append(formatter.format(path + file))

	for seq in sequences:
		for n in range(0, len(seq)):
			seq[n] = char_to_int[seq[n]]

	for i in range(0, len(sequences)):
		for j in range(i+1, len(sequences)):
			s1 = array(sequences[i], dtype=np.int16)
			s2 = array(sequences[j], dtype=np.int16)
			aligner = naiveAlgo.AlignmentFinder(s1, s2, scoring)
			pairs = aligner.find_gobal_alignment()
			print_sequences(pairs)

def print_sequences(pairs, filenames):
	top_seq = []
	bottom_seq = []
	for (b, t) in pairs:
		bottom_seq.append(b)
		top_seq.append(t)
	f = open(output + filenames + ' Comparison', 'w')
	for i in range(0, len(top_seq)):
		 f.write(str(top_seq[i]) + ' ' + str(bottom_seq[i]) + '\n')
	f.write('Score: ' + str(score_sequences(pairs)))
	f.close()

def score_sequences(pairs):
	top_seq = []
	bottom_seq = []
	for (b, t) in pairs:
		bottom_seq.append(b)
		top_seq.append(t)
	score = 0
	for i in range(0, len(top_seq)):
		 score += scoring[char_to_int[top_seq[i]], char_to_int[bottom_seq[i]]]
	return score

def score_single_sequence(file):

	seq = formatter.format(path + file)
	f = open(output + file + ' Total Score', 'w')
	f.write('Total Score: ' + str(score_sequences(list_to_pairs(seq, seq))))
	f.close()




if __name__ == "__main__":
	#sys.stdout = open('output', 'w')

	#for filename in os.listdir(path):
	#	if ".txt" in filename:
	#		score_single_sequence(filename)

	#print "Test"
	#conquer(["testA","testB"])
	

	
	conquer(["Human.txt","Neandertal.txt"])
	
	#diversity = []
	#for filename in os.listdir(path):
	#	if "(" in filename:
	#		diversity.append(filename)
	#conquer(diversity)

	#conquer(["ProtoHuman.txt","Baboon.txt","Bonobo.txt","Chimpanzee1.txt","Chimpanzee2.txt","Gorilla.txt","WesternLowlandGorilla.txt"])
	