function solution(people, limit) {
    let answer = 0;
    const sorted = people.sort((a, b) => b - a);
    while (sorted.length > 0) {
        if ( sorted.shift() + sorted[sorted.length-1] <= limit ) sorted.pop();
        answer++;
    }
    return answer;
}
