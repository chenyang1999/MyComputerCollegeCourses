#def grow_word(s):
#  #YOUR CODE GOES HERE
#  L=len(s)
#  list_s=list(s)
#  print(list_s)
#  for i in range(L):
#    print(list_s[i],i)
#    list_s[i]=list_s[i]+list_s[i]*i
#  print(list_s)
#  ans="".join(list_s)
##  print(ans)
#  return ans
  
def grow_word(s,i):
  L=len(s)
  if i>=L:
#    print(s)
#    print("".join(s))
    print(s)
    return "".join(s)
  else:
    s[i]=s[i]+s[i]*i
#    print(s)
    return grow_word(s,i+1)
      
s="banana"
ans=grow_word(list(s),0)
#print(s)
print(ans)