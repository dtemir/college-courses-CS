"""Part 1 of the Exercise 1"""
count = 0
total = 0
average = 0

print("Enter grades one a time, enter -1 to finish")
i = int(input())
while i != -1:
    count += 1
    total += i
    i = int(input())

average = total / count

print(average)