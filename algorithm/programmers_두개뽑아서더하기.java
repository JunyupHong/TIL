import java.util.HashSet;
import java.util.ArrayList;


class Solution {
    static HashSet<Integer> set;
    public int[] solution(int[] numbers) {
        
        set = new HashSet<Integer>();
        
        permutation(numbers, 0, new int[2], new boolean[numbers.length]);

        
        int[] answer = new int[set.size()];
        int i = 0;
        for (int s : set) {
            answer[i++] = s;
        }
        return answer;
    }
	
	static void permutation(int[] numbers, int dest, int[] selected, boolean[] isSelected) {
		if (dest == 2) {
			set.add(selected[0] + selected[1]);
			return;
		}
		
		for (int i = 0; i < numbers.length; i++) {
			if (isSelected[i]) continue;
			isSelected[i] = true;
			selected[dest] = numbers[i];
			permutation(numbers, dest+1, selected, isSelected);
			isSelected[i] = false;
		}
    }

}
