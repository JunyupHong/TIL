function solution(n, times) {
    let right = Math.max(...times) * n;
    let left = 1;
    let middle;
    while (right > left) {
        middle = Math.floor((right + left) / 2);
        times.reduce((acc, cur) => acc + Math.floor(middle / cur), 0) < n ? left = middle + 1 : right = middle;
    }

    return left;
}

