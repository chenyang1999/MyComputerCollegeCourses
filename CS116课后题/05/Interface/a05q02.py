import math
Lp=[0]*10

import sys
sys.setrecursionlimit(2000)

def highest(n):
  if n>=10:
    n=n//10
    return highest(n)
  return n
  
def gen_pd(i):
  return math.log10((i+1)/i)

def gen_a(L:list,i):
  if i in L:
    return L.count(i)
  else:
    return 0

def solve(p,a,n):
#  print(p,a,n)
  return math.pow(a-n*p, 2)/(n*p)
    
def obey_benford(L):
  #YOUR CODE GOES HERE
  p=list(map(gen_pd,range(1,10)))
#  print(p)
  L=list(map(highest, L))
#  print(L)
  n=len(L)
  a=list(map(gen_a,[L]*10,range(1,10)))
#  print(a)
  ans=list(map(solve,p,a,[n]*10))
#  print(ans)
  ans=sum(ans)
  return ans


#L = [1, 2, 1337]
L = [1] * 301 + [2] * 176 + [3] * 125 + [4] * 97 + [53] * 79 +[6]*67 +[7]*58 +[8]*51+[96]*46
ans=obey_benford(L)
print(ans)