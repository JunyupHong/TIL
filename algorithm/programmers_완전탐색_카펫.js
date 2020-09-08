function solution(brown, yellow) {
    for (let i = 1; i <= yellow; i++) {
        const horizontal = yellow / i;
        const vertical = i;
        if (brown === (horizontal + vertical) * 2 + 4) return [horizontal + 2, vertical + 2];
    }
    
}
