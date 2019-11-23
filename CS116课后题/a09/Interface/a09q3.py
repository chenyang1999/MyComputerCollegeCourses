from a09q1 import *
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

  for h in range(pla_img.height): 
    for w in range(pla_img.width):  
      if i<L:
        try:
          pla_img.image[h][w][2]+=m[i]
          i+=1
        except Exception as e:
          print(e)
          print("add message error in ",h,w)
          pass
      else:
        continue
  pla_img.save_img(file_name_out)
        
def decode_msg(file_name):
  #YOUR CODE GOES HERE
  img1=PPM(file_name)
  img2=PPM(file_name)
  img2.zero_out()
  img1.sub(img2)
  msg=[]
  message=[]
#  print(img1)
  for h in range(img1.height): 
    for w in range(img1.width): 
      msg.append(str(img1.image[h][w][2]))
  print(msg)
  for i in range(0,len(msg),8):
    m=msg[i:i+8]
    m="".join(m)
    print(i,m)
    m=chr(int(m,2))
    print(m)
    message.append(m)
    if m=='\0':
      break
  print("".join(message))
  return "".join(message)
  
  
message="what you want to say?"
message=list(message)
message=[ord(c) for c in message]
m=[format(i,'08b')for i in message]
print(m)
m=[int(i,2) for i in m]
print(m)
m="".join([chr(i) for i in m])
print(m)

if __name__=="__main__":
  message="what you want to say?"
  img_path="../Beaver_Set/cool_beaver.ppm"
  cipherimg_path="../Beaver_Set/cool_beaver_with_msg.ppm"
  plaintext_path="../Beaver_Set/msg_in_breaver.ppm"
  ciphertext=encode_msg(img_path, message, cipherimg_path)
  plaintext=decode_msg(cipherimg_path)
  
##test encode
test_img_path="../Beaver_Set/treasure.ppm"
#test_img_path="../Beaver_Set/beaver_has_a_message.ppm"
test_img_path="../Smile_Set/why_am_i_smiling(msg).ppm"
decode_msg(test_img_path)
#test_img=PPM(test_img_path)
#print(test_img)
#print(ciphertext)
#print(test_img==ciphertext)