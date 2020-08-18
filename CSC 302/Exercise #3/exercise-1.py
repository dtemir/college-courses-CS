"""This is Part 1 of the Exercise #3"""
array = []  # Initialize an array
print("Enter one digit at a time, enter 'q' to stop")
while True:  # Keep receiving input to the array until user enters 'q'
    curr = input()
    if curr == 'q':
        break
    else:
        curr = int(curr)
        array.append(curr)

length = len(array)  # Initialize a variable for storing the length of the array
for i in range(length - 1):  # Iterate through the array
    for j in range(length - i - 1):  # Iterate through the array while comparing neighbor digits (bubbles)
        if array[j] > array[j + 1]:  # In case one neighbor digit is greater, switch their values
            array[j], array[j + 1] = array[j + 1], array[j]  # Pythonic switching

print(array)
