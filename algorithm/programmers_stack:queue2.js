function solution(bridge_length, weight, truck_weights) {
    let answer = 0;

    const bridgeQueue = [];
    let bridgeWeight = 0;

    for(let i = 0; i < bridge_length; i++) {
        bridgeQueue.push(0);
    };

    while(truck_weights.length !== 0) {
        bridgeWeight -= bridgeQueue.shift();

        // 뒤에 트럭이 못들어갈때
        if(bridgeWeight + truck_weights[0] > weight) bridgeQueue.push(0);
        else {
            // 뒤에 트럭이 들어올때
            const w = truck_weights.shift();
            bridgeQueue.push(w);
            bridgeWeight += w;
        }
        answer++;
    }
    // 마지막 트럭이 지나가는 시간
    answer += bridge_length;
    return answer;
}