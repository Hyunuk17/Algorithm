package src.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4485_녹색옷입은애가젤다지 {

	public static void main(String[] args) throws IOException {
		/** BOJ_4485. 녹색 옷 입은 애가 젤다지?
		 * -----------------------------
		 * 
		 * NxN 동굴 
		 * (0,0)->(n-1,n-1)
		 * 상하좌우 인접 1칸씩 이동
		 * 
		 * 각 칸의 값의 합이 최소가 되도록 이동
		 * 
		 * 2 <= N <= 125 (0이면 종료)
		 * 0 <= K <= 9
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int idx = 0;
		while(true) {
			idx++;
			// 입력
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			if(N == 0) {
				break;
			}
			
			board = new int[N][N];
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			min = Integer.MAX_VALUE;
			// 문제풀이
			// N <= 125
			// 최소 비용 구하기 = 최단 경로 구하기
			// 정점(0,0)에서 정점(N-1, N-1)까지의 경로 구하기 : Dijkstra

			distance = new int[N][N];
			Dijkstra();
			
			
			// 출력
			sb.append("Problem ").append(idx).append(": ").append(distance[N-1][N-1]).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		bw.close();
		br.close();
	}

	static int N;
	static int[][] board;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int min;
	static int[][] distance;
	
	static void Dijkstra() {
		for(int i=0;i<N;i++) {
			Arrays.fill(distance[i], Integer.MAX_VALUE);
		}
		distance[0][0] = board[0][0];
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(0, 0, board[0][0]));
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			if(cur.cost > distance[cur.r][cur.c]) {
				continue;
			}

			for(int d=0;d<4;d++) {
				int nr = cur.r + dr[d];
				int nc = cur.c + dc[d];
				
				if(nr < 0 || nc <  0 || nr >= N || nc >= N) {
					continue;
				}
				
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
		int cost;
		
		public Node(int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}

		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
}	

