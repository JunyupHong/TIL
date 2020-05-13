function solution(N) {
    if (N === 1) return 4;
    const arr = [1, 1];
    for (let i = 2; i < N; i++) arr.push(arr[i - 1] + arr[i - 2]);
    return 4 * arr[N-1] + 2 * arr[N-2];
}
