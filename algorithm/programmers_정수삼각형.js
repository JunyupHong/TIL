function solution(triangle) {
    const values = [...triangle];
    for (let i = 1; i < values.length; i++) {
        for (let j = 0; j < values[i].length; j++) {
            if (j === 0) values[i][j] += values[i-1][j];
            else if (j === values[i].length - 1) values[i][j] += values[i-1][j-1];
            else values[i][j] += Math.max(values[i-1][j-1], values[i-1][j]);
        }
    }
    return Math.max(...values[values.length - 1]);
}
