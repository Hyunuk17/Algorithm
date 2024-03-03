package SWEA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1767_프로세서연결하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1767. 프로세서 연결하기
		 * -----------------------
		 * 
		 * NxN Cell
		 * core or 전선
		 * 가장자리 전원 흐름
		 * 
		 * core - 전선 - 전원  :  
		 * - 직선만 가능
		 * - 전선 교차 안됨
		 * 
		 * 최대한 많은 core, 전선 길이의 합이 최소가 되는 값 구하기
		 * 
		 * 7 <= N <= 12
		 * 1 <= core <= 12
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			cores = new ArrayList<>(); // 코어 리스트
			min = Integer.MAX_VALUE; // 전선의 최솟값
			maxCore = Integer.MIN_VALUE; // 사용할 코어 개수, 가능한 한 많은 core 사용
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 1) { // 코어라면
						
						// 가장자리는 Pass
						if(i == 0 || j == 0 || i == N-1 || j == N- 1) {
							continue;
						}
						// core 저장
						cores.add(new Node(i, j));
					}
				}
			}

			// 문제풀이			
			DFS(0, 0, 0);
			
			
			// 출력
			sb.append("#").append(t).append(" " ).append(min).append("\n");
			bw.write(sb.toString());
			bw.flush();
			
		}
		
		bw.close();
		br.close();
	}
	
	
	static int T;
	static int N;
	static int[][] board;
	static List<Node> cores;
	static int[] dr = { -1, 1, 0, 0};
	static int[] dc = { 0, 0, -1 ,1};
	static int maxCore = Integer.MIN_VALUE;
	static int min;
	
	static void DFS(int depth, int core, int wire) {
		if(depth == cores.size()) { // core를 모두 탐색하면 종료
			if(maxCore < core) { // 더 많은 코어를 사용하도록 선택
				maxCore = core;
				min = wire; // 전선 개수 초기화
			}
			else if(maxCore == core) {
				min = Math.min(min, wire);
			}
			return;
		}
		
		Node now = cores.get(depth);
		for(int d = 0; d<4;d++) { // 현재 core의 4방향 탐색
			int cnt = 0;
			int nr = now.r;
			int nc = now.c;
			
			// 직선으로 반복하여 탐색
			while(true) {
				
				nr += dr[d];
				nc += dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) { // 전원에 도달
					 break;
				 }
				 
				if(board[nr][nc] == 1) { // 이미 전선이 있는 경우
					cnt = 0;
					break;
				}
				
				cnt++; // 전선 개수 +1
			}
			
			nr = now.r;
			nc = now.c;
			for(int i=0;i<cnt;i++) { // 전선 채우기
				nr += dr[d];
				nc += dc[d];
				board[nr][nc] = 1; // 전선 처리
			}
			
			if(cnt == 0) { // 갈 수 있는 경우의 수가 없다면
				DFS(depth+1, core, wire); // 현재 코어를 제외하고 다음 탐색
			}
			else { // 갈 수 있는 경우의 수가 있다면
				DFS(depth+1, core+1, wire + cnt); // 현재 코어를 포함, 전선의 개수를 누적하고 다음 탐색 
				
				nr = now.r;
				nc = now.c;
				
				for(int i=0;i<cnt;i++) { // 탐색 후 전선 원상 복구
					nr += dr[d];
					nc += dc[d];
					board[nr][nc] = 0; // 원상 복구
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
