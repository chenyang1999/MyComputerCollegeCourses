s=input()
s=list(s)
ans=[]
for c in s:
	if c=='x' or c=='y' or c=='z':
		c=chr(ord(c)+3-26)
		ans.append(c)
	else:
		c=chr(ord(c)+3)
		ans.append(c)
print("".join(ans))