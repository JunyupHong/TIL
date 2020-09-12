from sys import stdin

stack = []
command_num = int(stdin.readline())

for input_str in stdin:
	command = input_str.split()
	if command[0] == 'push': stack.append(command[-1])
	elif command[0] == 'pop':
		if stack: print(stack.pop())
		else: print(-1)
	elif command[0] == 'top':
		if stack: print(stack[-1])
		else: print(-1)
	elif command[0] == 'size': print(len(stack))
	elif command[0] == 'empty': print(0 if stack else 1)

