import java.util.ArrayList;

class Solution {
    public String solution(int[] numbers) {
        ArrayList<String> strings = new ArrayList<String>();
        for (int n : numbers) {
            strings.add(Integer.toString(n));
        }
        return strings.stream().sorted((a, b) -> {
            String s1 = b + a;
            String s2 = a + b;
            for (int i = 0; i < s1.length(); i++) {
                if (s1.charAt(i) - s2.charAt(i) > 0) return 1;
                else if (s1.charAt(i) - s2.charAt(i) < 0) return -1;
            }
            return 0;
            }).reduce("", (acc, cur) -> acc + cur);
    }
}
