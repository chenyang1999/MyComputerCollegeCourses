from a09q1 import *
def encode_img(file_name, file_name_secret, file_name_out):
  #YOUR CODE GOES HERE
  pla_img=PPM(file_name)
  pla_img.zero_out()
  secret_img=PPM(file_name_secret)
  secret_img.get_grid()
  pla_img.add(secret_img)
  pla_img.save_img(file_name_out)
  return pla_img

def decode_img(file_name, file_name_out):
  #YOUR CODE GOES HERE
  img1=PPM(file_name)
  img2=PPM(file_name)
  img2.zero_out()
  img1.sub(img2)
  img1.re_grid()
  img1.save_img(file_name_out)
  return img1
  
if __name__=="__main__":
  secret_img_path="../Beaver_Set/treasure.ppm"
  show_img_path="../Beaver_Set/cool_beaver.ppm"
  cipherimg_path="../Beaver_Set/cool_beaver_cip.ppm"
  plainimg_path="../Beaver_Set/treasure_pla.ppm"
  cipherimg=encode_img(show_img_path, secret_img_path, cipherimg_path)
  plaintimg=decode_img(cipherimg_path, plainimg_path)
  
###test encode
#test_img_path="../Beaver_Set/treasure.ppm"
test_img_path="../Beaver_Set/beaver_w_treasure.ppm"
test_img=PPM(test_img_path)
print(test_img)
print(cipherimg)
print(test_img==cipherimg)

###test decode
#test_img_path="../Beaver_Set/treasure.ppm"
test_img_path="../Beaver_Set/treasure.ppm"
test_img=PPM(test_img_path)
print(test_img)
print(plaintimg)
print(test_img==plaintimg)

##test smile
test_img_path="../Smile_Set/look_behind_the_smile(img).ppm"
smile_img_path="../Smile_Set/whats behind the smile.ppm"
test_img=decode_img(test_img_path, smile_img_path)