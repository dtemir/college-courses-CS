# Assignment 1 - Building a Functional Perceptron

In this assignment, we build a **perceptron** and train it on a set of sonar data to see how accurately it can distinguish 
between two different classes of objects: **rocks** and **metal** cylinders. 

We train the perceptron using a _subset_ of data based on the [Perceptron Learning Algorithm](https://machinelearningmastery.com/perceptron-algorithm-for-classification-in-python/)

The result is a perceptron that is trained and can identify a rock or a metal cylinder with a **90% accuracy**.
    
    $ college-courses-CS/CSC470/sonar-perceptron$ python3 perceptron_tester.py 
    FULL DATASET TEST
    Learning rate parameters: 0.02;
    Number of epochs: 3000;
    Bias: -0.4600000000000025;
    Accuracy: 90.38461538461539%

## Data

The dataset is sonar data presented in a _.csv_ format. It has 208 rows of different inputs with 60 different values/columns
for each input, representing different angle readings from the sonar. The last, 61 column is the label, _R_ or _M_.

The dataset has to be pre-processed t convert the labels into numeric values and convert _.csv_ into a matrix.

## Findings

1. I picked **50% as the training set** to train the perceptron.
   * This leaves the results open for interpretation. 
   * The perceptron could be over-fit, but with as little as we're given, I would like to use more data for training.
2. To train the perceptron, I found that **3000 epochs** is more than an optimal number of iterations to train the perceptron.
   * I based this finding on the fact that the perceptron would start producing a **100% accuracy for the test data**.
3. I set the **bias to 3.0, but it gets dynamically adjusted** because I wanted the data to suit the bias better for its needs.
   * Making it dynamic enabled the perceptron to train better and produce greater accuracy.
4. I set the **learning parameter to 0.02** which I found to be small enough for the perceptron to make specific adjustments to weights.
   * Such a small learning parameter helped the training to adjust weights and not _overshoot_ to give too much weight for certain inputs.
   * However, it produces similar results of around 85% even if set to a value like 3.0 or 4.0.

_Note:_ Please make sure to use the <code>random.seed(0)</code> to re-create the results
