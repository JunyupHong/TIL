import math

def solution(n, words):
    answerSet = set([words[0]])
    
    for idx in range(len(words)):
        if idx == 0:
            continue
        prev = words[idx - 1]
        cur = words[idx]
        if prev[len(prev) - 1] != cur[0] or cur in answerSet:
            return [idx % n + 1, math.ceil((idx + 1) / n)]
        answerSet.add(cur)
        
    return [0, 0]
