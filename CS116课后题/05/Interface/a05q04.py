def recaman(n,r,i):
  #YOUR CODE GOES HERE
  if i<=n:
    if i>0 and r[i-1]-i>=0 and r[i-1]-i not in r[0:i]:
      r[i]=r[i-1]-i
    else:
      r[i]=r[i-1]+i
    print(r,i)
    return recaman(n, r, i+1)
  else:return r[n]
  
n=6
ans=recaman(n,[0]*(n+1),1)
print(ans)