import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(in.readLine());
		
		for (int testCase = 1; testCase <= T; testCase++) {
			String[] mapSize = in.readLine().split(" ");
			int H = Integer.parseInt(mapSize[0]);
			int W = Integer.parseInt(mapSize[1]);
			
			String[][] map = new String[H][W];
			int[] pos = new int[2];
			pos[0] = -1;
			pos[1] = -1;
			for (int i = 0; i < H; i++) {
				String[] line = in.readLine().split("");
				for (int j = 0; j < W; j++) {
					map[i][j] = line[j];
//					if (line[j].matches("^|<|>|v")) {
					if (line[j].equals("^") || line[j].equals("v") || line[j].equals("<") || line[j].equals(">")) {
						pos[0] = i;
						pos[1] = j;
					}
				}
			}
			
			int commandCount = Integer.parseInt(in.readLine());
			
			for (String command : in.readLine().split("")) {
				doCommand(map, pos, command);
			}
			
			System.out.print("#" + testCase + " ");
			for (String[] m : map) {
				System.out.println(String.join("", m));
			}
			
		}
	}
	
	static void doCommand(String[][] map, int[] pos, String cmd) {
		switch(cmd) {
			case "U":
				if (pos[0] > 0 && map[pos[0] - 1][pos[1]].equals(".")) {
					map[pos[0]][pos[1]] = "."; 
					pos[0]--;
				}
				map[pos[0]][pos[1]] = "^";
				break;
			case "D":
				if (pos[0] < map.length - 1 && map[pos[0] + 1][pos[1]].equals(".")) {
					map[pos[0]][pos[1]] = "."; 
					pos[0]++;
				}
				map[pos[0]][pos[1]] = "v";
				break;
			case "L":
				if (pos[1] > 0 && map[pos[0]][pos[1] - 1].equals(".")) {
					map[pos[0]][pos[1]] = "."; 
					pos[1]--;
				}
				map[pos[0]][pos[1]] = "<";
				break;
			case "R":
				if (pos[1] < map[0].length - 1 && map[pos[0]][pos[1] + 1].equals(".")) {
					map[pos[0]][pos[1]] = "."; 
					pos[1]++;
				}
				map[pos[0]][pos[1]] = ">";
				break;
			case "S":
				int bulletX = pos[0];
				int bulletY = pos[1];
				int direction = -1;
				if (map[pos[0]][pos[1]].equals("^")) {
					if (pos[0] <= 0) break;
					direction = 1;
				} else if (map[pos[0]][pos[1]].equals("v")) {
					if (pos[0] >= map.length - 1) break;
					direction = 2;
				} else if (map[pos[0]][pos[1]].equals("<")) {
					if (pos[1] <= 0) break;
					direction = 3;
				} else if (map[pos[0]][pos[1]].equals(">")) {
					if (pos[1] >= map[0].length - 1) break;
					direction = 4;
				}
				while (true) {
					switch(direction) {
						case 1:
							bulletX--;
							break;
						case 2:
							bulletX++;
							break;
						case 3:
							bulletY--;
							break;
						case 4:
							bulletY++;
							break;
					}
					if (bulletX < 0 || bulletX >= map.length || bulletY < 0 || bulletY >= map[0].length)
						break;
					if (map[bulletX][bulletY].equals("#")) break;
					else if (map[bulletX][bulletY].equals("*")) {
						map[bulletX][bulletY] = ".";
						break;
					}
				}
				break;
		}
	} 
}

