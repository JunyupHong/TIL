function solution (participant, completion) {
    let answer;
    const comp_hash = {};
    completion.forEach((c, i) => {
        if(comp_hash[c]) comp_hash[c]++;
        else comp_hash[c] = 1;
    });
    for(let i = 0; i < participant.length; i++) {
        if(!comp_hash[participant[i]]) {
            answer = participant[i];
            break;
        }
        comp_hash[participant[i]]--;
    }
    return answer;
}
