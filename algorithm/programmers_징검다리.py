from functools import reduce

def solution(distance, rocks, n):
    rocks.append(distance)
    rocks.sort()

    start, end = 0, distance
    mid = 0
    
    while start <= end:
        mid = (start + end) // 2
        remove_count = 0
        prev = 0
        for i, cur in enumerate(rocks):
            if cur - prev < mid:
                remove_count += 1
            else:
                prev = cur
        if remove_count > n:
            end = mid - 1
        else:
            start = mid + 1

    return end
        
        
