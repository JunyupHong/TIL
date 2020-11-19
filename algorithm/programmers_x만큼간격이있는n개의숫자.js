function solution(x, n) {
    return [...Array(n).keys()].map(num => (num + 1) * x);
}
