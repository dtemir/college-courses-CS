"""This is implementation of the Exercise 4"""
string_list = list(input())  # Convert user input to a list

string_output = []  # Create a list for storing the reversed list
length = len(string_list)  # Assign a variable to represent the length of the string

for i in range(length):  # Iterate through the string list
    # Append values to the output string by taking last elements from
    # the string list
    string_output.append(string_list[-(i + 1)])

print(string_output)

# Pythonic version
string_list.reverse()
print(string_list)
