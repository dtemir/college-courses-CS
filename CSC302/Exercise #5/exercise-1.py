"""This is an implementation of the Exercise 5"""
n = int(input())
count = 0
sumBefore, sumAfter = 0, 0
current, currentIncreased = 0, 0
averageSumBefore, averageSumAfter = 0, 0

for i in range(n):
    current = int(input())
    sumBefore = sumBefore + current
    if current < 50000:
        currentIncreased = current * 1.05
        sumAfter = sumAfter + currentIncreased
    else:
        currentIncreased = current * 1.04
        sumAfter = sumAfter + currentIncreased
    count = count + 1

averageSumBefore = int(sumBefore/count)
averageSumAfter = int(sumAfter/count)
print(sumBefore, sumAfter)
print(sumAfter-sumBefore)
print(averageSumBefore, averageSumAfter)
print(averageSumAfter - averageSumBefore)
print(count)