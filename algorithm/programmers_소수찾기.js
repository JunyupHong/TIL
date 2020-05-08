function solution(n) {
    const isPrime = [false];
    for (let i = 2; i <= n; i++) {
        isPrime.push(true);
    }
    for (let i = 2; i * i <= n; i++) {
        if (!isPrime[i - 1]) continue;
        for (let j = 2 * i; j <= n; j += i) {
            isPrime[j - 1] = false;
        }
    }
    
    return isPrime.filter(p => p).length;
}
