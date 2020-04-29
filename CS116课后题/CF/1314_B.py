t=int(input())
for _ in range(t):
	n,k=input().split()
	k=int(k)
	n=int(n)
	x=input().split()
	l=[10**10+1]+[int(i) for i in x]+[10**10+1]
#	print(l)
	cf=[0]*(n+2)
	for j in range(1,n+1):
#		print(j,l[j])
		if l[j-1]<l[j] and l[j]>l[j+1]:
			cf[j]=1
	bj=0
	left=n
#	print(l)
#	print(cf)
	bjj=0
	bjj=sum(cf[n-k+1:n-1])
	for j in range(n+1-k,k,-1):
		if cf[j-k+1]==1:bjj+=1
		if cf[j]==1:bjj-=1
		if bjj>=bj:
			left=j-k
			bj=bjj
#			print(j-k+1,j-1,bjj)
	print(bj+1,left)
	
'''
2
15 7
3 7 4 8 2 3 4 3 21 2 3 4 2 1 3

5
8 6
1 2 4 1 2 4 1 2
5 3
3 2 3 2 1
10 4
4 3 4 3 2 3 2 1 0 1
15 7
3 7 4 8 2 3 4 5 21 2 3 4 2 1 3
7 5
1 2 3 4 5 6 1

'''	
	