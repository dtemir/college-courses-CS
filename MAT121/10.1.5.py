import matplotlib.pyplot as plt
import numpy as np
import statsmodels.api as sm

# data
x = [3, 8, 0.25, 4.7, 13.5]
y = [100, 152, 152, 177, 211]

# plot
plt.scatter(x, y)
plt.title('Animal Gestation/Incubation vs Average Lifespan')
plt.xlabel('Incubation/Gestation (in Months)')
plt.ylabel('Average Lifespan (in Years)')
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

