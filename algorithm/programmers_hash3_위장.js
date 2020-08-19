function solution(clothes) {
    const map = new Map();
    clothes.forEach(cloth => {
        if (map.has(cloth[1])) map.set(cloth[1], map.get(cloth[1]) + 1);
        else map.set(cloth[1], 1);
    });
    
    return [...map.values()].reduce((acc, cur) => acc * (cur+1), 1) - 1;
    // let result = 1;
    // map.forEach(ele => result *= ele + 1);
    // return result - 1;
}
