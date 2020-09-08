function solution(routes) {
	let answer = 1;
    let cameraCoverage = routes[0][1];
    
	routes.sort((a, b) => a[0] - b[0]);
    
	for (let i = 0; i < routes.length - 1; i++) {
        if (cameraCoverage >= routes[i+1][0]) cameraCoverage = Math.min(cameraCoverage, routes[i+1][1]);
        else {
            answer++;
            cameraCoverage = routes[i+1][1];
        }
    }
    return answer;
}
