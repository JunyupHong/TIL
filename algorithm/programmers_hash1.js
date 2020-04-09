function solution (participant, completion) {
	var answer = 'a';
	const comp_hash = {};
	completion.forEach((c, i) => {
		if(comp_hash[c]) comp_hash[c]++;
		else comp_hash[c] = 1;
	});
	
//	participant.forEach((p, i) => {
//		if(comp_hash[p] === 0) {
//			answer = comp_hash[p];
//			break;
//		} else {
//			comp_hash--;
//		}
//	});

	for(let i = 0; i < participant.length; i++) {
		if(comp_hash[participant[i]] === 0) {
			answer = participant[i];
			break;
		} else {
			comp_hash[participant[i]]--;
		}
	}	
	return answer;
}

const part = ['a','a', 'b', 'c'];
const com = ['a', 'b','c'];
console.log(solution(part, com));
