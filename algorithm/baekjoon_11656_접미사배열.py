string = input()
answers = []
for i in range(len(string)):
	answers.append(string[i:])

answers.sort()
for answer in answers:
	print(answer)
