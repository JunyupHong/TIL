import java.util.Set;
import java.util.HashSet;
import java.util.Arrays;

class Solution {
    static Set<Integer> answer;

    public int solution(String numbers) {
        answer = new HashSet<Integer>();
        String[] inputs = numbers.split("");
        for (int i = 1; i <= inputs.length; i++) {
            permutation(inputs, new String[i], 0, new boolean[inputs.length]);
        }
        return answer.size();
    }
    
    public void permutation(String[] numbers, String[] selected, int dest, boolean[] isVisited) {
        if (dest == selected.length) {
            int n = Integer.parseInt(String.join("", selected));
            if (checkPrimeNumber(n)) answer.add(n);
            return;
        }
        
        for (int i = 0; i < numbers.length; i++) {
            if (isVisited[i]) continue;
            selected[dest] = numbers[i];
            isVisited[i] = true;
            permutation(numbers, selected, dest+1, isVisited);
            isVisited[i] = false;
        }
    }

    public boolean checkPrimeNumber(int number) {
        if (number == 0 || number == 1) return false;
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
