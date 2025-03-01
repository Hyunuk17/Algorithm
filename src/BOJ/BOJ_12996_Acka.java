package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_12996_Acka {
	/*
	 * BOJ 12996. Acka 
	 * ---------------
	 * 
	 * [문제 설명] 
	 * 아이돌 그룹 
	 * - 3명
	 * 
	 * 앨범 
	 * - 곡 S개 
	 * - 각 곡은 3명 중 적어도 1명이 불러야 함 
	 * - 참여한 사람이 다른 곡이 존재한다면, 다른 앨범으로 판단
	 * 
	 * [입력] 
	 * S : 앨범에 포함된 곡의 개수 
	 * arr : 각 사람이 불러야하는 곡의 수
	 * 
	 * [출력] 
	 * 앨범을 만들 수 있는 방법의 수 % 1,000,000,007
	 *
	 * [제한사항] 
	 * 1 <= S <= 50 
	 * 1 <= arr[i] <= S
	 *
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());

		arr = new int[3];
		for (int i = 0; i < 3; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 문제풀이
		/*
		 * 앨범을 만드는 경우의 수 
		 * - i번째 곡이 어디에 속할것인가 
		 * - {1], {2}, {3}, {1,2}, {1,3}, {2,3}, {1,2,3} => 7가지 
		 * - 7^50(S) => TLE
		 * 
		 * 최대로 속할 수 있는 수는 제한되어있음 
		 * - dp[s][i][j][k] : S개 곡을 보았을 때, 각 사람이 i,j,k개 녹음한 앨범의 수
		 * 
		 * 
		 */
		dp = new int[S + 1][arr[0] + 1][arr[1] + 1][arr[2] + 1];
		for (int[][][] a : dp) {
			for (int[][] b : a) {
				for (int[] c : b) {
					Arrays.fill(c, -1);
				}
			}
		}

		sb.append(topDown(S, arr[0], arr[1], arr[2]));

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int S;
	static int[] arr;
	static int[][][][] dp;
	static int[][] CASE = { 
			{ 1, 0, 0 }, 
			{ 0, 1, 0 }, 
			{ 0, 0, 1 }, 
			{ 1, 1, 0 }, 
			{ 1, 0, 1 }, 
			{ 0, 1, 1 }, 
			{ 1, 1, 1 }
	};
	static int MOD = 1_000_000_007;

	static int topDown(int s, int a, int b, int c) {
		// Base Case
		if (s == 0) {
			if (a == 0 && b == 0 && c == 0) {
				return 1;
			} else {
				return 0;
			}
		}

		if (s < 0) {
			return 0;
		}
		if (a < 0 || b < 0 || c < 0) {
			return 0;
		}

		// Memoization
		if (dp[s][a][b][c] != -1) {
			return dp[s][a][b][c];
		}

		// Recurrence Relation
		long result = 0;
		for (int i = 0; i < CASE.length; i++) {
			result += topDown(s - 1, a - CASE[i][0], b - CASE[i][1], c - CASE[i][2]);
		}

		dp[s][a][b][c] = (int) (result % MOD);
		return dp[s][a][b][c];
	}
}