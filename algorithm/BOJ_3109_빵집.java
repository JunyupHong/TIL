package com.ssafy.algo.ws0218;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static int R;
	static int C;
	static String[][] map;
//	static boolean[][] isVisited;
	static int result;

	public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("src/com/ssafy/algo/ws0218/input.txt"));
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = in.readLine().split(" ");
		R = Integer.parseInt(inputs[0]);
		C = Integer.parseInt(inputs[1]);
		
//		isVisited = new boolean[R][C];
		map = new String[R][C];
		for (int i = 0; i < R; i++) {
			map[i] = in.readLine().split("");
		}
		result = 0;
		for(int i = 0; i < R; i++) {
			recursive(i, 0);
		}
		
		System.out.println(result);
	}
	
	static boolean recursive(int row, int col) {
		if (map[row][col].equals("x")) return false;
		if (col == C-1) {
			result++;
			return true;
		}

		map[row][col] = "x";
		if (row > 0 && !map[row-1][col+1].equals("x")) {
			if(recursive(row-1, col+1)) return true;
		}
		if (!map[row][col+1].equals("x")) if (recursive(row, col+1)) return true;
		if (row < R-1 && !map[row+1][col+1].equals("x")) {
			if (recursive(row+1, col+1)) return true;
		}
		
//		isVisited[row][col] = true;
//		if (row > 0 && !isVisited[row-1][col+1]) {
//			if(recursive(row-1, col+1)) return true;
//		}
//		if (!isVisited[row][col+1]) if (recursive(row, col+1)) return true;
//		if (row < R-1 && !isVisited[row+1][col+1]) {
//			if (recursive(row+1, col+1)) return true;
//		}

		return false;
	}
}

