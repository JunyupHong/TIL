from functools import reduce
def solution(n, times):
    start, end = 0, min(times) * n
    mid = (start + end) // 2
    answer = min(times) * n
    while start <= end:
        mid = (start + end) // 2
        person = reduce(lambda acc, cur: acc + (mid // cur), times, 0)
        if person >= n:
            end = mid - 1
            if answer > mid: answer = mid
        else:
            start = mid + 1
    return answer
