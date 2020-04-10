function solution(heights) {
    const answer = [];
	const stack = heights.slice(); // 깊은 복사

    for (let i = heights.length - 1; i >= 0; i--) {		
		const last = stack.pop();
		console.log(last);
    	while(1) {
    		let val = stack.pop();
    		if(val === undefined) {
    			answer.push(0);
    			break;
    		}
    		if(val > last) {
    			answer.push(i);
    			break;
    		}
    	}
    }
    answer.reverse();
    return answer;
}