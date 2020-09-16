function solution(n) {
    let answer = '';
    let num = n;
    while (num > 0) {
        const quotient = num % 3;
        num = Math.floor(num / 3);
        if (quotient === 0) {
            answer = '4' + answer;
            num -= 1;
        } else answer = quotient + answer;
    }
    return answer;
}
