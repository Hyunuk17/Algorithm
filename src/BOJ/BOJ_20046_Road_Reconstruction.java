package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_20046_Road_Reconstruction {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 20046. Road Reconstruction 
		 * ------------------------------
		 * 
		 * NxM 격자판 맨 왼쪽 위 -> 맨 오른쪽 아래 이동
		 * 
		 * 최단 비용 경로 구하기
		 * 
		 * 1 <= m, n <= 1,000
		 * 단위도로 존재(0), 1, 2의 비용으로 도로 건설 
		 * 가능한 단위 격자(1, 2), X로 표시된 단위
		 * 격자(-1)
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		board = new int[n][m];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 문제풀이
		// 최단 비용의 경로를 구하는 문제
		// 간선을 이으면서 최소로 하는 경로 만들기
		// 다익스트라를 하는데 검은 위치는 0, X는 패스하면서 만들기

		// N, M <= 1000
		// (4*1000*1000)*(log4*1000*1000)?

		distance = new int[n][m];
		Dijkstra();

		int ans = distance[n - 1][m - 1];
		sb.append(ans == Integer.MAX_VALUE ? -1 : ans);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}

	static int m;
	static int n;
	static int[][] board;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] distance;

	static void Dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		for (int i = 0; i < n; i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		distance[0][0] = board[0][0];

		pq.add(new Node(0, 0, 0));
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			if (distance[cur.r][cur.c] < cur.w) {
				continue;
			}

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nr < 0 || nr >= n || nc < 0 || nc >= m) {
					continue;
				}

				if (board[nr][nc] == -1) {
					continue;
				}

				if (distance[cur.r][cur.c] + board[nr][nc] < distance[nr][nc]) {
					distance[nr][nc] = distance[cur.r][cur.c] + board[nr][nc];
					pq.add(new Node(nr, nc, distance[nr][nc]));
				}
			}
		}
	}

	static class Node implements Comparable<Node> {
		int r;
		int c;
		int w;

		public Node(int r, int c, int w) {
			this.r = r;
			this.c = c;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}

}
