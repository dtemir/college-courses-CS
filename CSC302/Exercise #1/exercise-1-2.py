"""Part 2 of the Exercise 1"""
count = 25
total = 0
average = 0

for j in range(count):
    i = int(input())
    total += i

average = total / count

print(average)