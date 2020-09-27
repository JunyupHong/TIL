function solution(strings, n) {
    // return strings.sort((a, b) => {
    //     if (a[n] === b[n]) return ( a < b ) ? -1 : ( a === b ) ? 0 : 1;
    //     else return ( a[n] < b[n] ) ? -1 : ( a[n] === b[n] ) ? 0 : 1;
    // });
    return strings.sort((a, b) => ( a[n] < b[n] ) ? -1 : ( a[n] === b[n] ) ? ( a < b ) ? -1 : ( a === b ) ? 0 : 1 : 1);
}
