from sys import stdin
from collections import deque

N, M, V = map(int, stdin.readline().split())

edges = set()
for _ in range(M):
	edges.add(tuple(map(int, stdin.readline().split())))

visited = set([V])

dfs_stack = []
bfs_queue = deque()

print(edges)

