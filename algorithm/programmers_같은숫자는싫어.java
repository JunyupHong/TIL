import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for (int n : arr) {
            if (arrList.size() == 0) arrList.add(n);
            else {
                if (arrList.get(arrList.size()-1) != n) arrList.add(n);
            }
        }
        
        int[] answer = new int[arrList.size()];
        int idx = 0;
        for (int n : arrList) {
            answer[idx++] = n;
        }
        return answer;
    }
}
