function solution(truck, w) {
	const answer = [];
	const trucks = new Map();
	truck.forEach((t, i) => trucks.set(i + 1, t));

	const weights = [...w];

	while(weights.length > 0) {
		const weight = weights.shift();
		const minWeight = Math.min(...weights);
		if (Math.max(...trucks.values()) < weight) answer.push(-1);
		else {
			for (let [i, v] of trucks) {
				if (v >= weight) {
				trucks.set(i, trucks.get(i) - weight);
				answer.push(i);
				if (minWeight > trucks.get(i)) trucks.delete(i);
				break;
				}
			}
		}
	}

	/*
	w.forEach(weight => {
		if (![...trucks.values()].some(t => t >= weight)) answer.push(-1);
		else {
			for (let [i, v] of trucks) {
				if (v < weight) continue;
				trucks.set(i, trucks.get(i) - weight);
				answer.push(i);
				break;
			}
		}
	});
*/
	return answer;
}

console.log(solution([1,4,5,2,4], [2,4,4,3,2]));
console.log(solution([2,7,4,9], [7,6,8]));
