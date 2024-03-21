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

public class BOJ_2533_사회망서비스 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ_2533. 사회망 서비스(SNS) ------------------------
		 * 
		 * SNS에서 친구 관계를 그래프로 표현 : 트리, 모든 경로 존재, 사이클 없음 사람은 정점, 엣지는 친구 관계
		 * 
		 * 사람 : 얼리 어답터 or 아님 얼리 어답터가 아니라면, 주위 사람들이 모두 얼리 어답터여야 아이디어를 받아들일 수 있음
		 * 
		 * 2 <= N <= 1,000,000
		 * 
		 * 모든 개인이 새 아이디어를 수용하기 위해 필요한 최소 얼리 어답터 수 구하기
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < N - 1; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			graph[u].add(v);
			graph[v].add(u);
		}

		// 문제풀이
		// dp[N][2] : N명, 얼리어답터 or !얼리어답터
		// 내가 root라면?
		// 자식 모드가 모두 얼리어답터여야 함
		// or 내가 얼리어탑터여야 함
		// 내가 얼리어탑터일때 최소이려면, 자식은 상관 없음
		// base case? : leaf일때, 내가 무조건 얼리어답터여야 함

		dp = new int[N + 1][2];
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE);
		}

		min = Math.min(topDown(1, 1, 0), topDown(1, 1, 1));
		sb.append(min);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static List<Integer>[] graph;
	static int[][] dp;
	static int min = Integer.MAX_VALUE;

	static int topDown(int root, int pre, int status) {
		if (dp[root][status] != Integer.MAX_VALUE) {
			return dp[root][status];
		}

		dp[root][status] = status;
		for (int i = 0; i < graph[root].size(); i++) {
			if (graph[root].get(i) == pre) {
				continue;
			}
			dp[root][status] += Math.min(topDown(graph[root].get(i), root, 1), topDown(graph[root].get(i), root, 1 - status));
		}

		return dp[root][status];
	}
}
