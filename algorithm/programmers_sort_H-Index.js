function solution(citations) {
    for (let h = citations.length; h > 0; h--) {
        if (h <= citations.filter(c => c >= h).length) return h;
    }
    return 0;
}
