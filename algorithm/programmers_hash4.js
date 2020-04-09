function solution(genres, plays) {
	const answer = [];
	const data = {};

	genres.forEach((g, i) => {		
		if (!data[g]) {
			data[g] = {
				count: 0,
				list: []
			};
		}
		data[g].count += plays[i];
		data[g].list.push({
			index: i,
			count: plays[i]
		});
	});

	Object.values(data).sort((a, b) => {
		return b.count - a.count;
	}).forEach((v, i) => {
		// data가 하나일때 
		if (v.list.length === 1) answer.push(v.list[0].index);
		else {
			// sorting 했으므로 count가 같은 경우 index 내림차순도 자동 구현
			v.list.sort((a, b) => { return b.count - a.count });
			answer.push(v.list[0].index);
			answer.push(v.list[1].index);
		}
	})

	return answer;
}

