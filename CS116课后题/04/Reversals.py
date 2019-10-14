s="live on time"
def reverse3(s):
	l=list(s)
	l.reverse()
	print("".join(l))
	return "".join(l)
	
def reversal(s):
	l=s.split(" ")
	ans=[]
	print(l)
	for i in l:
		ans.append(reverse3(i))
		print(ans)
	ans=" ".join(ans)
	return ans
print(reversal(s))