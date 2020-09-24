from sys import stdin
from collections import deque

input_str = stdin.readline().split()
k = int(input_str[1])
people = deque([i+1 for i in range(int(input_str[0]))])
answer = []
count = 1
while people:
	if count % k != 0: people.append(people.popleft())
	else: answer.append(people.popleft())
	count += 1

print("<" + ", ".join(map(str, answer)) + ">")
