import matplotlib.pyplot as plt
import numpy as np
import statsmodels.api as sm

# data
x = [7, 9, 6, 7, 11, 12, 11, 3, 3, 7, 9, 1]
y = [48897, 62866, 54585, 56611, 53541, 53541, 47027, 47970, 46668, 48029, 46735, 45000]

# plot
plt.scatter(x, y)
plt.title('Teaching Experience vs Annual Salary')
plt.xlabel('Number of Years')
plt.ylabel('Annual Salary')
plt.xlim(xmin=0)
plt.ylim(ymin=0)
plt.show()

# correlation
x_np = np.array(x)
y_np = np.array(y)
r = np.corrcoef(x_np, y_np)

print(r[0, 1])
print(np.median(y_np))

# hypothesis testing
X = sm.add_constant(x)
Y = sm.add_constant(y)

model = sm.OLS(y, X)
results = model.fit()

print(results.summary())

