function solution(n, computers) {
    // dfs - stack
//     let answer = 0;
//     const visited = [];
//     for (let i = 0; i < n; i++) {
//         visited.push(false);
//     }
    
//     for (let i = 0; i < n; i++) {
//         if (visited[i]) continue;
//         visited[i] = true;
//         answer++;
//         const stack = [i];
//         while (stack.length) {
//             const connection = computers[stack.pop()];
//             connection.forEach((c, idx) => {
//                 if(c && !visited[idx]) {
//                     stack.push(idx);
//                     visited[idx] = true;
//                 }
//             });
//         }
//     }
//     return answer;
    
    
    
    
    // dfs - recursion
    let answer = 0;
    const visited = [];
    for (let i = 0; i < n; i++) {
        visited.push(false);
    }
    function dfs(com, i) {
        if (visited[i]) return;
        visited[i] = true;
        com[i].forEach((connection, idx) => {
            if (connection) {
                dfs(com, idx);
            }
        });
    }
    
    for (let i = 0; i < n; i++) {
        if(!visited[i]) answer++;
        dfs(computers, i);
    }
    return answer;
    
    
    
    
    
    // bfs
//     let answer = 0;
//     const check = computers.map(() => 0);
//     for (let i = 0; i < n; i++) {
//         if(!check[i]) {
//             answer++;
//             // bfs
//             const queue = [i];
//             while (queue.length > 0) {
//                 const idx = queue.shift();
//                 check[idx] = 1;
//                 for(let j = 0; j < n; j++) {
//                     if (!check[j] && computers[j][idx]) queue.push(j);
                    
//                 }
//             }
//         }        
//     }
//     return answer;
}
