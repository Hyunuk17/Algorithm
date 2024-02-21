package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683_감시 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 15683. 감시
		 * --------------
		 * 
		 * NxM 직사각형 지도
		 * 빈칸(0), 벽(6), CCTV(1~5)
		 * 
		 * K개의 CCTV, 5가지 종류
		 * 1: >
		 * 2: <>, 반대 방향
		 * 3: ^>, 직각
		 * 4: <^>
		 * 5: <^v>
		 *
		 * CCTV 90도 회전 가능
		 * 
		 * 벽을 넘어 감시할 수 없음
		 * CCTV 통과 가능
		 * 
		 * CCTV의 방향을 정해서, 사각 지대의 최소 크기 구하기
		 * 
		 * 1 <= N, M <= 8
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		cctvs = new ArrayList<>(); // CCTV 정보 리스트
		
		// 각 CCTV가 가질 수 있는 방향 목록
		d = new int[5][][];
		d[0] = d1;
		d[1] = d2;
		d[2] = d3;
		d[3] = d4;
		d[4] = d5;
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				// CCTV면 리스트에 담는다
				if(board[i][j] == 1 || board[i][j] == 2 || board[i][j] == 3 || board[i][j] == 4 || board[i][j] == 5) {
					cctvs.add(new Node(i, j, board[i][j]));
				}
			}
		}
		
		// 문제풀이
		directions = new int[cctvs.size()]; // 각 CCTV가 가질 수 있는 방향의 순열 
		NP(0); // 순열 구하기
		
		// 출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static int[][] board;
	static List<Node> cctvs;
	static int min = Integer.MAX_VALUE;
	static int[] directions;
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	
	static int[][][] d;
	static int[][] d1 = {{0}, {1}, {2}, {3}}; // CCTV1
	static int[][] d2 = {{0, 1}, {2, 3}, {0, 1}, {2, 3}}; // CCTV2
	static int[][] d3 = {{0, 3}, {3, 1}, {1, 2}, {2, 0}}; // CCTV3
	static int[][] d4 = {{2, 0, 3}, {0, 3, 1}, {3, 1, 2}, {1, 2, 0}}; // CCTV4
	static int[][] d5 = {{0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}, {0, 1, 2, 3}}; // CCTV5
	
	// 순열 구하기
	static void NP(int depth) {
		if(depth == cctvs.size()) { // 기저조건
			// CCTV의 방향 순열에서의 사각지대 최소값 구하기
			min = Math.min(min, getArea());
			
			return;
		}
		
		// CCTV가 가질 수 있는 방향은 90도씩 회전하므로 4가지
		for(int i=0;i<4;i++) {
			directions[depth] = i; // 순열 저장
			NP(depth+1); // 다음 재귀
		}
	}
	
	// 사각지대 구하기
	static int getArea() {
		// Board: 깊은 복사
		int[][] tmpBoard = new int[N][M];
		for(int i=0;i<N;i++) {
			tmpBoard[i] = board[i].clone();
		}
		
		int cnt = 0; // 사각지대 count
		for(int i=0;i<cctvs.size();i++) { // CCTV 번호
			Node cctv = cctvs.get(i); // 현재 CCTV
			
			// 현재 CCTV가 가진 감시할 수 있는 방향의 수
			for(int j=0;j<d[cctv.type-1][directions[i]].length;j++) { 
				int nr = cctv.r;
				int nc = cctv.c;
				
				// 전진하지 못할 때까지 탐색
				while(true) {
					nr += dr[d[cctv.type-1][directions[i]][j]];
					nc += dc[d[cctv.type-1][directions[i]][j]];
					
					// 범위를 벗어난 경우
					if(nr < 0 || nr >= N || nc < 0 || nc >= M) {
						break;
					}
					
					// 벽에 가로막힌 경우
					if(board[nr][nc] == 6) {
						break;
					}
					
					// 감시되고 있는 구역 #으로 처리
					tmpBoard[nr][nc] = '#';
				}
			}
		}
		
		// 사각지대 세기
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				if(tmpBoard[i][j] == 0) { // 사각지대라면
					cnt++; // cnt +1
				}
			}
		}
		
		return cnt; // 현재 순열에서 사각지대의 수 반환
	}
	
	// CCTV
	static class Node {
		int r;
		int c;
		int type;
		
		public Node(int r, int c, int type) {
			this.r = r;
			this.c = c;
			this.type = type;
		}
	}
}
