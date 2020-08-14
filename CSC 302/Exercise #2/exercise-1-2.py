"""Part 2 of the Exercise 2"""
A = set(list(map(int, input().split())))
B = set(list(map(int, input().split())))

C = A & B
print(C)

C = A | B
print(C)