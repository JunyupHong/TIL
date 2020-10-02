from sys import stdin

input_str = stdin.readline()

stick = 0
current_stick = 0

for i, char in enumerate(input_str[0:-1]):
	if char == '(':
		next_char = input_str[i + 1]
		if next_char == ')': # 레이저
			stick += current_stick
		else: # 막대
			stick += 1
			current_stick += 1
	else:
		prev_char = input_str[i - 1]
		if prev_char == ')': #레이저
			current_stick -= 1

print(stick)

