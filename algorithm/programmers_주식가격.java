import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int p : prices) {
            queue.offer(p);
        }
        
        int idx = 0;
        while(queue.size() > 0) {
            int n = queue.poll();
            int count = 0;
            for (int i = answer.length - queue.size(); i < answer.length; i++) {
                count++;
                if (prices[i] < n) break;
            }
            answer[idx++] = count;
        }
        
        return answer;
    }
}
