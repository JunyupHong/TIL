def solution(citations):
    citations.sort(reverse=True)
    for h in range(citations[0], 0, -1):
        if len(list(filter(lambda citation: citation >= h, citations))) >= h: return h
    return 0
