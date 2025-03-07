package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_16437_양_구출_작전 {
	/*
	 * BOJ 16437. 양 구출 작전 
	 * ---------------------
	 * 
	 * [문제 설명] 
	 * 섬 
	 * - N개 
	 * - 1번 섬 : 구명보트 
	 * - 다른 섬 : 양들 or 늑대들 
	 * - 다리 : 각 섬 i에서 1번 섬으로 이어지는 경로는 유일(P[i])
	 * 
	 * 양들의 이주 
	 * - 구명보트를 타고, 늑대가 없는 나라로 이주 
	 * - 1번 섬으로 가는 길에, 양이 늑대를 만난다면, 늑대의 수만큼 잡아먹힘
	 *
	 * 
	 * [입력] 
	 * N : 섬의 개수 
	 * T : 늑대(W), 양(S) 
	 * A : 늑대 or 양의 수 
	 * P : 다리(a, b)
	 * 
	 * [출력] 
	 * 1번 섬에 도착한 양의 수
	 *
	 * [제한사항] 
	 * 2 <= N <= 123,456 
	 * 1 <= a_i <= 10^9 
	 * 1 <= P_i <= N
	 *
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		island = new ArrayList<>(); // 2~N번
		island.add(new Node('0', 0));
		island.add(new Node('0', 0));

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 2; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int cnt = Integer.parseInt(st.nextToken());
			int destination = Integer.parseInt(st.nextToken());
			graph[i].add(destination);
			graph[destination].add(i);

			island.add(new Node(c, cnt));
		}

		// 문제풀이
		/*
		 * 양 데리고 오기
		 * - 역순으로 보기
		 * - 1. DFS로 끝까지 이동 
		 * - 2. 복귀하면서 계산 
		 * - O(N)
		 * 
		 * 양의 수
		 * - 각 섬마다 최대 10^9
		 * - long 타입
		 */

		visited = new boolean[N + 1];
		visited[1] = true;
		sb.append(DFS(1));

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static List<Node> island;
	static List<Integer>[] graph;
	static boolean[] visited;
	static int count;

	static long DFS(int num) {
		long sheep = 0;
		long wolf = 0;
		
		Node cur = island.get(num);
		if (cur.t == 'W') {
			wolf = cur.cnt;
		} else {
			sheep += cur.cnt;
		}

		// 이전에서 넘어 온 양의 수 구하기
		for (int i = 0; i < graph[num].size(); i++) {
			int next = graph[num].get(i);
			if (visited[next]) {
				continue;
			}

			visited[next] = true;
			sheep += DFS(next);
		}

		return (sheep - wolf) < 0 ? 0 : (sheep - wolf);
	}

	static class Node {
		char t;
		int cnt;

		public Node(char t, int cnt) {
			this.t = t;
			this.cnt = cnt;
		}
	}
}