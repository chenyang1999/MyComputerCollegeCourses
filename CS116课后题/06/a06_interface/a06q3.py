def pd(L,prime):
  if L in prime:
    return True
  ans=False
  if L[:1] in prime:ans=ans|pd(L[1:], prime)
  if L[:2] in prime:ans=ans|pd(L[1:], prime)
  if L[:3] in prime:ans=ans|pd(L[1:], prime)
  if L[:4] in prime:ans=ans|pd(L[1:], prime)
  return ans
def is_slime(num):
  prime=['','0']
  p_num=0
  bj=[True]*(min(10000,num))
  #筛选法
  for i,is_prime in enumerate(bj):
    if i<2:
      bj[i]=False
      continue
    if is_prime:
      for j in range(i+i,min(10000,num),i):
        bj[j]=False
  for i in range(min(10000,num)):
    if bj[i]:
      prime.append(str(i))
  
  return pd(str(num), prime)
num=70317
print(is_slime(num))
