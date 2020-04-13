function solution(progresses, speeds) {
    const answer = [];
    let progressStack = progresses.slice();
    const speedStack = speeds.slice();
    
	while (progressStack.length > 0) {
		progressStack = progressStack.map((p, i) => p += speedStack[i]);

		let hostCount = 0;
		while (progressStack[0] >= 100) {
			progressStack.shift();
			speedStack.shift();
			hostCount++;
		}
		if (hostCount > 0) answer.push(hostCount);
	}

    return answer;
}