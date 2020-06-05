from collections import deque

def solution(prices):
    answer = []
    dq = deque(prices)
    while(len(dq) > 0):
        p = dq.popleft()
        time = 0
        for price in dq:
            time += 1
            if (p > price):
                break
        answer.append(time)
    return answer
