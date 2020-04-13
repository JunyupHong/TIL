function solution(bridge_length, weight, truck_weights) {
    let answer = 0;

    const bridgeQueue = [];
    let bridgeWeight = 0;

    for(let i = 0; i < bridge_length - 1; i++) {
        bridgeQueue.push(0);
    };


    while(truck_weights.length !== 0) {
        bridgeWeight -= bridgeQueue.shift();

        // check weight
        if(bridgeWeight + truck_weights[0] > weight) {
            // 뒤에 차가 못들어갈때
            bridgeQueue.push(0);
        } else {
            // 뒤에 차가 들어올때
            const w = truck_weights.shift();
            bridgeQueue.push(w);
            bridgeWeight += w;
        }
        answer++;
    }

    answer += bridge_length;
    return answer;
}