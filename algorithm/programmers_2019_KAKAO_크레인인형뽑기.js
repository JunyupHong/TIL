function solution(board, moves) {
    let answer = 0;
    const map = [];
    const stack = [];

    for (let i = 0 ; i < board[0].length; i++) {
        const arr = [];
        for (let b of board) {
            if(b[i] !== 0) arr.push(b[i]);
        }
        map[i] = arr;
    }
    for (let m of moves) {
        const pick = map[m - 1].shift();

        if (!pick) continue;
        if (pick !== stack[stack.length - 1]) stack.push(pick);
        else {
            stack.pop();
            answer += 2;
        }
    }
    
    return answer;
}
