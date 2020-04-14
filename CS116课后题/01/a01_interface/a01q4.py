import math
def outer_radius(r, n):
  #YOUR CODE GOES HERE
  theta=math.pi*2/n/2
  ans=(r*math.sin(theta))/(1-math.sin(theta))
  return ans
#print(outer_radius(1.0 , 6))
print(outer_radius(2.0 , 4))
  
  