import matplotlib.pyplot as plt
import numpy as np
import statsmodels.api as sm

# data
x = [27320, 25780, 32571, 27568, 26349, 25577, 22253, 24693, 22879, 24517, 29092, 22785, 23358, 28895, 24418, 24520]
y_c = [70, 59, 62, 66, 59, 60, 55, 59, 48, 61, 59, 47, 48, 54, 58, 55]
y = [p / 100 for p in y_c]
# plot
plt.scatter(x, y)
plt.title('Average Debt vs Percentage with Debt')
plt.xlabel('Average Debt (in Dollars)')
plt.ylabel('Percentage with Debt')
plt.xlim(xmin=20000)
plt.ylim(ymin=0)
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

