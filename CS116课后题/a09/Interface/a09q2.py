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
if __name__=="__main__":
  secret_img_path="../Smile_Set/rose.ppm"
  show_img_path="../Smile_Set/smile.ppm"
  cipherimg_path="../Smile_Set/cool_beaver_cip.ppm"
  plainimg_path="../Smile_Set/treasure_pla.ppm"
  cipherimg=encode_img(show_img_path, secret_img_path, cipherimg_path)
  plaintimg=decode_img(cipherimg_path, plainimg_path)
  
###test encode
#test_img_path="../Beaver_Set/treasure.ppm"
#test_img_path="../Beaver_Set/beaver_w_treasure.ppm"
#test_img=PPM(test_img_path)
#print(test_img)
#print(cipherimg)
#print(test_img==cipherimg)
#
####test decode
#
#test_img_path="../Beaver_Set/treasure.ppm"
#test_img=PPM(test_img_path)
#print(test_img)
#print(plaintimg)
#print(test_img==plaintimg)

##test smile
#test_img_path="../Smile_Set/look_behind_the_smile(img).ppm"
#smile_img_path="../Smile_Set/whats behind the smile.ppm"
#test_img=decode_img(test_img_path, smile_img_path)