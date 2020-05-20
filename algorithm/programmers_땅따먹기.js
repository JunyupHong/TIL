function solution(land) {
    const values = [...land];
    for (let i = 1; i < values.length; i++) {
        const row = [...values[i-1]];
        for (let j = 0; j < 4; j++) {
            values[i][j] += Math.max(...row.slice(0, j), ...row.slice(j+1));
        }
    }
    return Math.max(...values[values.length-1]);
}
