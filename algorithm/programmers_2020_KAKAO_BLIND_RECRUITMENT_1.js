function solution(s) {
    let answer = s.length;
    const resultArr = [];
    for (let i = 1 ; i <= s.length / 2; i++) {
        const arr = s.split('');
        let prevStr = '';
        let count = 1;
        for (let j = 0; true; j += i) {
            const curStr = s.slice(j, j+i);   
            if(prevStr === curStr) {
                count++;
            } else {
                if(!resultArr[i-1]) resultArr[i-1] = '';
                if(count > 1) resultArr[i-1] += prevStr+count;
                else {
                    resultArr[i-1] += prevStr;
                }
                count = 1;
                prevStr = curStr;
            }
            if(!curStr) break;
        }
    }
    resultArr.forEach(r => {
        if(r.length < answer) answer = r.length;
    });
    return answer;
}
