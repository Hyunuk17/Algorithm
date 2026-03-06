package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7569_토마토 {
	/*
	 * BOJ 7569. 토마토 
	 * ---------------
	 * 
	 * [문제 설명] 
	 * 토마토 보관 
	 * - MxN 격자 모양 상자에 하나씩 넣음 
	 * - 상자들을 수직으로 H개 쌓아 보관
	 * 
	 * 익은 토마토 
	 * - 인접한 장소에 익지 않은 토마토들이 있다면, 영향을 받아 익음 
	 * - [위, 아래, 왼쪽, 오른쪽, 앞, 뒤] 6방향 
	 * - 저절로 익는 경우는 없음
	 * 
	 * [입력] 
	 * M, N : 상자의 크기 
	 * H : 상자의 높이 수 
	 * tomato[][][] : 1(익은 토마토), 0(익지 않은 토마토), -1(빈 공간)
	 * 
	 * [출력] 
	 * 창고의 모든 토마토가 익게되는 일수 
	 * - 불가능하면 -1
	 *
	 * [제한사항] 
	 * 2 <= M <= 100 
	 * 2 <= N <= 100 
	 * 1 <= H <= 100
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		tomato = new int[H][N][M];
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {

				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					tomato[h][i][j] = Integer.parseInt(st.nextToken());
				}
			}
		}

		// 문제풀이
		/*
		 * 모든 토마토 익히기 
		 * - 익은 토마토에서 1일마다 전파 => BFS
		 * 
		 * BFS 
		 * - O(V+E) 
		 * - 정점 : O(MxNxH) = O(100 * 100 * 100) => 10*6 
		 * - 간선 : O(6V)
		 * => O(7V) => PASS
		 */

		BFS();

		boolean flag = true;
		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (tomato[h][i][j] == 0) {
						flag = false;
					}
				}
			}
		}

		int result = !flag ? -1 : min;
		sb.append(result);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int M;
	static int N;
	static int H;
	static int[][][] tomato;
	static int[] dh = { -1, 1, 0, 0, 0, 0 };
	static int[] dr = { 0, 0, -1, 1, 0, 0 };
	static int[] dc = { 0, 0, 0, 0, -1, 1 };
	static int min = 0;

	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][][] visited = new boolean[H][N][M];

		for (int h = 0; h < H; h++) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (tomato[h][i][j] == 1) {
						queue.add(new Node(h, i, j, 0));
						visited[h][i][j] = true;
					}
				}
			}
		}

		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			min = Math.max(min, cur.distance);

			for (int d = 0; d < 6; d++) {
				int nh = cur.h + dh[d];
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];

				if (nh < 0 || nr < 0 || nc < 0 || nh >= H || nr >= N || nc >= M) {
					continue;
				}

				if (visited[nh][nr][nc] == true) {
					continue;
				}

				if (tomato[nh][nr][nc] == -1 || tomato[nh][nr][nc] == 1) {
					continue;
				}

				visited[nh][nr][nc] = true;
				tomato[nh][nr][nc] = 1;
				queue.add(new Node(nh, nr, nc, cur.distance + 1));
			}
		}
	}

	static class Node {
		int h;
		int r;
		int c;
		int distance;

		public Node(int h, int r, int c, int distance) {
			this.h = h;
			this.r = r;
			this.c = c;
			this.distance = distance;
		}
	}
}