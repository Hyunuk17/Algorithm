package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17070_파이프옮기기1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 17070. 파이프 옮기기 1
		 * -----------------------
		 * 
		 * NxN 집
		 * 빈 칸(0), 벽(1)
		 * 
		 * 파이프 이동
		 * 가로(0), 세로(1), 대각선(2) 방향 가능
		 * 
		 * 파이프 오른 쪽 끝에 이동시키는 방법의 개수 구하기
		 * 
		 * 3 <= N <= 16
		 * 방법의 수 <= 1,000,000 : int
		 * 1초
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		board = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 문제풀이
		// (0,0), (0,1) : 가로(0)에서 시작
		// (N-1,N-1)에 닿으면 좋료
		// N <= 16이라 시간 복잡도를 크게 신경쓰지 않았음 
		BFS();
		
		// 출력
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}

	static int N;
	static int[][] board;
	static int[][][] direction = {
			{{0, 1}, {0,0}, {1, 1}},
			{{0, 0}, {1, 0}, {1, 1}},
			{{0, 1}, {1, 0}, {1, 1}}
			};
	
	
	static int cnt;
	
	static void BFS() {
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0, 1, 0));
		
		while(!queue.isEmpty()) {
			Node now = queue.poll();
			
			// 목적지 도달 == 경우의 수
			if(now.r == N-1 && now.c == N-1) {
				cnt++;
			}
			
			// 현재 파이프가 갈 수 있는 방향
			for(int d=0;d<3;d++) {
				int nr = now.r + direction[now.d][d][0];
				int nc = now.c + direction[now.d][d][1];
				int nd = d; // 방향
				
				// 가로일 때, 세로는 제외
				if(now.d == 0 && nd == 1) {
					continue;
				}
				// 세로일 때, 가로는 제외
				else if(now.d == 1 && nd == 0) {
					continue;
				}
				
				// 범위를 벗어난 경우
				if(nr < 0 || nr >= N || nc < 0 || nc >= N) {
					continue;
				}
				
				// 벽이면 제외
				if(board[nr][nc] == 1) {
					continue;
				}
				
				// 대각선이면 벽을 3개 보아야 함
				if(nd == 2) {
					if(board[nr-1][nc] == 1 || board[nr][nc-1] == 1) {
						continue;
					}
				}
				
				// queue에 담고 다음 탐색
				queue.add(new Node(nr, nc, nd));				
			} 
		}
		
	}
	
	static class Node {
		int r;
		int c;
		int d;
		
		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
	}
}
