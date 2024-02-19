package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_1954_달팽이숫자 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1954. 달팽이 숫자 ------------------
		 * 
		 * 1부터 N*N까지 숫자를 시계방향으로 출력
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Test Case
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			visited = new boolean[N][N];
			StringBuilder sb = new StringBuilder();

			// 문제풀이
			solve();


			// 출력
			sb.append("#").append(t).append("\n");
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					sb.append(board[i][j]).append(" ");
				}
				sb.append("\n");
			}
			bw.write(sb.toString());
			bw.flush();
		}

		bw.close();
		br.close();
	}

	static int T;
	static int N;
	static int[][] board;
	static boolean[][] visited;
	// 방향: 좌, 하, 우, 상
	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static void solve() {
		// 초기 설정: 좌표, 입력할 수
		int y = 0;
		int x = 0;
		int num = 1;
		// 시작점 
		board[y][x] = num;
		visited[y][x] = true;
		// 시작방향
		int direction = 0;
		num++;
		
		while (true) {
			// 기저조건
			if (num > N * N) {
				break;
			}
			
			// 다음 방향으로 좌표 설정
			int ny = y + dy[direction];
			int nx = x + dx[direction];
			
			
			// 범위를 벗어나거나 or 이미 숫자가 있는 곳이라면
			if(ny < 0 || ny >= N || nx < 0 || nx >= N || visited[ny][nx] == true) {
				direction = (direction+1)%4; // 방향 바꾸기
				continue;
			}
			
			// 현재 위치에 숫자를 입력
			visited[ny][nx] = true;
			board[ny][nx] = num;
			// 다음 숫자로 변경
			num++;
			// 좌표 변경
			y = ny;
			x = nx;
		}
	}
}
