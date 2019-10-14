s='word'
def tsunami(s):
	l=[]
	for i in range(len(s)):
		x=s
		x=x.replace(x[i], x[i].upper())
		l.append(x)
	return l
print(tsunami(s))