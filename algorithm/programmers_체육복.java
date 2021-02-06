import java.util.HashSet;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int[] students = new int[n];
        HashSet<Integer> lostSet = new HashSet<Integer>();
        HashSet<Integer> reserveSet = new HashSet<Integer>();
        for (int l : lost) {
            lostSet.add(l);
        }
        for (int r : reserve) {
            reserveSet.add(r);
        }
        
        for (int i = 0; i < n; i++) {
            students[i] = 1;
            if (lostSet.contains(i+1)) students[i]--;
            if (reserveSet.contains(i+1)) students[i]++;
        }
        
        for (int i = 0; i < students.length-1; i++) {
            if (students[i] >= 2 && students[i+1] == 0) {
                // 내가 2개 이상이고, 뒷사람이 없으면 제공
                students[i]--;
                students[i+1]++;
            }
            if (students[i] == 0 && students[i+1] >= 2) {
                // 내가 없고, 뒷사람이 2개 이상이면 가져옴
                students[i]++;
                students[i+1]--;
            }
            // 체육복이 있는지 없는지 체크
            if (students[i] >= 1) answer++;
        }
        // 마지막 사람 체크
        if (students[students.length-1] >= 1) answer++;
        return answer;
    }
}
