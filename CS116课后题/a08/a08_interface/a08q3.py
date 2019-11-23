def encryption(original, encrypted):
  #YOUR CODE GOES HERE
  l=len(original)
  ans={}
  for i in range(l):
        if original[i] not in ans.keys():
              ans[original[i]]=encrypted[i]
        if ans[original[i]]!=encrypted[i]:
              return {}
  return ans
print(encryption('banana','ymrmrm'))