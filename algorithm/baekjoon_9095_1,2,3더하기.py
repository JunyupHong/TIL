for _ in range(int(input())):
	n = int(input())

	count = 0
	arr = list(filter(lambda ele: ele <= n, [1, 2, 3]))
	
	while True:
		count += arr.count(n)
		arr = list(filter(lambda ele: ele != n, arr))
		
		temp = []
		for num in arr:
			if num + 1 <= n:
				temp.append(num+1)
			if num + 2 <= n:
				temp.append(num+2)
			if num + 3 <= n:
				temp.append(num+3)
		arr = temp
		if len(arr) == 0: break

	print(count)

