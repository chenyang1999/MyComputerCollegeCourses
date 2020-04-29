t=int(input())
for _ in range(t):
	n,k=input().split()
	k=int(k)
	n=int(n)
	x=input().split()
	l=[10**9+1]+[int(i) for i in x]+[10**9+1]
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
	for j in range(n+1,k,-1):
		if sum(cf[j-k+1:j-1])>=bj:
			left=j-k
			bj=sum(cf[j-k:j])
#			print(j-k+1,j-1)
	print(bj+1,left)
			
	