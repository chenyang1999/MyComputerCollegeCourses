def full_isbn(n):
  l=list(str(n))
#  print(l)
  odd=0
  even=0
  for i in range(0,len(l),2):
    odd=odd+int(l[i])
  for i in range(1,len(l),2):
    even=even+int(l[i])
#  print(odd,even)
  l=l+[str((odd+even*3)%10)]
  return int("".join(l))
  #YOUR CODE GOES HERE

num=567856785678
print(full_isbn(num))