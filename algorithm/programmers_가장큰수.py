def solution(numbers):
    if len(numbers) == numbers.count(0): return '0'
    return "".join(sorted(list(map(str, numbers)), key=lambda number: number*3, reverse=True))
