"""Part 1 of the Exercise 2"""
A = {10, 12, 6, 8, 15}
B = {4, 12, 6, 9, 11}

# Intersection: an operation in which only similar elements are extracted into a different set
C = A & B
print(C)
# Union: an operation in which all unique elements are extracted into a different set
C = A | B
print(C)
# Difference is the set of all elements that are in the first set and not in the second set
C = A - B
print(C)
C = B - A
print(C)
