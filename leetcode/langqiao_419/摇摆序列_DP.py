s=[]		
m,n=input().split()
m=int(m)
n=int(n)
dp=[[0]*1024]
for i in range(1024):
	dp+=[[0]*1024]
for i in range(1,n+1):
	dp[1][i]=n-i+1
for i in range(2,m+1):
	if i&1:
		for j in range(n,0,-1):
			dp[i][j] = (dp[i-1][j-1] + dp[i][j+1]) % 10000
	else:
		for j in range(1,n+1):
			dp[i][j] = (dp[i-1][j+1] + dp[i][j-1]) % 10000;
if m&1:
	print(dp[m][1])
else:
	print(dp[m][n])