class PPM:
  maxval=0
  def __init__(self, file_name):
    '''
    Fields: magic_number (Str), width (Nat), height(Nat),
            maxval (Nat), image (listof Nat)
    requires: 0 <=  maxval  <= 65536
              0 <= image[i] <= maxval  for all i in range(len(image))
    '''
    #YOUR CODE GOES HERE
    
#    with open(file_name,'r') as f:
    f=open(file_name,'r')
    image_data = f.read()
#      print(image_data)
    image_data=image_data.split()
#    self.file_name=file_name
    self.magic_number=image_data[0]
    self.width=int(image_data[1])
    self.height=int(image_data[2])
    self.maxval =int(image_data[3])
    self.image=image_data[4:]
    self.image=[int(i) for i in self.image]
#    self.image= [list(self.image[i:i+3]) for i in range(0, len(self.image), 3)]
#    self.image= [list(self.image[i:i+self.width]) for i in range(0,self.height*self.width,self.width)]
    f.close()

#      print(len(self.image))  
#      print(len(self.image_rbg))
#      print(self.image)  
#      print(self.image_rbg)


  def __eq__(self, other):
    '''
    Returns true if and only if self and other have the same fields
    '''
    return isinstance(other, PPM) and \
           self.magic_number == other.magic_number and\
           self.width == other.width and\
           self.height == other.height and\
           self.maxval == other.maxval and\
           self.image == other.image
  
  def __repr__(self):
    '''
    Returns a string of the image
    '''
    return "Dimensions: ({0.width}, {0.height})\nImage: {0.image}".format(self)  
  
  def zero_out(self):
    #YOUR CODE GOES HERE
    it=2
    while it<len(self.image):
      if self.image[it]%2==0:
        it+=3
      else:
        self.image[it]=self.image[it]-1
        it+=3
    return None
  
#  def zero_in(self):
#    #YOUR CODE GOES HERE
#    for h in range(self.height): 
#      for w in range(self.width):
##        print(self.image[h][w])
#        try:
#          self.image[h][w][2]=self.image[h][w][2]+self.image[h][w][2]%2
#        except Exception as e:
#          print(e)
#          print("zero_in error in ",h,w)
#          pass
#    return None
    
#  def get_grid(self):
#    for h in range(self.height): 
#      for w in range(self.width): 
#          try:
#            if self.image[h][w][2]>0:
#              self.image[h][w][0]=0
#              self.image[h][w][1]=0
#              self.image[h][w][2]=1 
#            else:
#              self.image[h][w][0]=0
#              self.image[h][w][1]=0
#              self.image[h][w][2]=0
#          except Exception as e:
#            print(e)
#            print("getgrid error in ",h,w)
#            pass
#    return None
#  
#  def re_grid(self):
#    for h in range(self.height): 
#      for w in range(self.width):
#          try:
#            if self.image[h][w][2]>0:
#              self.image[h][w][0]=255
#              self.image[h][w][1]=255
#              self.image[h][w][2]=255 
#            else:
#              self.image[h][w][0]=0
#              self.image[h][w][1]=0
#              self.image[h][w][2]=0
#          except Exception as e:
#            print(e)
#            print("regrid error in ",h,w)
#            pass
#    return None
#      
#  def add(self,x):
#    for h in range(self.height): 
#      for w in range(self.width):
#          for c in range(3):  
#            try:
#              self.image[h][w][c]+=x.image[h][w][c]
#            except Exception as e:
#              print(e)
#              print("add error in ",h,w)
#              pass
#    return None
#  
#  def sub(self,x):
#    for h in range(self.height): 
#      for w in range(self.width):
#          for c in range(3):  
#            try:
#              self.image[h][w][c]-=x.image[h][w][c]
#            except Exception as e:
#              print("sub error in ",h,w)
#              print(e)
#              pass
#    return None
#     
#  def flatten(self):
#    ft=[]
#    for h in range(self.height): 
#      for w in range(self.width): 
#        for c in range(3):
#          ft.append(str(self.image[h][w][c]))
#      ft.append("\n")
#    ft.pop()
#    return " ".join(ft)
#  def save_img(self,filename=None):
#    if filename!=None:
#      with open(filename,'w') as f:
#        print(self.magic_number,file=f)
#        print(self.width,self.height,file=f)
#        print(self.maxval,file=f)
#        print(self.flatten(),file=f)
def save_img(ppm,filename=None):
  if filename!=None:
    f= open(filename,'w')
    f.write(ppm.magic_number)
    f.write("\n")
    f.writelines(str(ppm.width)+" "+str(ppm.height))
    f.write("\n")
    f.writelines(str(ppm.maxval))
    f.write("\n")
    img=[str(i)+" " for i in ppm.image]
    f.writelines(img)
if __name__=="__main__":      
  img_path="../Beaver_Set/treasure.ppm"
  out_path="../out.ppm"
  test_img=PPM(img_path)
#  print(test_img)
#  print(len(test_img.image))
  test_img.zero_out()
  save_img(test_img,out_path)
#  print(test_img)
#  print(test_img.height*test_img.width*3,len(test_img.image))

#  test_img.save_img("../test.ppm")
