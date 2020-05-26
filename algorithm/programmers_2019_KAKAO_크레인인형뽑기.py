def solution(board, moves):
    answer = 0
    stack = []
    boardMap = []
    for i in range(len(board)):
        arr = []
        for j in range(len(board)):
            if board[j][i] != 0:
                arr.append(board[j][i])
        boardMap.append(arr)
    
    for m in moves:
        if len(boardMap[m-1]) == 0:
            continue
        pick = boardMap[m-1].pop(0)
        if len(stack) == 0:
            stack.append(pick)
        else :
            if pick == stack[len(stack)-1]:
                stack.pop(len(stack)-1)
                answer += 2
            else:
                stack.append(pick)
    
    return answer
