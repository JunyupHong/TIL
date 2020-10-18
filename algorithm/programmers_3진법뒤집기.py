def solution(n):
    answer = 0
    num = n
    base3 = ""
    while (num > 0):
        base3 += str(num % 3)
        num = num // 3 # 몫 구하기
    for idx, base in enumerate(base3):
        answer += 3 ** (len(base3) - idx - 1) * int(base)
    return answer
