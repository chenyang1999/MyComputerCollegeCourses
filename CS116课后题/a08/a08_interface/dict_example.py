a={}
a['a']=1
a[1]=123
a[2]=123
a[1]={123:123}
a[123]=3123
for k,v in a.items():
    print(k,v)
for k in a.keys():
    print(k)
for v in a.values():
    print(v)
if 1 in a.keys():
    print(True)
if  in a.items():
    print(True)
