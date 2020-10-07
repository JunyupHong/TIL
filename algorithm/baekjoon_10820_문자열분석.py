from sys import stdin

for string in stdin:
	lower = 0
	upper = 0
	number = 0
	space = 0
	for char in string:
		ascii_num = ord(char)
		if 48 <= ascii_num <= 57: number += 1
		elif 65 <= ascii_num <= 91: upper += 1
		elif 97 <= ascii_num <= 123: lower += 1
		elif ascii_num == 32: space += 1
	print(lower, upper, number, space)
