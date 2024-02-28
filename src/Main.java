import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 20303. 할로윈의 양아치 ---------------------
		 * 
		 * 사탕 빼앗기 한 아이에게 빼앗으면 그 친구들의 사탕도 모조리 뺴았는다. - 무향 그래프 인접 노드?
		 * 
		 * K명 미만으로 최대한 빼앗을 수 있는 사탕 개수 구하기
		 * 
		 * N: 거리의 아이들 수, 1<= N <= 30,000 M: 아이들의 친구 관계 수, 0 <= M <= 100,000 K: 넘으면 안된는
		 * 아이 인원 수, 1 <= K <= min(N, 3,000)
		 * 
		 * c: 아이들이 받은 사탕의 수, 1 <= c_i <=10,000
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 그래프 생성
		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		// 사탕 개수 입력
		st = new StringTokenizer(br.readLine());
		c = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			c[i] = Integer.parseInt(st.nextToken());
		}

		// 간선 입력
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b);
			graph[b].add(a);
		}

		// 문제풀이
		// 모든 노드를 시작점으로 삼아 BFS
		// 3,000 * (3,000 + 100,000) == 3*10^3 * 10^6 : 완전 탐색 불가능

		// 시작점을 특정할 수 없음
		// 해당 연결 요소가 포함한 값의 총합으로 계산? -> BFS, Union-Find
		visited = new boolean[N + 1];
		for (int i = 1; i <= N; i++) {
			if (visited[i] == false) {
				BFS(i);
			}
		}

		// 최대 인원수가 K미만이 되도록 연결 요소를 조합
		// 최대 사탕의 수 구하기

		// Knapsack
		// i(번호), j(무게), dp[i][j](최대캔디수)
		// dp[i][j] := i번째 물건까지 무게 j 이하로 가방에 담았을 때, 최대 가치
		dp = new int[list.size() + 1][K];

		for (int i = 1; i <= list.size(); i++) {
			for (int j = 0; j < K; j++) {
				int w = list.get(i - 1).cnt;
				int v = list.get(i - 1).candy;

				if (j - w < 0) {
					dp[i][j] = dp[i - 1][j];
					continue;
				}
				dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w] + v);
			}
		}

		// 출력
		sb.append(dp[list.size()][K - 1]);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static int K;
	static int[] c;
	static List<Integer>[] graph;
	static boolean[] visited;
	static List<Node> list = new ArrayList<>();
	static int max = 0;
	static int[][] dp;

	// Connected Component 구하기
	static void BFS(int start) {
		int candy = 0;
		int cnt = 0;
		Queue<Integer> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start] = true;

		while (!queue.isEmpty()) {
			int now = queue.poll();
			candy += c[now];
			cnt++;

			for (int next : graph[now]) {
				if (visited[next] == true) {
					continue;
				}

				visited[next] = true;
				queue.add(next);
			}
		}

		list.add(new Node(candy, cnt));
	}

	static class Node {
		int candy;
		int cnt;

		public Node(int candy, int cnt) {
			this.candy = candy;
			this.cnt = cnt;
		}
	}

}
