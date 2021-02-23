import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        int bridgeWeight = 0;
        
        Queue<Integer> trucks = new LinkedList<Integer>();
        Queue<Integer> bridge = new LinkedList<Integer>();
        for (int w : truck_weights) {
            trucks.offer(w);
        }
        for (int i = 0; i < bridge_length; i++) {
            bridge.offer(0);
        }
        while (trucks.size() > 0) {
            bridgeWeight -= bridge.poll();
            
            if (bridgeWeight + trucks.peek() > weight) bridge.offer(0);
            else {
                int w = trucks.poll();
                bridge.offer(w);
                bridgeWeight += w;
            }
            answer++;
        }
        answer += bridge_length;
        return answer;
    }
}
