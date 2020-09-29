from sys import stdin
from collections import deque

string = stdin.readline().rstrip()
left = deque(string)
right = deque()

command_count = int(stdin.readline())

for _ in range(command_count):
	command = stdin.readline().split()
	if command[0] == 'L':
		if left: right.appendleft(left.pop())
	elif command[0] == 'D':
		if right: left.append(right.popleft())
	elif command[0] == 'B':
		if left: left.pop()
	elif command[0] == 'P':
		left.append(command[1])
print("".join(left+right))

'''
# 시간초과
from sys import stdin

string = stdin.readline().rstrip()
command_count = int(stdin.readline())

cur_pos = len(string)
for _ in range(command_count):
	command = stdin.readline().split()
	if command[0] == 'L':
		if cur_pos > 0: cur_pos -= 1
	elif command[0] == 'D':
		if cur_pos < len(string): cur_pos += 1
	elif command[0] == 'B':
		if cur_pos > 0:
			string = string[:cur_pos - 1] + string[cur_pos:]
			cur_pos -= 1
	elif command[0] == 'P':
		string = string[:cur_pos] + command[1] + string[cur_pos:]
		cur_pos += 1
print(string)
'''

