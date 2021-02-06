import java.util.ArrayList;

class Solution {
    public String solution(String[] participant, String[] completion) {
        ArrayList<String> participants = new ArrayList<String>();
        
		ArrayList<String> completions = new ArrayList<String>();
		for (String p : participant) {
			participants.add(p);
		}
		for (String c : completion) {
			completions.add(c);
		}
		
		participants.sort(null);
		completions.sort(null);
		
		for (int i = 0 ; i < completions.size(); i++) {
			if (!participants.get(i).equals(completions.get(i))) return participants.get(i);
		}
		return participants.get(participants.size()-1);
    }
}
