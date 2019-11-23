'''
def chainHash(InputList)：
    res = {}
    for line in InputList:
        if line.split()[0] not in res:
            temp = [] 
            temp.append(line.split()[1])
            res["%s" % line.split()[0]] = temp
        else:
            res["%s" % line.split()[0]].append(line.split()[1])
    return res
'''
def quickSort(L, low, high):
    i = low 
    j = high
    if i >= j:
        return L
    key = L[i]
    while i < j:
        while i < j and L[j] >= key:
            j = j-1                                                             
        L[i] = L[j]
        while i < j and L[i] <= key:    
            i = i+1 
        L[j] = L[i]
    L[i] = key 
    quickSort(L, low, i-1)
    quickSort(L, j+1, high)
    return L

def is_distinct(L):
    # YOUR CODE GOES HERE
    # quickSort(L,0,len(L)-1)
    L.sort()
    for i in range(1,len(L)):
        if L[i-1]==L[i]:
            return False
    return True
L=[-10,9,5,4,-10]
print(is_distinct(L))