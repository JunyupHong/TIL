function solution(begin, target, words) {
    let answer = 0;
    const wordLen = begin.length;

    const check = words.map(() => 0);
    const queue = [{word: begin, count: 0}];
    while(queue.length) {
        const w = queue.shift();
        if (w.word === target) return w.count;
        check[words.indexOf(w.word)] = 1;
        for (let i = 0; i < words.length; i++) {
            if (check[i]) continue;
            for(let j = 0; j < wordLen; j++) {
                if (w.word.slice(0, j) + w.word.slice(j+1) 
                    === words[i].slice(0, j) + words[i].slice(j+1)) {
                    queue.push({word: words[i], count: w.count+1});
                }
            }
        }
    }

    return answer;
}
