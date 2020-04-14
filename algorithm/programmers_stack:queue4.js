function solution(priorities, location) {
    let answer = 0;
    const queue = priorities.map((p, i) => {
        return {
            val: p,
            idx: i,
        }
    });
    const order = [];
    while(queue.length !== 0) {
        const j = queue.shift();
        if (queue.some((ele) => ele.val > j.val)) queue.push(j);
        else order.push(j.idx);
    }
    answer = order.indexOf(location) + 1;
    return answer;
}