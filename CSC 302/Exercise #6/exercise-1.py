"""The is an implementation of the Exercise 6"""
from itertools import combinations

# Pythonic way of finding a power set through combinations
s = {'a', 'b', 'c', 'd'}
for i in range(len(s)):
    for element in combinations(s, i):
        print(element)

