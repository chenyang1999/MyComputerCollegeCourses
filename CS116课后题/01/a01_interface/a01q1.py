import math
def brine(num_eggs, egg_radius, jar_radius, jar_height):
  #YOUR CODE GOES HERE
  egg_v=num_eggs*(4/3)*(egg_radius**3)*math.pi
  v=jar_radius**2*math.pi*jar_height
  ans=v-egg_v
  return ans
print(brine(20, 2.0, 5.25, 100.0))