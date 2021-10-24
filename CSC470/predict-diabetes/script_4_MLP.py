# SCRIPT 4
# Replacing the Adam optimizer with another options.
import pandas as pd
import numpy as np
import matplotlib
from matplotlib import pyplot as plt
import seaborn as sns
from sklearn import preprocessing
from sklearn.model_selection import train_test_split
from sklearn.metrics import confusion_matrix, roc_curve
from keras.models import Sequential
from keras.layers import Dense

# read data
print("Reading csv...")
df = pd.read_csv('diabetes.csv')

# get histograms
def get_hist(df):
    df.hist()
    plt.show()

# STEP 1: Histograms
# get_hist(df)

# get density plots
def get_dens_plot(df):
    # create a subplot of 3 x 3
    figure, axes = plt.subplots(3,3,figsize=(15,15))

    # make enough padding for titles
    figure.tight_layout(pad=5.0)

    # plot density plot for each variable
    for idx, col in enumerate(df.columns):
        ax = plt.subplot(3,3,idx+1)
        ax.yaxis.set_ticklabels([])
        sns.distplot(df.loc[df.Outcome == 0][col],
                     hist=False,
                     axlabel=False,
                     kde_kws={'linestyle': '-',
                              'color': 'black',
                              'label': "No Diabetes"})
        sns.distplot(df.loc[df.Outcome == 1][col],
                    hist=False,
                    axlabel=False,
                    kde_kws={'linestyle': '--',
                             'color': 'black',
                             'label': "Diabetes"})
        ax.set_title(col)

    # hid last subplot because they're outcomes
    plt.subplot(3,3,9).set_visible(False)

    # show plot
    plt.show()

# STEP 2: Density Plots
# get_dens_plot(df)

# STEP 3: Preprocessing
# check for null values
print("Checking for null values...")
print(df.isnull().any())

# get descriptive statistics
print("Printing descriptive statistics...")
pd.set_option('max_columns', None)
print(df.describe(include='all'))

# get zero values in each column
print("Number of rows with 0 values for each variable:")
def rows_with_zeroes(df):    
    for col in df.columns:
        missing_rows = df.loc[df[col] == 0].shape[0]
        print(col + ": " + str(missing_rows))

rows_with_zeroes(df)

print("Replacing 0 values in each column...")
# replace 0 values with NaN
df['Glucose'] = df['Glucose'].replace(0, np.nan)
df['BloodPressure'] = df['BloodPressure'].replace(0, np.nan)
df['SkinThickness'] = df['SkinThickness'].replace(0, np.nan)
df['Insulin'] = df['Insulin'].replace(0, np.nan)
df['BMI'] = df['BMI'].replace(0, np.nan)

rows_with_zeroes(df)

# replace NaN with means for existing values
df['Glucose'] = df['Glucose'].fillna(df['Glucose'].mean())
df['BloodPressure'] = df['BloodPressure'].fillna(df['BloodPressure'].mean())
df['SkinThickness'] = df['SkinThickness'].fillna(df['SkinThickness'].mean())
df['Insulin'] = df['Insulin'].fillna(df['Insulin'].mean())
df['BMI'] = df['BMI'].fillna(df['BMI'].mean())

# STEP 4: Normalizing
# normalize the datavia centering
print("Centering the data...")
df_scaled = preprocessing.scale(df)

df_scaled = pd.DataFrame(df_scaled, columns=df.columns)
df_scaled['Outcome'] = df['Outcome']
df = df_scaled

print(df.describe().loc[['mean', 'std', 'max'],].round(2).abs())

# STEP 5: Training, Testing, and Validation Sets
# split datset into an input matrix and outcome vector
X = df.loc[:, df.columns != 'Outcome']
y = df.loc[:, 'Outcome']

# create training and testing sets
print("Preparing training set, testing set, and validation set...")
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)

# create validation set
X_train, X_val, y_train, y_val = train_test_split(X_train, y_train, test_size=0.2)

# STEP 6: Design Multilayer Perceptron
# create Keras Sequential class
print("Creating an MLP...")
model = Sequential()

# add first hidden layer
model.add(Dense(32, activation='relu', input_dim=8))

# add second hidden layer
model.add(Dense(16, activation='relu'))

# add output layer
model.add(Dense(1, activation='sigmoid'))

# compile the network
model.compile(optimizer='adamax',
              loss='binary_crossentropy',
              metrics=['accuracy'])

# STEP 7: Train the Network
# train with 200 epochs
print("Training the MLP...")
model.fit(X_train, y_train, epochs=200)

# STEP 8: Test the Network
# evaluate with training set
scores = model.evaluate(X_train, y_train)
print("Training Accuracy: %.2f%%\n" % (scores[1]*100))

# evaluate with testing set
scores = model.evaluate(X_test, y_test)
print("Testing Accuracy: %.2f%%\n" % (scores[1]*100))

# see true positives and false negatives
def get_confusion_matrix():
    global model, X_test, y_test
    y_test_pred = (model.predict(X_test) > 0.5).astype('int32')
    c_matrix = confusion_matrix(y_test, y_test_pred)
    ax = sns.heatmap(c_matrix, annot=True,
                     xticklabels=['No Diabetes', 'Diabetes'],
                     yticklabels=['No Diabetes', 'Diabetes'],
                     cbar=False, cmap='Blues')
    ax.set_xlabel('Prediction')
    ax.set_ylabel('Actual')
    
    plt.show()

print("Presenting the confusion matrix...")
get_confusion_matrix()
    
def get_roc_curve():
    global model, X_test, y_test
    y_test_pred_probs = model.predict(X_test)

    FRP, TRP, _ = roc_curve(y_test, y_test_pred_probs)

    plt.plot(FRP, TRP)
    plt.plot([0,1], [0,1], '--', color='black') # diagonal line
    plt.title("ROC Curve")
    plt.xlabel("False Positive Rate")
    plt.ylabel("True Positive Rate")
    plt.show()

print("Presenting the ROC curve...")
get_roc_curve()
