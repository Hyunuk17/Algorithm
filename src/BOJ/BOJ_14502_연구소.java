package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14502_연구소 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 14502. 연구소
		 * ---------------
		 * 
		 * NxM 연구소
		 * 빈칸(0), 벽(1), 바이러스(2)
		 * 
		 * 벽 3개 새로 새워야 함
		 * 바이러스 상, 하, 좌, 우 이동
		 * 
		 * 안전 영역 크기의 최댓값을 구하기
		 * 
		 * 3 <= N, M <= 8
		 * 2 <= virus <= 10
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 문제풀이
		// 새로 세워야 하는 벽 3개
		// N, M <= 8
		// 64C3 = n! / (n-m)!*m! = 41664 => 약 NM^3
		// NM + 2NM = 3NM = 192 
		// 총 NM^4 < 10^8
		visited = new boolean[N][M];
		NP(0, 0, 0); // 벽 세우기
				
		// 출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int M;
	static int[][] board;
	static Node[] permutation;
	static boolean[][] visited;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc=  {0, 0, -1, 1};
	static int max = 0;
	
	// 벽의 위치 정하기
	static void NP(int depth, int r, int c) {
		if(depth == 3) {
			solve();
			return;
		}
		
		for(int i=r;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(board[i][j] != 0) {
					continue;
				}
				
				board[i][j] = 1;
				NP(depth+1, i, j);
				board[i][j] = 0;
			}
		}
	}
	
	static void solve() {
		// permuation에 따른 임시 board 만들기
		int[][] tmpBoard = new int[N][M];
		for(int i=0;i<N;i++) {
			tmpBoard[i] = board[i].clone();
		}
		
		
		// BFS 수행하며 바이러스 퍼트리기
		visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tmpBoard[i][j] != 2) {
					continue;
				}
				if(visited[i][j] == false) {
					BFS(i, j, tmpBoard);
				}
			}
		}
		
		// 안전구역 세기
		int cnt = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tmpBoard[i][j] == 0) {
					cnt++;
				}
			}
		}

		// 안전구역 최대값 구하기
		max = Math.max(max, cnt);
	}
	
	// BFS
	static void BFS(int r, int c, int[][] tmpBoard) {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(r,c));
		visited[r][c] = true;
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			tmpBoard[now.r][now.c] = 2;
			
			for(int d=0;d<4;d++) {				
				int nr = now.r + dr[d];
				int nc = now.c + dc[d];
				
				if(nr < 0 || nr >= N || nc < 0 || nc >= M) { // 범위 벗어남
					continue;
				}
				
				if(visited[nr][nc] == true) { // 이미 방분
					continue;
				}
				
				if(tmpBoard[nr][nc] == 1) { // 벽
					continue;
				}
				
				visited[nr][nc] = true; // 방문처리
				queue.add(new Node(nr, nc)); // 바이러스 queue에 넣기
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
