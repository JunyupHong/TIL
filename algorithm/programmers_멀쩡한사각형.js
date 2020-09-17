function solution(w, h) {
    const gcd = getGCD(Math.max(w, h), Math.min(w, h));
    return w * h - ((w + h) / gcd - 1) * gcd;
}

// 유클리드 호제법
function getGCD(max, min) {
    return min === 0 ? max : getGCD(min, max % min);
}
