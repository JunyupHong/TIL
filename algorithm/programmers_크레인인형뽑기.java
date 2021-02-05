import java.util.Stack;

class Solution {
    public int solution(int[][] board, int[] moves) {
       int answer = 0;
        Stack<Integer> dollStack = new Stack<Integer>();
        Stack<Integer>[] map = new Stack[board.length];
        for (int i = 0; i < board.length; i++) {
        	map[i] = new Stack<Integer>();
        }
        
        for (int i = board.length - 1; i >= 0; i--) {
        	for (int j = board.length - 1; j >= 0; j--) {
        		if(board[j][i] != 0) {
        			map[i].push(board[j][i]);
        		}
        	}
        }
        
        for (int m : moves) {
        	if (map[m-1].isEmpty()) continue;
        	
        	int doll = map[m-1].pop();
        	if (dollStack.size() != 0 && dollStack.peek() == doll) {
        		dollStack.pop();
        		answer += 2;
        	} else dollStack.push(doll);
        }
       return answer;
    }
}
