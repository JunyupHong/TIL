def solution(numbers):
    num_set = set()
    
    for i, n in enumerate(numbers):
        for next_n in numbers[i+1:]:
            num_set.add(n + next_n)
    answer = list(num_set)
    answer.sort()
    return answer
