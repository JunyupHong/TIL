function solution(n) {
    let answer = '';
    for (let i = 0; i < Math.floor(n/2); i++) {
        answer += '수박';
    }
    if (n % 2 === 1) answer += '수';
    return answer;
}
