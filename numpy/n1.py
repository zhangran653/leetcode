import numpy as np

nparr = np.array([i for i in range(10)])
print(nparr)
print(nparr.dtype)

# numpy 创建array

a = np.zeros(10)
print(a.dtype)

a = np.zeros((3, 5))
print(a)

a = np.ones((3, 5))

print(a)

a = np.full((3, 5), 100)
print(a)

# arrange
a = np.arange(0, 20, 2)
print(a)

# linspace
a = np.linspace(0, 20, 10)
print(a)
# random
a = np.random.randint(0, 10)
print(a)
a = np.random.randint(0, 10, 10)
print(a)

a = np.random.random((3, 5))
print(a)
a = np.random.normal(10, 100)
print(a)
# basic operator
x = np.arange(10)
print(f'x: {x}')
X = np.arange(15).reshape(3, 5)
print(f"X: {X}")

print(f"x ndim: {x.ndim}")
print(f"X ndim: {X.ndim}")

print(f"X shape: {X.shape}")

# 数据访问
print(X[0][0])  # not recommended

print(X[2, 3])

print(x[0:5])
print(x[:5])
print(x[::2])

print(X[:2, :3])
print(X[:2, ::2])
print(X[::-1, ::-1])
print(X[0, :])
print(X[:, 0])

subX = X[:2, :3]
print(subX)
subX[0, 0] = 100
print(X)
subX = X[:2, :3].copy()
subX[0, 0] = 1
print(X)

# reshape
print(x.shape)

A = x.reshape(2, 5)
print(A)
B = x.reshape(10, -1)
print(B.shape)

# 合并
x = np.array([1, 2, 3])
y = np.array([3, 2, 1])
z = np.concatenate([x, y])
print(z)
A = np.array([[1, 2, 3], [4, 5, 6]])
print(np.concatenate([A, A]))

print(np.concatenate([A, A], axis=1))

print(np.concatenate([A, x.reshape(1, -1)]))

print(np.vstack([A, x]))

B = np.full((2, 2), 100)
print(np.hstack([A, B]))

# 分割
x = np.arange(10)
x1, x2, x3 = np.split(x, [3, 7])
print(x1)
print(x2)

print(x3)

A = np.arange(16).reshape((4, 4))
A1, A2 = np.split(A, [2])
print(A1)

A1, A2 = np.split(A, [2], axis=1)
print(A2)

u, l = np.vsplit(A, [2])

print(l)

le, ri = np.hsplit(A, [-1])
print(ri)

# 矩阵运算
L = np.arange(10)
print(2 * L)
# universal function
X = np.arange(15).reshape(3, 5)
print('----')
print(X)
print(X / 2)

A = np.arange(4).reshape(2, 2)
B = np.full((2, 2), 10)
print(A + B)
print(A * B)
C = A.dot(B)
print(C)
print(A.T)

# 向量和矩阵的运算
V = np.array([1, 2])
print(V + A)

print(np.tile(V, (2, 1)))

print(V * A)

print(V.dot(A))
print(A.dot(V))

# 矩阵的逆
invA = np.linalg.inv(A)

print(invA)

# 聚合操作

L = np.random.random(100)
print(L)

print(np.sum(L))
print(np.min(L))
print(np.max(L))

X = np.arange(16).reshape(4, -1)
print(np.sum(X))
print(np.sum(X, axis=0))

# 索引
print(np.argmin(L))

# 比较
print(X < 4)
