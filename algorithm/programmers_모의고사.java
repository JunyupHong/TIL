import java.util.Arrays;
import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        int[] correctCnts = {0, 0, 0};
        int[][] picks = { {1,2,3,4,5}, {2,1,2,3,2,4,2,5}, {3,3,1,1,2,2,4,4,5,5} };
        
        for (int i = 0 ; i < answers.length; i++) {
            for (int j = 0; j < picks.length; j++) {
                int idx = i % picks[j].length;
                if (picks[j][idx] == answers[i]) correctCnts[j]++;
            }
        }

        int max = 0;
        ArrayList<Integer> answerArrayList = new ArrayList<Integer>();
        for (int i = 0; i < correctCnts.length; i++) {
            if (max < correctCnts[i]) {
                max = correctCnts[i];
                answerArrayList.clear();
                answerArrayList.add(i + 1);
            } else if (max == correctCnts[i]) {
                answerArrayList.add(i + 1);
            }
        }
        
        int[] answer = new int[answerArrayList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerArrayList.get(i);
        }
        return answer;
    }
}
