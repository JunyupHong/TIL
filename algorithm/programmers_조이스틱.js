function solution(name) {
    let answer = 0;
    const chars = name.split("");
    let idx = 0;
    
    while (true) {
        const first = chars.findIndex(c => c !== "A");
        const last = chars.length - chars.reverse().findIndex(c => c !== "A") - 1;
        chars.reverse();
        if (first === -1) break;
        
        if (Math.abs(first - idx) <= Math.abs(chars.length - last + idx)) {
            answer += Math.abs(first - idx);
            idx = first;
        } else {
            
            answer += Math.abs(chars.length - last + idx);
            idx = last;
        }
        answer += Math.min( chars[idx].charCodeAt(0) - "A".charCodeAt(0),
                           "Z".charCodeAt(0) - chars[idx].charCodeAt(0) + 1 );
        chars[idx] = "A";
    }
    return answer;
}
