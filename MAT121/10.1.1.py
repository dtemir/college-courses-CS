import matplotlib.pyplot as plt
import numpy as np
import statsmodels.api as sm

# data
x = [18, 16, 14, 12, 10, 15, 13, 18, 12, 16, 16]
y = [52866, 40393, 27062, 21427, 14470, 41010, 31376, 79962, 21387, 39925, 57815]

# plot
plt.scatter(x, y)
plt.title('Female Salaries Based On Education')
plt.xlabel('Years of Education')
plt.ylabel('Annual Salary')
plt.ylim(ymin=0)
plt.show()

# correlation
x_np = np.array(x)
y_np = np.array(y)
r = np.corrcoef(x_np, y_np)

print(r[0, 1])

# hypothesis testing
X = sm.add_constant(x)
Y = sm.add_constant(y)

model = sm.OLS(y, X)
results = model.fit()

print(results.summary())

