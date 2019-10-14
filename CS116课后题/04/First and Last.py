L = ["abba", "banana", "dumpling", "level", "trait"]
def first_and_last(L):
	ans=[]
	for i in L:
		print(i)
		if i[0]!=i[-1]:
			ans.append(i)
	return ans
print(first_and_last(L))