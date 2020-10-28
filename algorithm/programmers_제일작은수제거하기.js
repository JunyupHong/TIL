function solution(arr) {
    return arr.length === 1 ? [-1] : arr.filter(ele => ele !== Math.min(...arr));
}
