def flatten(L,ans):
  ans=[]
  print(*L)
#  L=(*L)
#  L=list(*L)
#  while [] in L:
#    L=*list(L)
  for i in L:
    if type(i)!=list:
      ans.append(i)
    else:
      flatten(i,ans)
  print(L)

L=[[1 ,2 ,3] ,[4 ,5 ,[6] , None , []]]
ans=[]
flatten(L,ans)
print(ans)
