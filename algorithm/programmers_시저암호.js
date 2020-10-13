function solution(s, n) {
    let answer = '';
    [...s].forEach(char => {
        if (char === ' ') answer += " ";
        else {
            const charCode = char.charCodeAt(0);
            answer += String.fromCharCode(charCode > 90
                                          ? (charCode + n - 97) % 26 + 97 
                                          : (charCode + n - 65) % 26 + 65
                                         );
        }
    });
    
    return answer;
}
