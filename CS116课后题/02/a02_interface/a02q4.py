def strange_sum(n):
  #YOUR CODE GOES HERE
  ans=1
  a=0
  b=1
  for i in range(1,n+1):
    a=a+i
    b=b*i
    ans=ans+a/b
  return ans

num=2
print(strange_sum(num))