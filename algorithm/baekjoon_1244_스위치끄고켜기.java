/**
 * BOJ 1244 스위치 켜고 끄기
 */
package com.ssafy.algo.hw01;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int switchCount = sc.nextInt();
		sc.nextLine();
		
		String[] switches = sc.nextLine().split(" ");
		
		int studentCount = sc.nextInt();
		sc.nextLine();
		String[][] students = new String[studentCount][2];
		for (int i = 0; i < studentCount; i++) {
			students[i] = sc.nextLine().split(" ");
		}
		
		for (int i = 0; i < students.length; i++) {
			if (students[i][0].equals("1")) {
				recursiveMan(switches, Integer.parseInt(students[i][1]) - 1, Integer.parseInt(students[i][1]));
			} else {
				recursiveWoman(switches, Integer.parseInt(students[i][1]) - 1, 0);
			}
		}
		
		for (int i = 0; i < switches.length; i++) {
			System.out.print(switches[i] + " ");
			if ((i+1) % 20 == 0)
				System.out.println("");
		}
		sc.close();
	}

	public static void recursiveMan(String[] switches, int currentIndex, int gap) {
		if (currentIndex >= switches.length)
			return;
		switches[currentIndex] = change(switches[currentIndex]);
		recursiveMan(switches, currentIndex + gap, gap);
	}
	public static void recursiveWoman(String[] switches, int index, int gap) {
		if (switches.length <= index + gap || index - gap < 0)
			return;
		if (!switches[index + gap].equals(switches[index - gap])) {
			return;
		}
		if (gap == 0) {
			switches[index] = change(switches[index]);
		} else {
			switches[index + gap] = change(switches[index + gap]);
			switches[index - gap] = change(switches[index - gap]);
		}
		recursiveWoman(switches, index, gap + 1);
	}
	
	public static String change(String state) {
		if (state.equals("1"))
			return "0";
		else 
			return "1";
	}
}

