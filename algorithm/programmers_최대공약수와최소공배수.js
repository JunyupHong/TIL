function solution(n, m) {
    let gcd = Math.min(n, m);
    let lcm = Math.max(n, m);
    while (n % gcd !== 0 || m % gcd !== 0) gcd--;
    while (lcm % n !== 0 || lcm % m !== 0) lcm++;
    return [gcd, lcm];
}
