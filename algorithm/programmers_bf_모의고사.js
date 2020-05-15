function solution(answers) {
    const answer = [];
    const count = [ 0, 0, 0 ];
    const order = [[1,2,3,4,5], [2,1,2,3,2,4,2,5], [3,3,1,1,2,2,4,4,5,5]];

    answers.forEach((ans, i) => {
        // 1번
        if (order[0][i % order[0].length] === ans) count[0]++;
        // 2번
        if (order[1][i % order[1].length] === ans) count[1]++;
        // 3번
        if (order[2][i % order[2].length] === ans) count[2]++;
    });

    const max = Math.max.apply(null, count);

    count.forEach ((c, i) =>  {
        if (c === max) answer.push(i+1);
    });
    return answer;
}
