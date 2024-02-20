package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2636_치즈 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 2636. 치즈
		 * -------------
		 * 
		 * 사각형 판
		 * 판의 가장자리 X, 치즈를 둘 수 없음
		 * 회색 - 치즈, 하나 이상의 구멍이 있을 수 있음
		 * 겉부분의 공기에 노출된 치즈는 1시간 후에 녹음 : 'c'로 표시된 부분
		 * 
		 * 공기 중에 치즈가 모두 녹아 없어지는 데 걸리는 시간 구하기
		 * + 모두 녹기 한 시간 전에 남아있는 치즈 조작의 칸 개수 구하기
		 * 
		 * (r, c) <= 100
		 * 빈 칸(0), 치즈(1)
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		board2 = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) {
					cheeses++; // 치즈 개수
				}
			}
		}
		
		// 문제풀이
		while(cheeses > 0) {
			time++; // 녹는데 걸리는 시간
			// 구역 나누기
			section = new boolean[N][M]; // 구역 확인 
			idx = 0; // 구역 번호
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(section[i][j] == false) {
						devideSection(i, j); // 구역 나누기
						idx++; // 다음 구역 번호
					}				
				}
			}
			
			// 치즈 녹이기
			cnt = 0; // 이번 시간에서 녹인 치즈의 개수
			for(int i=0;i<N;i++) {
				for(int j=0;j<M;j++) {
					if(board2[i][j] == 0) { // 구역 번호가 0이면 실행, 첫 번째 칸은 치즈가 들어갈 수 없으므로 항상 0이다
						check(i, j); // 겉면의 치즈 녹이기
					}
				}
			}
			
			cheeses -= cnt; // 전체 치즈 개수 줄이기
		}
		
		
		// 출력
		sb.append(time).append("\n").append(cnt);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int M;
	static int[][] board;
	static int[][] board2; // 구역
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int cheeses;
	static boolean[][] section;
	static int idx;
	static int cnt;
	static int time;
	
	// 구역 나누기 BFS
	static void devideSection(int r, int c) {
		Queue<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] {r, c});
		section[r][c] = true;
		
		// 인접한 번호를 가지는 구역으로 분리
		while(!queue.isEmpty()) {
			int[] point = queue.poll();
			r = point[0];
			c = point[1];
			board2[r][c] = idx;
			
			for(int d=0;d<4;d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
					continue;
				}
				
				if(section[nr][nc] == true) {
					continue;
				}
				
				if(board[r][c] != board[nr][nc]) {
					continue;
				}
				
				section[nr][nc] = true;
				queue.add(new int[] {nr, nc});
			}
		}
	}
	
	// 현재 칸에서 인접한 4방향에 치즈가 있는지 확인
	static void check(int r, int c) {
		for(int d=0;d<4;d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			if(nr < 0 || nr >= N || nc < 0 || nc >= M) { // 범위를 벗어난 경우
				continue;
			}
			
			if(board[nr][nc] == 1) { // 인접한 칸에 치즈가 있는 경우
				cnt++; // 개수 +1
				board[nr][nc] = 0; // 치즈 녹이기
			}
			
		}
	}

}
