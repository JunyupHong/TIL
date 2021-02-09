function solution(n, computers) {
    let result = 0;
    const isVisited = Array.from({length: n}, () => false);
    
    const queue = [];
    while (isVisited.some(visit => !visit)) {
        if (queue.length === 0) {
            queue.push(isVisited.findIndex(item => !item));
            result++;
        }
        
        const comIdx = queue.shift();
        isVisited[comIdx] = true;
        
        computers[comIdx].forEach((com, i) => {
            if (com === 1 && i !== comIdx) {
                if (!isVisited[i]) queue.push(i);
            }
        })
    }
    return result;
}
