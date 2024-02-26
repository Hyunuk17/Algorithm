package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3197_백조의_호수 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * 지구온난화 := BOJ 백조의 호수 -------
		 * 
		 * RxC : 지도 일반 땅과 인접한 빙하가 매일 녹는다 대각선 고려X
		 * 
		 * 두 사람이 몇일마다 만나는지 계산 땅(.), 빙하(X), 사람(L)
		 * 
		 * 1 <= R, C <= 1,500 시간복잡도 주의
		 *
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		queue = new ArrayDeque<>();
		waterQueue = new ArrayDeque<>();

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				board[i][j] = str.charAt(j);

				if (board[i][j] == 'L') {
					if (start == null) {
						start = new Node(i, j);
					} else {
						end = new Node(i, j);
					}

					board[i][j] = '.'; // 백조가 있는 땅도 물이다
				}

				if (board[i][j] == '.') {
					waterQueue.add(new Node(i, j));
				}

			}
		}

		// 문제풀이
		visited = new boolean[R][C];
		queue.add(start);
		visited[start.r][start.c] = true;

		int time = 0;
		while (true) {
			if (move() == true) {
				break;
			}

			melting();
			time++;
		}

		// 출력
		sb.append(time);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int R;
	static int C;
	static char[][] board;
	static boolean[][] visited;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static Node start;
	static Node end;
	static Queue<Node> queue;
	static Queue<Node> waterQueue;
	static int ans;

	static boolean move() {
		Queue<Node> q = new ArrayDeque<>();

		while (!queue.isEmpty()) {
			Node now = queue.poll();
			int r = now.r;
			int c = now.c;

			if (r == end.r && c == end.c) { // 목적지 도착
				return true;
			}

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C) { // 범위를 벗어남
					continue;
				}

				if (visited[nr][nc]) { // 이미 방문
					continue;
				}

				visited[nr][nc] = true;
				if (board[nr][nc] == 'X') { // 얼음
					q.add(new Node(nr, nc)); // 다음 탐색지역: 현재 물과 연결된 얼음(녹을 예정)
				} else if (board[nr][nc] == '.') {
					queue.add(new Node(nr, nc)); // 현재 위치 가능한 지역: 백조의 위치
				}
			}
		}

		// 현재 위치에서는 목적지에 도달하지 못했음
		// 위 함수에서 물은 모두 방문되었음(백조 위치)
		// 이제 녹을 얼음을 이동할 지역으로 queue(q)에 담기
		queue = q; // 녹을 얼음의 위치가 물이 되어 다음 타임에 백조가 위치할 장소가 됨
		return false; // 목적지에 도달하지 못했음
	}

	static void melting() {
		int size = waterQueue.size();
		for (int i = 0; i < size; i++) {
			Node now = waterQueue.poll(); // 물의 위치
			int r = now.r;
			int c = now.c;

			for (int d = 0; d < 4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];

				if (nr < 0 || nr >= R || nc < 0 || nc >= C) { // 범위를 벗어남
					continue;
				}

				if (board[nr][nc] == 'X') { // 물과 인접한 얼음
					board[nr][nc] = '.'; // 얼음이 녹아 물이 되었음
					waterQueue.add(new Node(nr, nc)); // 다음 타임에 얼음과 인접한 물의 위치가 됨
				}
			}

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
