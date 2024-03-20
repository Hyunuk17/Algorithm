package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11049_행렬곱셈순서 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 11049. 행렬 곱셈 순서 --------------------
		 * 
		 * NxM, MxK 행렬을 곱하는 순서를 다르게 해서 곱셈 연산의 수를 구함 곱셈 연산의 수가 최소
		 * 
		 * 1 <= N <= 500 1 <= r, c <= 500
		 * 
		 * min < 2^31-1 = int
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		matrix = new Node[N + 1];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			matrix[i] = new Node(r, c, 0);
			// Node로 변경?
		}

		// 문제풀이

		// dp[left][right] := left~right까지의 행렬 곱셈 연산 최소 횟수
		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		// 500 * 500 = 250000
		min = topDown(0, N - 1);
		sb.append(min);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int r;
	static Node[] matrix;
	static int min = Integer.MAX_VALUE;
	static int[][] dp;

	static int topDown(int left, int right) {
		// memoization
		if (dp[left][right] != Integer.MAX_VALUE) {
			return dp[left][right];
		}

		// 행렬 1개 연산 불가 : 횟수 0
		if (left == right) {
			return 0;
		}

		// 중간점 i를 기준으로 최소를 갱신
		for (int i = left; i < right; i++) {
			dp[left][right] = Math.min(dp[left][right], topDown(left, i) + topDown(i + 1, right) + matrix[left].r * matrix[i].c * matrix[right].c);
		}

		// 구간 [left,right]에서의 곱셈 연산 최소 횟수
		return dp[left][right];
	}

	static class Node {
		int r;
		int c;
		int cnt;

		public Node(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}

		@Override
		public String toString() {
			return "Node [r=" + r + ", c=" + c + ", cnt=" + cnt + "]";
		}

	}
}
