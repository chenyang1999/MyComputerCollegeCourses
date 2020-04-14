import math
def doubling_time(i):
  #YOUR CODE GOES HERE
  a=72/(100*i)
  b=math.log(2,(1+i))
  ans=b-a
  ans=abs(ans)
  return ans
print(doubling_time(0.08))