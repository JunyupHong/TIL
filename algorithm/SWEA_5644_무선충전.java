import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static class BC implements Comparable<BC> {
		int[] pos = new int[2];
		int range;
		int power;
		public BC(int x, int y, int range, int power) {
			this.pos[0] = x;
			this.pos[1] = y;
			this.range = range;
			this.power = power;
		}
		
		boolean isChargable(int[] pos) {
			int d = Math.abs(this.pos[0] - pos[0]) + Math.abs(this.pos[1] - pos[1]);
			if (d > this.range) return false;
			else return true;
		}

		@Override
		public int compareTo(BC o) {
			if (this.power > o.power) return -1;
			else return 0;
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String[] inputs;
		int T = Integer.parseInt(in.readLine());

		for (int tc = 1; tc <= T; tc++) {
			inputs = in.readLine().split(" ");
			int M = Integer.parseInt(inputs[0]);
			int A = Integer.parseInt(inputs[1]);
			String[] aMove = in.readLine().split(" ");
			String[] bMove = in.readLine().split(" ");
			int[] aPos = new int[] {1, 1};
			int[] bPos = new int[] {10, 10};
			
			int chargeAmount = 0;
			ArrayList<BC> bc = new ArrayList<BC>();
			for (int i = 0; i < A; i++) {
				inputs = in.readLine().split(" ");
				bc.add(new BC(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]), Integer.parseInt(inputs[3])));
			}
			
			bc.sort(null);
			
			for (BC cur : bc) {
				if (cur.isChargable(aPos)) {
					chargeAmount += cur.power;
					break;
				}
			}
			for (BC cur : bc) {
				if (cur.isChargable(bPos)) {
					chargeAmount += cur.power;
					break;
				}
			}

			ArrayList<BC> aPossibleBC = new ArrayList<BC>();
			ArrayList<BC> bPossibleBC = new ArrayList<BC>();
			for (int t = 0; t < M; t++) {
				aPossibleBC.clear();
				bPossibleBC.clear();

				// move
				if (aMove[t].equals("1")) aPos[1] -= 1;
				else if (aMove[t].equals("2")) aPos[0] += 1;
				else if (aMove[t].equals("3")) aPos[1] += 1;
				else if (aMove[t].equals("4")) aPos[0] -= 1;
				if (bMove[t].equals("1")) bPos[1] -= 1;
				else if (bMove[t].equals("2")) bPos[0] += 1;
				else if (bMove[t].equals("3")) bPos[1] += 1;
				else if (bMove[t].equals("4")) bPos[0] -= 1;
				
				for (BC cur : bc) {
					if (cur.isChargable(aPos)) aPossibleBC.add(cur);
					if (cur.isChargable(bPos)) bPossibleBC.add(cur);
				}
				
				if (aPossibleBC.size() == 0 && bPossibleBC.size() == 0) {
					// nothing
				} else if (aPossibleBC.size() == 0 || bPossibleBC.size() == 0) {
					if (aPossibleBC.size() > 0) chargeAmount += aPossibleBC.get(0).power;
					else chargeAmount += bPossibleBC.get(0).power;
				} else {
					BC aTemp = aPossibleBC.get(0);
					BC bTemp = bPossibleBC.get(0);
					if (aTemp == bTemp) {
						if (aPossibleBC.size() > 1 && bPossibleBC.size() > 1) {
							if (aPossibleBC.get(1).power > bPossibleBC.get(1).power) {
								aTemp = aPossibleBC.get(1);
							} else {
								bTemp = bPossibleBC.get(1);
							}
						} else if (aPossibleBC.size() > 1) {
							aTemp = aPossibleBC.get(1);
						} else if (bPossibleBC.size() > 1) {
							bTemp = bPossibleBC.get(1);
						}
					}
					if (aTemp == bTemp) {
						chargeAmount += aTemp.power;
					} else {
						chargeAmount += aTemp.power;
						chargeAmount += bTemp.power;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(chargeAmount).append("\n");
		}
		System.out.println(sb.toString());
	}
}
