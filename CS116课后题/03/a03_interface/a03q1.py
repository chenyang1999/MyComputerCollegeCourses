sc=list(["!@#$%^&*(),.<>?{}[]+-_"])
low=[chr(i) for i in range(97,123)]
up=[chr(i) for i in range(65,91)]
num=list(str(1234567890))
#print(sc)
#print(low)
#print(up)
def valid_password(s):
  if ' ' in s:return False
  if len(s)<8:return False
  if len([i for i in s if i in low])==0:return False
  if len([i for i in s if i in up])==0:return False
  if len([i for i in s if i in num])==0:return False
  if len([i for i in s if i in sc])==0:return False
  return True
password="aB1!filler"
#password="f4!L"
#password="Tru$tNo1"
print(valid_password(password))