function solution(cacheSize, cities) {
    let answer = 0;
    const cache = [];
    if (cacheSize === 0) return cities.length * 5;
    
    for (let city of cities) {
        const c = city.toLowerCase();
        const i = cache.indexOf(c);
        if (i !== -1) {
            answer += 1;
            if (cache.length === cacheSize) cache.splice(i, 1);
        } else {
            answer += 5;
            if (cache.length === cacheSize) cache.shift();
        }
        cache.push(c);
    }
    return answer;
}
