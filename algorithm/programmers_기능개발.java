import java.util.Queue;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answerList = new ArrayList<Integer>();
        int day = 0;
        for (int i = 0; i < progresses.length; i++) {
            int cnt = 1;
            if ((100 - progresses[i]) % speeds[i] == 0)
                day = (100 - progresses[i]) / speeds[i];
            else 
                day = (100 - progresses[i]) / speeds[i] + 1;
            
            for (int j = i+1; j < progresses.length; j++) {
                if (progresses[j] + speeds[j] * day < 100) {
                    break;
                }
                cnt++;
                i++;
            }
            answerList.add(cnt);
        }
        
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
