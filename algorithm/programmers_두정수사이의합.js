function solution(a, b) {
    const min = Math.min(a, b);
    const max = Math.max(a, b);
    let answer = min;
    for(let i = min + 1; i <= max; i++) {
        answer += i;
    }
    return answer;
}
