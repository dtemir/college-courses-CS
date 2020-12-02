import matplotlib.pyplot as plt
import numpy as np
import statsmodels.api as sm

# data
x = [2, 2, 1, 2, 2, 3, 0, 1, 0, 1, 0, 0, 1, 0, 0, 1]
y = [110, 110, 90, 120, 110, 120, 100, 110, 110, 110, 110, 110, 110, 110, 110, 110,]

# plot
plt.scatter(x, y)
plt.title('Cereal Calories vs Cereal Fats')
plt.xlabel('Calories (per Serving)')
plt.ylabel('Fat (per Gram)')
plt.xlim(xmin=0)
plt.ylim(ymin=0, ymax=140)
plt.show()

# correlation
x_np = np.array(x)
y_np = np.array(y)
r = np.corrcoef(x_np, y_np)

print(r[0, 1])
print(np.mean(y_np))

# hypothesis testing
X = sm.add_constant(x)
Y = sm.add_constant(y)

model = sm.OLS(y, X)
results = model.fit()

print(results.summary())

