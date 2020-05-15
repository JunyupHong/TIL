function solution(d, budget) {
    const sort = d.sort((a, b) => a - b);
    let acc = 0;
    for (let i = 0; i < sort.length; i++) {
        acc += sort[i];
        if (acc > budget) return i;
    }
    return sort.length;
}
