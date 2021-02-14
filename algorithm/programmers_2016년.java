import java.util.Calendar;

class Solution {
    public String solution(int a, int b) {
        String[] result = new String[]{"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        
        Calendar calendar = Calendar.getInstance();
        calendar.set(2016, a - 1, b);
        
        return result[calendar.get(Calendar.DAY_OF_WEEK)-1];
    }
}
