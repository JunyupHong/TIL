function solution(n, words) {
    const answer = [0, 0];
    const answerSet = new Set();
    answerSet.add(words[0]);
    for (let i = 1; i < words.length; i++) {
        const prev = words[i - 1];
        const cur = words[i];        
        if (answerSet.has(cur) || prev[prev.length - 1] !== cur[0]) {
            return [i % n + 1, Math.ceil((i+1) / n)];
        }
        answerSet.add(cur);
    }

    return answer;
}
