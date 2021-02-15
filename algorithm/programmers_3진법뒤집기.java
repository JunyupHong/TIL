import java.util.ArrayList;

class Solution {
    public int solution(int n) {
        int result = 0;
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        while(n > 0) {
            numbers.add(n%3);
            n /= 3;
        }
        
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(numbers.size() - i-1) * Math.pow(3, i);
        }
        return result;
    }
}
