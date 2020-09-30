function solution(s) {
    return [...s].sort((a, b) => a > b ? -1 : a > b ? 1 : 0).join('');
}
