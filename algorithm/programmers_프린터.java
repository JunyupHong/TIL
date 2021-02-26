import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Integer> priority = new LinkedList<Integer>();
        for (int i : priorities) {
            priority.offer(i);
        }        
        
        while(priority.size() > 0) {
            int J = priority.poll();
            boolean flag = false;
            for (int i : priority) {
                if (J >= i) continue;
                flag = true;
                break;
            }
            location--;
            if (flag) {
                priority.offer(J);
                if (location == -1) location = priority.size()-1;
            } else {
                answer++;
                if (location == -1) break;
            }
        }
        return answer;
    }
}
