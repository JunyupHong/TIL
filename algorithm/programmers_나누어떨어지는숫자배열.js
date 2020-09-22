function solution(arr, divisor) {
    const answer = arr.filter(num => num % divisor === 0).sort((a, b) => a - b);
    if (answer.length === 0) return [-1];
	return answer;
}
