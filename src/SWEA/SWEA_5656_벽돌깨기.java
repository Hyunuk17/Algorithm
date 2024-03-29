package src.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_5656_벽돌깨기 {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 5656. 벽돌깨기 ----------------
		 * 
		 * WxH 격자, 구슬 N개 구슬을 쏘아 벽돌을 깨트리는 게임 0(빈 공간), 1~9(벽돌)
		 * 
		 * 구슬 좌, 우 움직임 항상 맨 위에 있는 벽돌만 부수기 가능
		 * 
		 * 구슬이 명중한 벽돌은 상하좌우로 '벽돌에 적힌 숫자 -1 '칸 만큼 같이 제거 연쇄작용 있음 공중에 뜬 구슬을 밑으로 떨어짐
		 * 
		 * 1 <= N <= 4 2 <= W <= 12 2 <= H <= 15
		 * 
		 * 남은 벽돌의 개수 구하기
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			min = Integer.MAX_VALUE;
			
			
			int[][] board = new int[H][W];
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 문제풀이
			// 입력이 작아 BF 가능?
			// Queue로 연쇄폭발 구현
			for (int i = 0; i < W; i++) { // 구슬 시작점 선택
				int[][] tmpBoard = new int[H][W]; // 복사본 생성
				for (int j = 0; j < H; j++) {
					tmpBoard[j] = board[j].clone();
				}
				drop(tmpBoard, i); // 구슬 부수기
				re(1, tmpBoard); // 재귀
			}
			
			
			// 출력
			sb.append("#").append(t).append(" ").append(min).append("\n");
			bw.write(sb.toString());
			bw.flush();

		}

		bw.close();
		br.close();
	}

	static int T;
	static int N, W, H;
	static int[] dr = {-1, 1, 0, 0}; // 상 하 좌 우
	static int[] dc = {0, 0, -1, 1};
	static int min = Integer.MAX_VALUE;

	static void re(int depth, int[][] board) {
		if (depth == N) { // 구슬 개수 반복
			int cnt = 0; // 남은 구슬 개수
			for(int i=0;i<H;i++) {
				for(int j=0;j<W;j++) {
					if(board[i][j] != 0) {
						cnt++; // count
					}
				}
			}
			min = Math.min(min, cnt); // 갱신
			return;
		}

	

		// 다음 구슬을 떨어뜨릴 위치
		for (int i = 0; i < W; i++) {
			// 복사본 생성
			int[][] tmpBoard = new int[H][W];
			for (int j = 0; j < H; j++) {
				tmpBoard[j] = board[j].clone();
			}
			drop(tmpBoard, i); // 구슬 떨어뜨림
			re(depth + 1, tmpBoard); // 다음 재귀
		}
	}

	static void drop(int [][] board, int point) {
		Queue<Node> queue = new ArrayDeque<>();
		boolean[][] visited = new boolean[H][W];

		// 구슬이 떨어진 위치
		for(int h=0;h<H;h++) {
			if(board[h][point] != 0) {
				queue.add(new Node(h, point, board[h][point])); // 연쇄폭발 시작점
				visited[h][point] = true; // 방문처리
				break;
			}
		}
		
		// 연쇄폭발
		while(!queue.isEmpty()) {
			Node cur = queue.poll(); 
			board[cur.r][cur.c] = 0; // 현재 위치 구슬 부수기
			
			for(int d=0;d<4;d++) { // 상하좌우
				int nr = cur.r;
				int nc = cur.c;
				for(int i=0;i<cur.v-1;i++) { // 연쇄폭발 거리만큼 반복
					nr += dr[d];
					nc += dc[d];

					// 범위를 벗어난 경우
					if(nr < 0 || nc < 0 || nr >= H || nc >= W) {
						continue;
					}
					
					
					// 이미 방문한 점
					if(visited[nr][nc] == true) {
						continue;
					}
					
					// 빈 공간
					if(board[nr][nc] == 0) {
						continue;
					}
					
					visited[nr][nc] = true; // 방문처리
					queue.add(new Node(nr, nc, board[nr][nc])); // 연쇄폭발
				}
				
			}
			
		}
		
		// 바닥으로 정리
		for(int i=0;i<W;i++) {
			for(int j=H-1;j>0;j--) { // 바닥부터 탐색
				if(board[j][i] == 0) { // 0일 때, 위에 벽돌이 있는지 확인
					// 0의 1칸 위에부터 꼭대기까지
					for(int k=j-1;k>=0;k--) {
						if(board[k][i] != 0) { // 벽돌이 있다면
							board[j][i] = board[k][i];
							board[k][i] = 0; // 위치 이동
							break;
						}
					}
				}
			}
		}
		
	}
	
	static class Node {
		int r = 0;
		int c = 0;
		int v = 0;
		
		public Node(int r, int c, int v) {
			this.r = r;
			this.c = c;
			this.v = v;
		}
	}
}
