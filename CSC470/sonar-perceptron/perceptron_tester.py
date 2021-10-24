from csv import reader  # reader object reads a csv file line by line
import random  # random helps pick training set
from perceptron import Perceptron  # Perceptron object

"""
DATASET FUNCTIONS
"""


# Load the CSV file containing the inputs and desired outputs
#
# dataset is a 2D matrix where each row contains 1 set of inputs plus the desired output
#  -for each row, columns 0-59 contain the inputs as floating point values
#  -column 60 contains the desired output as a character: 'R' for Rock or 'M' for Metal
#  -all values will be string values; conversion to appropriate types will be necessary
#  -no bias value is included in the data file

def load_csv(filename):
    # dataset will be the matrix containing the inputs
    dataset = list()

    # Standard Python code to read each line of text from the file as a row
    with open(filename, 'r') as file:
        csv_reader = reader(file)
        for row in csv_reader:
            if not row:
                continue

            # add current row to dataset
            dataset.append(row)

    return dataset


# Convert the input values in the specified column of the dataset from strings to floats
def convert_inputs_to_float(dataset, column):
    for row in dataset:
        row[column] = float(row[column].strip())


# Convert the desired output values, located in the specified column, to unique integers
# For 2 classes of outputs, 1 desired output will be 0, the other will be 1
def convert_desired_outputs_to_int(dataset, column):
    # Enumerate all the values in the specified column for each row
    class_values = [row[column] for row in dataset]

    # Create a set containing only the unique values
    unique = set(class_values)

    # Create a lookup table to map each unique value to an integer (either 0 or 1)
    lookup = dict()
    for i, value in enumerate(unique):
        lookup[value] = i

    # Replace the desired output string values with the corresponding integer values
    for row in dataset:
        row[column] = lookup[row[column]]

    return lookup


# Load the dataset from the CSV file specified by filename
def load_dataset(filename):
    # Read the data from the specified file
    dataset = load_csv(filename)

    # Convert all the input values form strings to floats
    for column in range(len(dataset[0]) - 1):
        convert_inputs_to_float(dataset, column)

    # Convert the desired outputs from strings to ints
    convert_desired_outputs_to_int(dataset, len(dataset[0]) - 1)

    return dataset


"""
CREATE THE TRAINING SET
"""


# Create the training set
# -Training set will consist of the specified percent fraction of the dataset
# -How many inputs you decide to use for the training set, and how you choose
# those values, is entirely up to you
#
# Params:	dataset - the entire dataset
#
# Returns:	a matrix, or list of rows, containing only a subset of the input
# vectors from the entire dataset
def create_training_set(dataset):
    # Seed the generator to obtain same results for testing
    random.seed(0)

    # pick 50% of dataset as training
    training_set = random.sample(dataset, 104)

    return training_set


"""
CREATE A PERCEPTRON, TRAIN IT, AND TEST IT
"""

# Step 1: Acquire the dataset
dataset = load_dataset('sonar_all-data.csv')

# Steps 2 & 3 are omitted because load_dataset() manages them

# Step 4: Create the training set

training_set = create_training_set(dataset)

# Step 5: Create the perceptron

synaptic_weights = [0.0] * (len(dataset[0]) - 1)
bias = 3.0
perceptron = Perceptron(bias, synaptic_weights)

# Step 6: Train the perceptron

epochs = 3000
learning_rate_parameter = 0.02
perceptron.train(training_set, learning_rate_parameter, epochs)

# Step 7: Test the trained perceptron & Step 8: Display the test results and accuracy of the perceptron

print("TRAINING DATASET TEST")

perceptron.test(training_set, learning_rate_parameter, epochs)

print("FULL DATASET TEST")

perceptron.test(dataset, learning_rate_parameter, epochs)