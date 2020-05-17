def solution(n, lost, reserve):
    answer = 0
    lostIdx = set(lost) - set(reserve)
    reserveIdx = set(reserve) - set(lost)
    
    temp = set([])
    for l in lostIdx:
        if l - 1 in reserveIdx:
            temp.add(l)
            reserveIdx.remove(l-1)
        elif l + 1 in reserveIdx:
            temp.add(l)
            reserveIdx.remove(l+1)
    lostIdx -= temp
    
    return n - len(lostIdx)
