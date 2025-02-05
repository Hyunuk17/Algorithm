package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_27210_신을_모시는_사당 {
	/*
	 * BOJ 27210. 신을 모시는 사당 
	 * ------------------------
	 * 
	 * [문제 설명] 
	 * 사당 
	 * - 돌상 N개 
	 * - 일렬로 놓여 있음 
	 * - 왼쪽/오른쪽 방향
	 * 
	 * 궁극의 깨달음 
	 * - 연속한 몇 개의 돌상에 금칠 
	 * - 깨달음의 양 = |(왼쪽 돌상 개수) - (오른쪽 돌상 개수)|
	 * 
	 * [입력] 
	 * N : 돌상의 개수 
	 * 방향 : 왼쪽(1), 오른쪽(2)
	 * 
	 * [출력] 
	 * 최대한 많은 깨달음을 얻기 위해 금을 칠하였을 때, 얻을 수 있는 깨달음의 양
	 *
	 * [제한사항] 
	 * 1 <= N <= 100,000
	 * 
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		directions = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			directions[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		// 문제풀이
		/*
		 * 깨달음의 양이 최대 
		 * - 왼쪽을 바라보는 돌상 개수가 최대인 특정 구간 [L, R] 구하기 
		 * - |왼쪽-오른쪽| : 절댓값을 구해야 함, 반대 방향이면 절댓값이 1감소하는 구조
		 * 
		 * 연속합의 최대 구하기 
		 * - DP[i][0] : i번 원소까지 봤을 때 최대 구간, 왼쪽(0)인 경우 +1 
		 * - DP[i][1] : i번 원소까지 봤을 때 최대 구간, 오른왼쪽(1)인 경우 +1 
		 * - DP[i][j]가 음수라면, 현재 원소부터 시작하는 것으로 판단
		 * 
		 * 시간복잡도 
		 * - 100,000 = 10^5(N^2은 안된다는 힌트) 
		 * - O(N)
		 */

		dp = new int[N + 1][2];
		int max = 0;
		for (int i = 1; i <= N; i++) {
			int direction = directions[i];
			int reverseDirection = (direction + 1) % 2; // 0->1, 1->0

			// 현재 방향에 하나 더하기
			dp[i][direction] = dp[i - 1][direction] + 1;

			// 반대 방향이므로 구간합 감소, 음수가 되지는 않음
			if (dp[i - 1][reverseDirection] > 0) {
				dp[i][reverseDirection] = dp[i - 1][reverseDirection] - 1;
			}

			max = Math.max(max, dp[i][direction]);
		}

		sb.append(max);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int[] directions;
	static int[][] dp;
}
