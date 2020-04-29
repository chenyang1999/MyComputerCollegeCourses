import math
n=int(input())
l=[]#l[i]表示每个村庄的三维
for _ in range(n):
	cin=input().split()
	x,y,z=cin
	x=int(x)
	y=int(y)
	z=int(z)
	l.append([x,y,z])
#print(l)
mat=[[0]*n]
for i in range(n-1):
	mat+=[[0]*n]

for i in range(n):
	for j in range(n):
		mat[i][j]=math.sqrt((l[i][0]-l[j][0])**2+(l[i][1]-l[j][1])**2)+(l[i][2]-l[j][2])**2
#		print(mat[i][j],i,j)
#print(mat)

# Minimum spanning tree. Prim algorithm
import sys

# 同样的，这里也是为了引入无穷大

graphMatrix = mat

treeDis = graphMatrix[0]  # 各个点距离生成树的最短距离列表
visited = [0 for i in range(n)]  # 已经访问过的节点将被置为1
visited[0] = 1
# 不在树中的点距离树有最短距离，在树中对应的距离最小的那个店
# 比如neighbor[1]=0表示在节点1还不在树中时，它离树中的节点0距离最小
neighbor = [0] * n
for i in range(n-1):
	minDis = sys.maxsize
	minDisPos = int()
	# 找出此时离树距离最小的不在树中顶点
	for j in range(n):
		if (not visited[j]) and (treeDis[j] < minDis):
			minDis = treeDis[j]
			minDisPos = j

	visited[minDisPos] = 1
#	print(minDisPos, minDis)
	# print("Here tree")
	# print(treeDis)
	for j in range(n):
		# 刷新剩下的顶点距离树的最短距离
		if (not visited[j]) and (graphMatrix[j][minDisPos] < treeDis[j]):
			treeDis[j] = graphMatrix[j][minDisPos]
			neighbor[j] = minDisPos
		# print("Here minDIsPos : "+str(minDisPos))

#print(neighbor)
#print("Edges that in the tree:")
ans=0
for i in range(1, n):
#	print(str(i) + '-' + str(neighbor[i]),mat[i][neighbor[i]])
	ans+=mat[i][neighbor[i]]
print('%.2f'%ans)
