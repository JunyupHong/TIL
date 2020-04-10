// function solution(heights) {
//     const answer = [];
// 	const stack = heights.slice(); // 깊은 복사
// 	let right;
// 	while (right = stack.pop()) {
// 		if(stack.length === 0) answer.push(0); // 1번 인덱스 신호는 아무도 받을 수 없음
// 		for(let i = stack.length - 1; i >= 0; i--) {
// 			if(stack[i] > right) {
// 				answer.push(i + 1); // index가 아닌 n번째이므로 +1 해줌
// 				break;
// 			} else if(i === 0) answer.push(0); // 아무도 받지않음
// 		}
// 	}
//     answer.reverse();
//     return answer;
// }

function solution(heights) {
    const answer = [];
    for(let i = 0; i < heights.length; i++) {
    	const right = heights[i];
    	if (i === 0) {
    		answer.push(0); // 0번 인덱스 신호는 받을 수 없음
    		continue;
    	}
    	for(let j = i - 1; j >= 0; j--) {
    		if(heights[j] > right) {
    			answer.push(j + 1);
    			break;
    		} else if(j === 0) answer.push(0);
    	}
    }

    return answer;
}