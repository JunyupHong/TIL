import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        List<Integer> cit = new ArrayList<Integer>();
        for (int c : citations) cit.add(c);
        cit.sort((a, b) -> b - a);
        
        for (int h = cit.get(0); h > 0; h--) {
            int n = h;
            if(cit.stream().filter(c -> c >= n).count() >= h) return h;
        }
        
        return 0;
    }
}
