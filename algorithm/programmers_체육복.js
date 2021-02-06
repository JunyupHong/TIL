function solution(n, lost, reserve) {
    const lostSet = new Set(lost);
    const reserveSet = new Set(reserve);
    for (let l of lost) {
        if (reserveSet.has(l)) {
            lostSet.delete(l);
            reserveSet.delete(l);
        }
    }
    
    const borrow = new Set();
    for (let l of lostSet) {
        if (reserveSet.has(l-1)) {
            borrow.add(l);
            reserveSet.delete(l-1);
        } else if (reserveSet.has(l+1)) {
            borrow.add(l);
            reserveSet.delete(l+1);
        }
    }
    
    return n - lostSet.size + borrow.size;
}
