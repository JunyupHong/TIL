function solution(number, k) {
    const result = [];
    let leftCount = k;
    const numbs = [...number];
    
    result.push(numbs[0]);
    for (let i = 1; i < numbs.length; i++) {
        const n = numbs[i];
        while (leftCount > 0 && n > result[result.length - 1]) {
            result.pop();
            leftCount--;
        }
        result.push(n);
    }
    
    if (leftCount > 0) result.splice(result.length - leftCount, leftCount);
    return result.join('');
}


/** Timeout이 나는 코드*/
//function solution(number, k) {
//    const numbs = [...number];
//    let idx = 0;
//    let count = 0;
//    while (count !== k) {
//        const curIdx = idx % number.length;
//        const nextIdx = (idx + 1) % number.length;
//        if (numbs[curIdx] < numbs[nextIdx]) {
//            numbs.splice(curIdx, 1);
//            count++;
//            idx--;
//        } else idx++;
//    }
//    return numbs.join('');    
//}
