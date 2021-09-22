class Perceptron(object):

	# Create a new Perceptron
	# 
	# Params:	bias -	arbitrarily chosen value that affects the overall output
	# regardless of the inputs
	#
	# synaptic_weights -	list of initial synaptic weights for this Perceptron
	def __init__(self, bias, synaptic_weights):

		assert type(synaptic_weights) == list, "synaptic weights have to be passed as a list"
		assert type(bias) == float, "bias must be a float number"

		self.bias = bias
		self.synaptic_weights = synaptic_weights

	# Activation function
	# Quantizes the induced local field
	#
	# Params:	z - the value of the induced local field
	#
	# Returns:	an integer that corresponds to one of the two possible output values (usually 0 or 1)
	def activation_function(self, z):
		if z < 0:
			return 0
		else:
			return 1

	# Compute and return the weighted sum of all inputs (not including bias)
	#
	# Params:	inputs - a single input vector (which may contain multiple individual inputs)
	#
	# Returns:	a float value equal to the sum of each input multiplied by its
	# corresponding synaptic weight
	def weighted_sum_inputs(self, inputs):
		assert type(inputs) == list, "inputs must be a list"

		total_weighted_sum = 0
		# find the total sum of each given vector
		for i, single_input in enumerate(inputs):
			total_weighted_sum += single_input * self.synaptic_weights[i]

		return total_weighted_sum

	# Compute the induced local field (the weighted sum of the inputs + the bias)
	#
	# Params:	inputs - a single input vector (which may contain multiple individual inputs)
	#
	# Returns:	the sum of the weighted inputs adjusted by the bias
	def induced_local_field(self, inputs):
		assert type(inputs) == float, "inputs must be a float"

		total_induced_field = 0
		# find the total sum of each given vector including bias
		total_induced_field += self.bias + inputs

		return total_induced_field

	# Predict the output for the specified input vector
	#
	# Params:	input_vector - a vector or row containing a collection of individual inputs
	#
	# Returns:	an integer value representing the final output, which must be one of the two
	# possible output values (usually 0 or 1)
	def predict(self, input_vector):

		input_vector = input_vector[:-1] # avoid the last column which denotes the status
		weighted_values = self.weighted_sum_inputs(input_vector)
		induced_values = self.induced_local_field(weighted_values)
		result = self.activation_function(induced_values)

		return result

	# Train this Perceptron
	#
	# Params:	training_set - a collection of input vectors that represents a subset of the entire dataset
	# learning_rate_parameter - the amount by which to adjust the synaptic weights following an
	# incorrect prediction
	# number_of_epochs - the number of times the entire training set is processed by the perceptron
	#
	# Returns:	no return value
	def train(self, training_set, learning_rate_parameter, number_of_epochs):

		for epoch in range(0, number_of_epochs):
			for row in training_set:
				prediction = self.predict(row)
				error = row[-1] - prediction

				# update bias
				self.bias += learning_rate_parameter * error

				# update weights
				for i in range(0, len(self.synaptic_weights)):
					weight = self.synaptic_weights[i] + learning_rate_parameter * error * row[i]
					self.synaptic_weights[i] = weight

	# Test this Perceptron
	# Params: test_set - the set of input vectors to be used to test the perceptron after it has been trained
	#
	# Returns: a collection or list containing the actual output (i.e., prediction) for each input vector
	def test(self, test_set, learning_rate_parameter, number_of_epochs):

		correct = 0
		for row in test_set:
			result = self.predict(row)
			if result == row[-1]:
				correct += 1

		num_inputs = len(test_set)
		accuracy = (correct / num_inputs) * 100

		print("""Weights are:
{};
Learning rate parameters: {};
Number of epochs: {};
Bias: {};
Accuracy: {}%
""".format(self.synaptic_weights, learning_rate_parameter, number_of_epochs, self.bias, accuracy))
