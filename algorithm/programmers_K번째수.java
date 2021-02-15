import java.util.Arrays;
class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        int idx = 0;
        for (int[] command : commands) {
            int i = command[0];
            int j = command[1];
            int k = command[2];
            
            int[] newArr = new int[j-i+1];
            for (int newIdx = i-1; newIdx < j; newIdx++) {
                newArr[newIdx - i+1] = array[newIdx];
            }
            
            Arrays.sort(newArr);
            answer[idx++] = newArr[k-1];
        }
        return answer;
    }
}
