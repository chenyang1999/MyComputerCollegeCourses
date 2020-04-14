def q(n):
  if n%10==0:
    return q(n//10)
  else:
    return n
def reverse(n):
  #YOUR CODE GOES HERE
  n=q(n)
  s=list(str(n))
  s.reverse()
  re="".join(s)
  ans=int(re)
  return ans
#a=reverse(1234)
a=reverse(1200)
print(a)