s=[]
def permutations(arr, position, end):
	if position == end:
		print(arr)
		s.append("".join(arr))
	else:
		for index in range(position, end):

			arr[index], arr[position] = arr[position], arr[index]
			permutations(arr, position+1, end)
			arr[index], arr[position] = arr[position], arr[index]

arr = ["(","(","(","(",")",")",")",")"]
permutations(arr, 0, len(arr))
print(len(s))
s=list(set(list(s)))
print(len(s))
