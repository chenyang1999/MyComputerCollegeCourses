def election_winner(R):
  #YOUR CODE GOES HERE
  S={}
  for D in R:
      for (k,v) in  D.items():
            # print(k,v)
            if k not in S.keys():
                  S[k]=0
            S[k]=S[k]+v
      # print(S)
  print(S)
  ans=max(S.values())
  # print(ans)
  for k,v in S.items():
        if v==ans:
              return k
  return None
district1 = {'Kirby':100,'Luigi':75,'Mario':125,'Marcus':1}
district2 = {'Luigi':200,'Kirby':125,'Mario':125}
ans=election_winner ([district1 , district2 ])
print(ans)
