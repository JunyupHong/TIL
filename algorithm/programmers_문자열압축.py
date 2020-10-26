from functools import reduce

def solution(s):
    answer = len(s)
    length = answer
    
    for n in range(1, length+1):
        divided_str = list(filter(lambda ele: ele != '', [s[n*i : n*(i+1)] for i in range(length//n + 1)]))
        result = [divided_str[0]]
        count = 1
        for i in range(1, len(divided_str)):
            if result[-1] == divided_str[i]: count += 1
            else:
                result.extend([count, divided_str[i]])
                count = 1
        result.append(count)
        answer = min(answer, sum(list(map(lambda ele: len(str(ele)), list(filter(lambda ele: ele != 1, result))))))

    return answer
