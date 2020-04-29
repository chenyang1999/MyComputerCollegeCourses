t=int(input())
for _ in range(t):
	cin=input().split()
	abcdi=[int(i) for i in cin]
	n,a,b,c,d=abcdi
#	print(c-d,(a-b)*xi,(a+b)*xi,c+d)
	if c-d<=(a-b)*n<=c+d or c-d<=(a+b)*n<=c+d or (a-b)*n<=c-d<=c+d<=(a+b)*n:
		print("Yes")
	else:
		print("No")