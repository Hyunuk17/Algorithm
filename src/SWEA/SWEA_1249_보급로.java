package src.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_1249_보급로 {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 1249. 보급로
		 * ---------------
		 * 
		 * 2차 세계 대전
		 * 도로가 폭격과 시가전 등으로 파손되어 있음
		 * 
		 * 출발지(S)에서 도착지(G)까지 가기 위한 도로 복구 작업
		 * 깊이(board[r][c]) : 복구에 걸리는 시간
		 * 상하좌우 이동 가능
		 * 
		 * 최단 시간 구하기
		 * 
		 * N <= 100
		 * 0 <= 깊이
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
		
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			
			for(int i=0;i<N;i++) {
				String str = br.readLine();
				for(int j=0;j<N;j++) {
					board[i][j] = str.charAt(j) - '0';
				}
			}
			
			// 문제풀이
			// (0, 0) -> (N-1, N-1)
			// BFS or Dijkstra?
			// 가중치가 있고, 0이상의 정수 => Dijkstra!!
			Dijkstra();
			
			// 출력
			sb.append("#").append(t).append(" ").append(distance[N-1][N-1]).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}
	
	static int T;
	static int N;
	static int[][] board;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] distance;
	static final int INF = 1_000_000;
	
	static void Dijkstra() {
		distance = new int[N][N];
		for(int i=0;i<N;i++) { // 큰 수 초기화
			Arrays.fill(distance[i], INF);
		}
		distance[0][0] = 0; // 자기자신

		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, 0)); // 시작점
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 현재, 최단 비교
			if(cur.w > distance[cur.r][cur.c]) {
				continue;
			}
			
			for(int d=0;d<4;d++) { // 간선 탐색
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				// 범위를 벗어난 경우
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) {
					continue;
				}
				
				// 최단 경로가 존재
				if(distance[nr][nc] > distance[cur.r][cur.c] + board[nr][nc]) {
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
