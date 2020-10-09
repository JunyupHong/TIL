from sys import stdin

string = stdin.readline().rstrip()
answer = ""
for char in string:
	ascii_code = ord(char)
	if 65 <= ascii_code <= 90:
		if ascii_code + 13 > 90: answer += chr((ascii_code + 13) % 91 + 65)
		else: answer += chr(ascii_code + 13)
	elif 97 <= ascii_code <= 122:
		if ascii_code + 13 > 122: answer += chr((ascii_code + 13) % 123 + 97)
		else: answer += chr(ascii_code + 13)
	else:
		answer += char
print(answer)

