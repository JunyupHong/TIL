function solution(n, m, k) {	
	let answer = 0;
	const people = [];
	for (let i = 0; i < n; i++) {
		people.push(0);
	}
	
	let bomb = 0;
	while(1) {
		if (++people[bomb] === m) break;
		if (people[bomb] % 2 === 0) bomb = (bomb + k) % n;
		else bomb = bomb >= k ? bomb - k : n - k + bomb;
		answer++;
	}

	return answer;
}


console.log(solution(3, 3, 2));
