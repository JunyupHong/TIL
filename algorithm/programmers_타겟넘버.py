def solution(numbers, target):
    cur_sum = numbers.pop()
    return dfs(target, cur_sum, numbers) + dfs(target, -cur_sum, numbers)

def dfs(target, cur_sum, left_numbers):
    if len(left_numbers) == 0:
        return 1 if cur_sum == target else 0
    else:
        new_numbers = left_numbers[:]
        num = new_numbers.pop()
        return dfs(target, cur_sum + num, new_numbers) + dfs(target, cur_sum - num, new_numbers)
