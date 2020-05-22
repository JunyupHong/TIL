function solution(s) {
    const len = s.length;
    if (len % 2) return s[Math.floor(len/2)];
    else return s[Math.floor(len/2) - 1]+s[Math.floor(len/2)];
}
