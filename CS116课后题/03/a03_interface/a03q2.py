def is_anagram(s, t):
  if sorted(s)==sorted(t):return True
  else:return False

s="stay"
t="team"
print(is_anagram(s, t))