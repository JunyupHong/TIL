import java.util.ArrayList;
import java.util.Arrays;
class Solution {
    public int[] solution(int[] arr, int divisor) {
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int a: arr) {
            if (a % divisor == 0) numbers.add(a);
        }
        if (numbers.size() == 0) return new int[] {-1};
        
        int[] answer = new int[numbers.size()];
        for (int i = 0; i < numbers.size(); i++) {
            answer[i] = numbers.get(i);
        }
        Arrays.sort(answer);
        return answer;
    }
}
