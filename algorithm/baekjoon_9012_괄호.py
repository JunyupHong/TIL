from sys import stdin

input_num = int(stdin.readline())

for _ in range(input_num):
	stack = []
	end = True
	for char in stdin.readline():
		if char == '(':
			stack.append(char)
		elif char == ')':
			if len(stack) == 0:
				print("NO")
				end = False
				break
			else: stack.pop()
	if end:
		if len(stack) == 0: print("YES")
		else: print("NO")


# -------
# from sys import stdin
# from collections import deque

# stdin.readline()

# for string in stdin:
# 	queue = deque(string)
# 	open_count = 0
# 	while queue:
# 		char = queue.popleft()
# 		if char == '(':
# 			open_count += 1
# 		elif char == ')':
# 			if open_count == 0:
# 				print("NO")
# 				break
# 			else: open_count -= 1
# 	if len(queue) == 0:
# 		if open_count == 0: print("YES")
# 		else: print("NO")