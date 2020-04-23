function solution(p) {
    let answer = '';

    if(isRight(p)) return p;
    answer = recursive(p);
    
    return answer;
}

function isRight(str) {
    const arr = str.split('');
    let count = 0;
    while(arr.length > 0) {
        if(arr.shift() === '(') count++;
        else count--;
        if(count < 0) return false;
    }
    return count === 0;
}

function recursive(str) {
    if(str === '') return '';
    let lcount = 0;
    let rcount = 0;
    for(let i = 0; i < str.length; i++) {
        if(str[i] === '(') lcount++;
        else if(str[i] === ')') rcount++;

        if(lcount === rcount) {
            const u = str.slice(0, i+1);
            const v = str.slice(i+1);
            if(isRight(u)) {
                const resultV = recursive(v);
                return u + resultV;
            } else {
                const resultV = recursive(v);
                const end = u.slice(1, u.length - 1).split('')
                                .map(s => s === '(' ? ')' : '(').join('');
                return '(' + resultV + ')' + end;
            } 
        }
    }
}
