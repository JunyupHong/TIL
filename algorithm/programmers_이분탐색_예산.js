function solution(budgets, M) {
    let max = Math.max(...budgets);
    let min = 1;

    while (min < max) {
        const mid = Math.floor((max + min + 1) / 2);
        budgets.map(b => Math.min(b, mid)).reduce((acc, cur) => acc + cur) <= M ? min = mid : max = mid - 1;
    }
    
    return min;
}
