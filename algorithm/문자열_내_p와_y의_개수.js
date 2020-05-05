function solution(s){    
    const pArr = s.match(/p/gi);
    const yArr = s.match(/y/gi);
    if (pArr && !yArr) return false;
    else if (!pArr && yArr) return false;
    return pArr.length === yArr.length;

    // const str = s.toUpperCase();
    // let pCount = 0;
    // let yCount = 0;
    // for(let i = 0; i < str.length; i++) {
    //     if(str[i] === 'P') pCount++;
    //     else if(str[i] === 'Y') yCount++;
    // }
    // return pCount === yCount;
}
