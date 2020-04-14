from a09q1 import *  
def save_img(ppm,filename=None):
  if filename!=None:
    f= open(filename,'w')
    f.write(ppm.magic_number)
    f.write("\n")
    f.writelines(str(ppm.width)+" "+str(ppm.height))
    f.write("\n")
    f.writelines(str(ppm.maxval))
    f.write("\n")
    img=[]
    j=0
    for i in ppm.image:
#      print(i,ppm.width*3)
      if j>0 and (j+1)%(ppm.width*3)==0:
        img.append(str(i)+"\n")
      else:
        img.append(str(i)+" ")
      j+=1
    f.writelines(img)

def get_grid(ppm):
  it=2
  while it<len(ppm.image):
    if ppm.image[it]==255:
      ppm.image[it-2]=0
      ppm.image[it-1]=0
      ppm.image[it]=1
    it+=3
  return ppm

def re_grid(ppm):
  it=2
  while it<len(ppm.image):
    if ppm.image[it]==1:
      ppm.image[it-2]=255
      ppm.image[it-1]=255
      ppm.image[it]=255
    it+=3
  return ppm

def add(y,x):
  it=0
  while it<len(y.image):
    y.image[it]+=x.image[it]
    it+=1
  return y

def sub(y,x):
  it=0
  while it<len(y.image):
    y.image[it]-=x.image[it]
    it+=1
  return y
  
def encode_img(file_name, file_name_secret, file_name_out):
  #YOUR CODE GOES HERE
  pla_img=PPM(file_name)
  pla_img.zero_out()
  secret_img=PPM(file_name_secret)
  secret_img=get_grid(secret_img)
  pla_img=add(pla_img,secret_img)
  save_img(pla_img,file_name_out)
  return None

def decode_img(file_name, file_name_out):
  #YOUR CODE GOES HERE
  img1=PPM(file_name)
  img2=PPM(file_name)
  img2.zero_out()
  img1=sub(img1,img2)
  img1=re_grid(img1)
  save_img(img1,file_name_out)
  return None  
  
def encode_msg(file_name, msg, file_name_out):
  #YOUR CODE GOES HERE
  message=list(msg)
  message=[ord(c) for c in message]
  m=[format(i,'08b') for i in message]
  m="".join(m)
  m=list(m)
  m=[int(i) for i in m]
  print(m)
  L=len(m)
  i=0
  pla_img=PPM(file_name)
  pla_img.zero_out()

#  for h in range(pla_img.height): 
#    for w in range(pla_img.width):  
#      if i<L:
#        try:
#          pla_img.image[h][w][2]+=m[i]
#          i+=1
#        except Exception as e:
#          print(e)
#          print("add message error in ",h,w)
#          pass
#      else:
#        break
  it1=2
  it2=0
  while it2<len(m):
    pla_img.image[it1]+=m[it2]
    it1+=3
    it2+=1
  save_img(pla_img,file_name_out)
        
def decode_msg(file_name):
  #YOUR CODE GOES HERE
  img1=PPM(file_name)
  img2=PPM(file_name)
  img2.zero_out()
  img1=sub(img1,img2)
#  print(img1.image)
  msg=[]
  message=[]
#  print(img1)
#  for h in range(img1.height): 
#    for w in range(img1.width): 
#      msg.append(str(img1.image[h][w][2]))
  it=2
  while it<len(img1.image):
    msg.append(str(img1.image[it]))
    it+=3
  for i in range(0,len(msg),8):
    m=msg[i:i+8]
    m="".join(m)
#    print(i,m)
    m=chr(int(m,2))
#    print(m)
    if m!='\0':
      message.append(m)

#  print("".join(message))
  return "".join(message)
  
## test message
#message="what you want to say?"
#message=list(message)
#message=[ord(c) for c in message]
#m=[format(i,'08b')for i in message]
#print(m)
#m=[int(i,2) for i in m]
#print(m)
#m="".join([chr(i) for i in m])
#print(m)
## 

#if __name__=="__main__":
#  message="what do you want to say?"
#  img_path="../Beaver_Set/cool_beaver.ppm"
#  cipherimg_path="../Beaver_Set/cool_beaver_with_msg.ppm"
#  plaintext_path="../Beaver_Set/msg_in_breaver.ppm"
#  ciphertext=encode_msg(img_path, message, cipherimg_path)
#  plaintext=decode_msg(cipherimg_path)
#  
###test encode
##test_img_path="../Beaver_Set/treasure.ppm"
#test_img_path="../Beaver_Set/beaver_has_a_message.ppm"
test_img_path="../Smile_Set/why_am_i_smiling(msg).ppm"
ciphertext=decode_msg(test_img_path)
print(ciphertext)
#print(test_img==ciphertext)