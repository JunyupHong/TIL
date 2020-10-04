from sys import stdin

string = stdin.readline().rstrip()

alphabet_set = ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z']

counts = []
for alphabet in alphabet_set:
	counts.append(str(string.find(alphabet)))

print(" ".join(counts))
