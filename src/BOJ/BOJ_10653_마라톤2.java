package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_10653_마라톤2 {
	/*
	 * BOJ 10653. 마라톤2 
	 * -----------------
	 * 
	 * [문제 설명] 
	 * 마라톤 대회 
	 * - 1~N 체크포인트
	 * 
	 * 건너뛰기 
	 * - 중간의 최대 K개 체크포인트 건너뛰기 
	 * - 1, N 체크포인트는 건너뛸 수 없음
	 * 
	 * 거리 
	 * - Manhattan Distance: |x1-x2|+|y1-y2|
	 * 
	 * [입력] 
	 * N : 체크포인트 개수 
	 * K : 건너뛸 체크포인트 개수 
	 * (x,y) : 체크포인트 좌표(겹칠 수도 있음)
	 * 
	 * [출력] 
	 * 최대 K개 체크포인트를 건너뛰면서, 달릴 수 있는 최소 거리
	 * 
	 * [제한사항] 
	 * 3 <= N <= 500 
	 * K < N 
	 * -1,000 <= x <= 1,000 
	 * -1,000 <= y <= 1,000
	 * 
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		checkpoints = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());

			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			checkpoints.add(new Node(x, y));
		}

		// 문제풀이
		/*
		 * 마라톤 
		 * - 1번에서 출발 ~ N번에 도착 
		 * - 중간에 K개 체크포인트 건너뛰기
		 *
		 * 최소 거리 구하기 
		 * - DP[i][j] : i번째 체크포인트까지 보았을 때, j개 체크포인트를 건너뛰고, 달려야하는 최소 거리 
		 * - 이전 위치(pre) ~ 현재 위치(i)의 거리(distance) 더하기
		 */

		dp = new int[N + 1][K + 1];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], 123_456_789);
		}

		dp[1][0] = 0;
		for (int i = 2; i <= N; i++) { // 체크포인트 확인
			for (int j = 0; j <= K; j++) { // 건너뛰기

				for (int k = 0; k <= j; k++) { // 1~j까지 모든 건너뛰기 경우를 확인
					int pre = (i - 1) - k; // 이전 체크포인트

					if (pre < 1) { // 체크포인트는 1보다 작을 수 없음
						continue;
					}

					// 최소 거리 계산
					dp[i][j] = Math.min(dp[i][j], dp[pre][j - k] + getDistance(pre, i));
				}
			}
		}
		;

		for (int i = 0; i <= K; i++) {
			result = Math.min(result, dp[N][i]);
		}

		sb.append(result);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int K;
	static List<Node> checkpoints;
	static int[][] dp;
	static int result = Integer.MAX_VALUE;

	static int getDistance(int start, int end) {
		start--;
		end--;
		return Math.abs(checkpoints.get(start).x - checkpoints.get(end).x) + Math.abs(checkpoints.get(start).y - checkpoints.get(end).y);
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}