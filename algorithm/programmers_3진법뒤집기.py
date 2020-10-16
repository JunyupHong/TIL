def solution(n):
    answer = 0
    num = n
    base3 = ""
    while (num > 0):
        base3 += str(num % 3)
        num = num // 3 # 몫 구하기

    for idx in range(len(base3)): answer += (3 ** idx) * int(base3[-1 * (idx+1)])
    return answer


# def solution(n):
#     answer = 0
#     num = n
#     base3 = ""
#     while (num > 0):
#         base3 += str(num % 3)
#         num = num // 3 # 몫 구하기
#     for idx, val in enumerate(base3):
#         answer += 3 ** (len(base3) - idx - 1) * int(val)
#     return answer

