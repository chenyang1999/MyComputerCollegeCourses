import math
Lp=[0]*10

import sys
sys.setrecursionlimit(2000)

def highest(n):
  if n>=10:
    n=n//10
    return highest(n)
  return n
  
def gen_pd(p,i):
  if i>9:
     return 
  p[i]=math.log10((i+1)/i)
  gen_pd(p,i+1)

def gen_a(a,L,i,ll):
  if i>=ll:
    return a
  else:
#    print(i,ll)
#    print(highest(L[i]))
    a[highest(L[i])]=a[highest(L[i])]+1
#    print(a)
    return gen_a(a,L,i+1,ll)
  
def solve(ans,p,a,i,n):
  if i>=len(a):
    return ans
  else:
    ans=ans+math.pow(a[i]-n*p[i], 2)/(n*p[i])
    return solve(ans, p, a, i+1,n)
def obey_benford(L):
  #YOUR CODE GOES HERE
  p=[0]*10
#  print(0)
  gen_pd(p,1)
#  print(p)
  a=[0]*10
  a=gen_a(a,L,0,len(L))
#  print(a)
  ans=solve(0, p, a, 1,len(L))
#  print(ans)
  return ans
  
L = [1] * 301 + [2] * 176 + [3] * 125 + [4] * 97 + [53] * 79 \
+[6]*67 +[7]*58 +[8]*51+[96]*46
#print(L)
#  for i in L:
#    Lp[highest(i)]=Lp[highest(i)]+1
#  n=len(L)
##  print(p[1:])
##  print(Lp[1:])
##  print(n)
#  ans=0
#  for i in range(1,10):
#    ans=ans+pow(Lp[i]-n*p[i],2)/(n*p[i])
#  return ans
#L=[1,2,1337]

L = [1] * 301 + [2] * 176 + [3] * 125 + [4] * 97 + [53] * 79 +[6]*67 +[7]*58 +[8]*51+[96]*46
ans=obey_benford(L)
print(ans)