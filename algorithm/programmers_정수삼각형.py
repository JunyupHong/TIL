def solution(triangle):
    for height in range(1, len(triangle)):
        length = len(triangle[height])
        for i in range(length):
            triangle[height][i] += max(triangle[height-1][i-1 if i != 0 else 0], triangle[height-1][i if i != length-1 else -1])

    return max(triangle[-1])
