function solution(customer, k) {
	const set = new Set();
	customer.forEach(c => {
		if (c[1] === 1) set.add(c[0]);
		else set.delete(c[0]);
	});

	return [...set].slice(0, k).sort((a,b) => a - b);
}

console.log(solution([[1,1], [2,1], [3,1], [2,0], [2,1]], 2));
console.log(solution([[2,1], [1,1], [3,1], [1,0], [1,1], [2,0], [2,1]], 1));
