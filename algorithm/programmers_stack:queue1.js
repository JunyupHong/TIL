function solution(heights) {
    const answer = [0];
    for (let i = 0; i < heights.length; i++) {
        const stack = heights.slice(0, i + 1);
        const signal = stack.pop();
        while (stack.length) {
            const h = stack.pop();
            if (h > signal) {
                answer.push(stack.length + 1);
                break;
            }
            if(stack.length === 0) answer.push(0);
        }
    	// const signal = heights[i];
    	// if (i === 0) {
    	// 	answer.push(0); // 0번 인덱스 신호는 받을 수 없음
    	// 	continue;
    	// }
    	// for (let j = i - 1; j >= 0; j--) {
    	// 	if (heights[j] > signal) {
    	// 		answer.push(j + 1);
    	// 		break;
    	// 	} else if (j === 0) answer.push(0);
    	// }
    }

    return answer;
}
