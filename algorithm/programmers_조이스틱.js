function solution(name) {
    let answer = 0;
    const chars = name.split("");
    let idx = 0;
    while (true) {
        const first = chars.findIndex(c => c !== "A");
        const last = chars.length - chars.reverse().findIndex(c => c !== "A") - 1;
        chars.reverse();
        if (first === -1) break;

        if (Math.abs(idx - first) > Math.abs(chars.length - last + idx)) {
            idx = last;
            answer += Math.abs(chars.length - last + idx);
        } else {
            idx = first;
            answer += Math.abs(idx - first);
        }
        answer += chars[idx].charCodeAt(0) - 65;
        console.log(answer);
        chars[idx] = "A";
    }
    
    return answer;
}
