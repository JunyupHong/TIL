function solution(arrangement) {
    let answer = 0;
    let stickCount = 0;
    for (let i = 0; i < arrangement.length - 1; i++) {
        if (arrangement[i] === '(' && arrangement[i+1] === ')' ) {
            // laser
            answer += stickCount;
            i++;
        } else if (arrangement[i] === '(' && arrangement[i+1] === '(') {
            // stick 시작
            stickCount++;
            answer++;
        } else if (arrangement[i] === ')' && arrangement[i-1] === ')') {
            stickCount--;
        }
    }
    return answer;
}
