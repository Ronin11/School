import numpy as np
from numpy import array

A, C, G, T = 0, 1, 2, 3
int_to_char = {0:'A', 1:'C', 2:'G', 3:'T'}


class AlignmentFinder(object):
	def __init__(self, seq1, seq2, score):
		self.seq1 = seq1
		self.seq2 = seq2
		self.indel = -5 
		self.scoring = score
		self.D = None

	def find_gobal_alignment(self):
		self.D = np.zeros((self.seq1.size+1, self.seq2.size+1), dtype=np.int16)
		self._compute_array()
		print self.D
		return self._traceback()

	def _compute_array(self):
		for i in xrange(self.seq1.size+1):
			self.D[i,0] = i*self.indel
		for j in xrange(self.seq2.size+1):
			self.D[0,j] = j*self.indel
		for i in xrange(1, self.seq1.size+1):
			for j in xrange(1, self.seq2.size+1):
				self.D[i,j] = max(  self.D[i-1, j-1] + self._get_score(i, j),
									self.D[i-1, j] + self.indel,
									self.D[i, j-1] + self.indel)
	def _get_score(self, i, j):
		return self.scoring[self.seq1[i-1], self.seq2[j-1]]
	
	def _get_aligned_pair(self, i, j):
		n1 = int_to_char[self.seq1[i-1]] if i>0 else '_'
		n2 = int_to_char[self.seq2[j-1]] if j>0 else '_'
		return (n1, n2)

	def _traceback(self):
		alignment= []
		i = self.seq1.size
		j = self.seq2.size
		while i >0 and j>0:
			if self.D[i-1, j-1] + self._get_score(i, j) == self.D[i,j]:
				alignment.append(self._get_aligned_pair(i, j))
				i -= 1
				j -= 1
			elif self.D[i-1, j] + self.indel == self.D[i,j]:
				alignment.append(self._get_aligned_pair(i, 0))
				i -= 1
			else:
				alignment.append(self._get_aligned_pair(0, j))
				j -= 1
		while i > 0:
			alignment.append(self._get_aligned_pair(i, 0))
			i -= 1
		while j > 0:
			alignment.append(self._get_aligned_pair(0, j))
			j -= 1
		alignment.reverse()
		return alignment  

def print_sequences(pairs):
	top_seq = []
	bottom_seq = []
	for (b, t) in pairs:
		bottom_seq.append(b)
		top_seq.append(t)
	for i in range(0, len(top_seq)):
		print top_seq[i] + ' ' + bottom_seq[i]


if __name__ == "__main__":
	s1 = array([G, T, A, C, A, G, T, A], dtype=np.int16)
	s2 = array([G, G, T, A, C, G, T], dtype=np.int16)
	aligner = AlignmentFinder(s1, s2)
	pairs = aligner.find_gobal_alignment()
	print_sequences(pairs)