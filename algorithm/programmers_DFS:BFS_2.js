function solution(n, computers) {
    let answer = 0;
    const check = computers.map(() => 0);
    for (let i = 0; i < n; i++) {
        if(!check[i]) {
            answer++;
            // bfs
            const queue = [i];
            while (queue.length > 0) {
                const idx = queue.shift();
                check[idx] = 1;
                for(let j = 0; j < n; j++) {
                    if (!check[j] && computers[j][idx]) queue.push(j);
                    
                }
            }
        }        
    }
    return answer;
}
