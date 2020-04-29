/* 这些串能识别,一共识别出4个string */
"string1"

"string2\\" "string3\""

"string4\n\'\0\a"

//这些串不能识别，比如不能将string5\识别为字符串，也不能将string6”识别为字符串，string7也不会被识别为字符串，不能将string8'识别为字符串

"string5\"

"string6""

"string7
"

"string8'"