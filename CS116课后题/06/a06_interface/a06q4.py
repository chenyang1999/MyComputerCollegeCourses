def palindrome(s):
  if s==s[::-1]:
    return True
  else:
    return False
    
def dg(s,i,k):
  if i+k<len(s):
    if palindrome(s[:i]+s[i+k:]):
      return True
    else:
      return dg(s,i+1,k)
  else: 
    return False

def near_palindrome(s, k):
  return dg(s, 0, k)
#  for i in range(len(s)-k):
#    print(s[:i],s[i+k:])
#    if palindrome(s[:i]+s[i+k:]):
#      print(s[:i],s[i+k:])
#      return True
#  return False
s="adcda"
k=2
print(near_palindrome(s,k))
