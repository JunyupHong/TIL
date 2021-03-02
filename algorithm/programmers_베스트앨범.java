import java.lang.Comparable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

class Solution {
    static Map<String, Integer> totalCounts = new HashMap<String, Integer>();
    
    class Music implements Comparable<Music> {
        public String genre;
        public int plays;
        public int idx;
        public Music(String genre, int plays, int idx) {
            this.genre = genre;
            this.plays = plays;
            this.idx = idx;
            totalCounts.put(genre, totalCounts.get(genre) + plays);
        }
        @Override
        public int compareTo(Music m) {
            if (totalCounts.get(this.genre) > totalCounts.get(m.genre)) return -1;
            else if (totalCounts.get(this.genre) < totalCounts.get(m.genre)) return 1;
            
            if (this.plays > m.plays) return -1;
            else if (this.plays < m.plays) return 1;
            return 0;
        }
    }
    
    public int[] solution(String[] genres, int[] plays) {
        ArrayList<Music> musics = new ArrayList<Music>();
        Map<String, Integer> checkCounts = new HashMap<String, Integer>();
        
        for (int i = 0; i < genres.length; i++) {
            if (!totalCounts.containsKey(genres[i])) totalCounts.put(genres[i], 0);
            if (!checkCounts.containsKey(genres[i])) checkCounts.put(genres[i], 0);
            musics.add(new Music(genres[i], plays[i], i));
        }
        Collections.sort(musics);
        
        ArrayList<Integer> answerList = new ArrayList<Integer>();
        for (Music m : musics) {
            if (checkCounts.get(m.genre) == 2) continue;
            checkCounts.put(m.genre, checkCounts.get(m.genre) + 1);
            answerList.add(m.idx);
        }
        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }
}
