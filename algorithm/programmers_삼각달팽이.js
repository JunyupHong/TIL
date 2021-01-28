function solution(n) {
    const map = [];
    for (let i = 0; i < n; i++) map.push(new Array(i+1));
    const move = [[1, 0], [0, 1], [-1, -1]];
    let number = 0;
    let count = n;
    
    let moveIdx = 0;
    let x = -1;
    let y = 0;
    while (count > 0) {
        for (let i = 0; i < count; i++) {
            x += move[moveIdx][0];
            y += move[moveIdx][1];
            map[x][y] = ++number;
        }
        moveIdx++;
        moveIdx %= 3;
        count--;
    }

    return map.flat();
}
