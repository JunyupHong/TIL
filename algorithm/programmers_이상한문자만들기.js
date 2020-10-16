function solution(s) {
    const words = s.split(' ');
    let answer = '';
    words.forEach(w => {
        for(let i = 0; i < w.length; i++) {
            if (i % 2 === 0) answer += w[i].toUpperCase();
            else answer += w[i].toLowerCase();
        }
        answer += ' ';
    });
    return answer.slice(0, answer.length - 1);
}
