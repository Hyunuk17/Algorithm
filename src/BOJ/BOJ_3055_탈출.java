package src.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055_탈출 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ_3055. 탈출 ------------
		 * 
		 * RxC 숲 .(비어있음), *(물), X(돌), 비버굴(D), 고슴도치(S)
		 * 
		 * 상하좌우 이동 1분마다 물이 비어있는 칸(인접)으로 확장 비버의 굴은 물이 들어오지 않음 고슴도치는 물이 찰 예정인 칸으로 이동 불가능
		 * 
		 * 고슴도치가 비버굴로 이동하기 위해 필요한 최소 시간 구하기 불가능하다면 "KAKTUS"
		 * 
		 * 1 <= R, C <= 50
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		board = new char[R][C];
		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);
				
				if (board[i][j] == 'D') { // 비버굴
					D = new Node(i, j);
				}
				
				if (board[i][j] == 'S') { // 고슴도치
					S = new Node(i, j);
				}
			}
		}

		// 문제풀이
		// BFS
		// 물 확장
		// 고슴도치 이동
		BFS();
		
		sb.append(ans != -1 ? ans : "KAKTUS");
		// 출력

		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int R;
	static int C;
	static char[][] board;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Node D;
	static Node S;
	static boolean[][] visited;
	static int ans = -1;

	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		visited = new boolean[R][C];
		queue.add(S); // 고슴도치 시작점

		int time = 0;
		while (!queue.isEmpty()) {
			flood(); // 홍수 부터 진행하여 갈 수 있는곳 제한
			int size = queue.size(); // 1초 단위 수행
			for (int k = 0; k < size; k++) {

				Node now = queue.poll();

				if (now.r == D.r && now.c == D.c) {
					ans = time;
					return;
				}

				for (int d = 0; d < 4; d++) {
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];

					// 범위 벗어남
					if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
						continue;
					}

					// 이미 방문한 곳
					if (visited[nr][nc] == true) {
						continue;
					}

					// 바위거나 물
					if (board[nr][nc] == 'X' || board[nr][nc] == '*') {
						continue;
					}

					// 방문처리
					visited[nr][nc] = true;
					queue.add(new Node(nr, nc));
				}

			}
			time++;
		}
	}

	// 홍수
	static void flood() {
		Queue<Node> queue = new ArrayDeque<>();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (board[i][j] == '*') { // 초기 물의 위치를 queue에 담기
					queue.add(new Node(i, j));
					visited[i][j] = true; // 방문처리
				}
			}
		}

		while (!queue.isEmpty()) {
			// 한 사이클만 수행
			int size = queue.size();
			for (int k = 0; k < size; k++) {
				Node now = queue.poll();

				for (int d = 0; d < 4; d++) {
					int nr = now.r + dr[d];
					int nc = now.c + dc[d];

					// 범위를 벗어남
					if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
						continue;
					}

					// 바위거나 비버굴이거나 이미 물이면
					if (board[nr][nc] == 'X' || board[nr][nc] == 'D' || board[nr][nc] == '*') {
						continue;
					}

					board[nr][nc] = '*'; // 물로 변경
				}
			}

			break;
		}
	}

	static class Node {
		int r;
		int c;

		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
