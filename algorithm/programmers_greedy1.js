function solution(n, lost, reserve) {
    const std_info = Array.apply(null, Array(n)).map(Number.prototype.valueOf, 1);
    lost.forEach(l => std_info[l-1]--);
    reserve.forEach(r => std_info[r-1]++);
    for (let i = 0; i < std_info.length; i++) {
        if (!std_info[i]) continue;
        if (std_info[i-1] === 2) {
            std_info[i]++;
            std_info[i-1]--;
        } else if(std_info[i+1] === 2) {
            std_info[i]++;
            std_info[i+1]--;
        }
    }
    return std_info.filter(std => std).length;
}
