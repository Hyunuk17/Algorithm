package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1600_말이되고픈원숭이 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 1600. 말이 되고픈 원숭이
		 * ------------------------
		 * 
		 * HxW 격자판
		 * 평지(0), 장애물(1)
		 * 
		 * 체츠판의 나이트처럼 움직임 : K번 이동 가능
		 * 그냥 상하좌우 이동 가능
		 * 
		 * 맨 왼쪽 위 -> 맨 오른쪽 아래
		 * 
		 * 원숭이의 이동 횟수 최솟값 구하기
		 * 
		 * 1 <= W, H <= 200
		 * 0 <= K <= 30
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		K = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		board = new int[H][W];
		
		for(int i=0;i<H;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<W;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 문제풀이
		// O(200*200 * 12)
		BFS();
		
		// 출력
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
		
	}
	
	static int K;
	static int W;
	static int H;
	static int[][] board;
	static int[] kr = {-2, -1, -2, -1, 1, 2, 2, 1};
	static int[] kc = {-1, -2, 1, 2, -2, -1, 1, 2};
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1}; 
	static boolean[][][] visited;
	static int min = Integer.MAX_VALUE;;
	
	// BFS
	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		visited = new boolean[H][W][K+1]; // 나이트 이동 횟수
		queue.add(new Node(0, 0, 0, 0));
		visited[0][0][0] = true;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			if(now.r == H-1 && now.c == W-1) {
				if(now.k <= K) {					
					min = now.cnt;
					return;
				}
			}
			
			
			// 원숭이 4방향
			for(int d=0;d<4;d++) {
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				
				if(nr < 0 || nr >= H || nc < 0 || nc >= W) {
					continue;
				}
				
				if(visited[nr][nc][now.k] == true) {
					continue;
				}
				
				if(board[nr][nc] == 1) {
					continue;
				}
				
				visited[nr][nc][now.k] = true;	
				queue.add(new Node(nr, nc, now.cnt+1, now.k));
			}
			
			// 말 8방향
			if(now.k < K) {
				for(int k=0;k<8;k++) {
					int nr = now.r + kr[k];
					int nc = now.c + kc[k];
					
					if(nr < 0 || nr >= H || nc < 0 || nc >= W) {
						continue;
					}
					
					if(visited[nr][nc][now.k+1] == true) {
						continue;
					}
					
					if(board[nr][nc] == 1) {
						continue;
					}
					
					visited[nr][nc][now.k+1] = true;
					queue.add(new Node(nr, nc, now.cnt+1, now.k+1));
				}
			}
		}
	}
	
	static class Node {
		int r;
		int c;
		int cnt;
		int k;
		
		public Node(int r, int c, int cnt, int k) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
			this.k = k;
		}
	}
}
