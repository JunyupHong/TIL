function solution(number, k) {
    const numbs = [...number];
    let idx = 0;
    let count = 0;
    while (count !== k) {
        const curIdx = idx % number.length;
        const nextIdx = (idx + 1) % number.length;
        if (numbs[curIdx] < numbs[nextIdx]) {
            numbs.splice(curIdx, 1);
            count++;
            idx--;
        } else idx++;
    }
    return numbs.join('');
    
//     const stack = [];
//     let count = 0;

//     while (numbs.length > 0) {
//         stack.push(numbs.shift());
//         while(stack[stack.length-1] < numbs[0]) {
//             count++;
//             stack.pop();
//             if (count === k) return stack.concat(numbs).join('');
//         }
//     }
    
//     return stack.slice(0, stack.length - k).join('');
}
