m,n=input().split()
x,y=input().split()

m=int(m)
n=int(n)
x=int(x)
y=int(y)

array=[[0]*n]
for i in range(m-1):
	array+=[[0]*n]
num=1
#确定螺旋的圈数和剩余数据个数
last=0
if m>n:
	count=n//2
	last=m-count*2
elif m==n:
	count=n//2
	last = m - count * 2
else:
	count=m//2
	last = n - count * 2
for i in range(count):
	row=i
	col=i
	for j in range(i,n-i):#每一圈第一行数据
		col=j
		array[row][col]=num
		num+=1
	for j in range(i+1,m-i):#每一圈最后一列数据
		row=j
		array[row][col]=num
		num+=1
	for j in range(n-i-2,i-1,-1):#每一圈最后一行数据
		col=j
		array[row][col]=num
		num+=1
	for j in range(m-i-2,i,-1):#每一圈第一行数据
		row=j
		array[row][col]=num
		num+=1
#依次计算剩余数据
for i in range(last):
	if m>n:
		array[count+i][count]=num
		num+=1
	else:
		array[count][count+i]=num
		num+=1
#for i in range(m):
#	for j in range(n):
#		print('%d ' % array[i][j], end="")
#	print("")
print(array[x-1][y-1])
