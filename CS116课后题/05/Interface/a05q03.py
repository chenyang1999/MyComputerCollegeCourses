from functools import reduce

def sum(x,y): return x * y 

def zero(x): 
  if x %10 ==0:
    return 1+zero(x//10)
  return 0

def num_zeroes(L):
  #YOUR CODE GOES HERE
  S=reduce(sum, L)
  ans=zero(S)
  return ans

L=[2, 5, 6, 100]
ans=num_zeroes(L)
print(ans)