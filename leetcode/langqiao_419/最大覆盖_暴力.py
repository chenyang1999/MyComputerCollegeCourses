n=int(input())
c=[]
for _ in range(n):
	x,y,r=input().split()
	x=int(x)
	y=int(y)
	r=int(r)
	c.append([x,y,r,0])
	#0 表示没有用过
ans=0
def dfs(sum_rr):
	global ans
	global c
#	print(c)
	if ans<sum_rr:
		ans=sum_rr
	for i in range(n):
		if c[i][3]==0:
			bj=1
			for j in range(n):
				if i!=j :
					if c[j][3]==1:
						if (c[i][0]-c[j][0])**2+(c[i][1]-c[j][1])**2<(c[i][2]+c[j][2])**2:
							bj=0
			if bj==1:
				# c[i][3]表示已经种过了
				c[i][3]=1
#				print(c[i])
				dfs(sum_rr+c[i][2]**2)
				c[i][3]=0
dfs(0)
print(ans)