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

# 두번째
from collections import deque

def solution(board, moves):
    answer = 0
    answer_stack = []
    board_stack = []
    
    # board를 column 기준으로 2차원 배열 만들기
    for i in range(len(board)):
        board_stack.append([])
        for j in range(len(board[0]) - 1, -1, -1):
            if board[j][i] != 0: board_stack[i].append(board[j][i])
    
    # moves에서 인형 뽑기
    for n in moves:
        if len(board_stack[n - 1]) > 0:
            item = board_stack[n - 1].pop()
            if len(answer_stack) > 0 and answer_stack[-1] == item:
                answer_stack.pop();
                answer += 2
            else:
                answer_stack.append(item)
    
    return answer
