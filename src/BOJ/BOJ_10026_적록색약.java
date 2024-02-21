package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_10026_적록색약 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 10026. 적록색약
		 * ----------------
		 * 
		 * NxN, R(빨강) G(초록), B(파랑)
		 * 그림은 몇 개 구역으로 이루어져 있음
		 * 각 구역은 같은 색
		 * 
		 * 일반 사람과 적록색약(R-G)이 보는 구역의 수 출력
		 * 
		 * 1 <= N <= 100
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		board = new char[N][N];
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<N;j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		
		// 문제 풀이
		// 일반 사람이 봤을 때 구역의 수
		visited = new boolean[N][N];
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j] == false) { // 이번 구역을 탐색한 적 없다면
					cnt++; // 카운트 증가
					BFS(i, j); // 해당 구역을 전부 vistied 처리
				}
			}
		}
		
		// 적록 색약이 봤을 때 구역의 수
		visited = new boolean[N][N];
		int cnt2 = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(visited[i][j] == false) { // 이번 구역을 탐색한 적 없다면
					cnt2++; // 카운트 증가
					BFS2(i, j); // 해당 구역을 전부 vistied 처리
				}
			}
		}
		
		// 출력
		sb.append(cnt).append(" ").append(cnt2);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int N;
	static char[][] board;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static void BFS(int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] node = queue.poll();
			r = node[0];
			c = node[1];
			
			for(int d=0;d<4;d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위를 벗어남
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				
				// 이미 탐색한 구역
				if(visited[nr][nc] == true) {
					continue;
				}
				
				// 같은 색이 아님
				if(board[r][c] != board[nr][nc]) {
					continue;
				}
				
				visited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
				
			}
		}
	}
	
	static void BFS2(int r,int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.add(new int[] {r, c});
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			int[] node = queue.poll();
			r = node[0];
			c = node[1];
			
			for(int d=0;d<4;d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				// 범위를 벗어남
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				
				// 이미 탐색한 구역
				if(visited[nr][nc] == true) {
					continue;
				}
				
				// 적록색약
				if(board[r][c] == 'R' || board[r][c] == 'G') { // R or B
					if(board[nr][nc] == 'B') { // B
						continue;
					}
				}
				else if(board[r][c] == 'B') { // B
					if(board[nr][nc] != 'B') { // R or G
						continue;
					}
				}
				
				visited[nr][nc] = true;
				queue.add(new int[] {nr, nc});
				
			}
		}
	}
}
