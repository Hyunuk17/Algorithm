package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1987_알파벳 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1987. 알파벳
		 * --------------
		 * 
		 * RxC 보드
		 * (1,1) 말
		 * 각 칸에 대문자 알파벳
		 * 
		 * 상하좌우 이동 가능
		 * 같은 알파벳을 2번 지날 수 없음
		 * 
		 * 말이 최대한 몇 칸을 지낼 수 있는지 구하기
		 * 
		 * 1 <= R,C <= 20
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		board = new char[R][C];
		alpha = new boolean[26];
		
		for(int i=0;i<R;i++) {
			String str = br.readLine();
			for(int j=0;j<C;j++) {
				board[i][j] = str.charAt(j);
			}
		}
		
		// 문제풀이
		alpha[board[0][0] - 'A'] = true; // 처음 칸도 포함
		DFS(0, 0, 1);
		
		// 출력
		bw.write(max+"");
		bw.flush();
		
		bw.close();
		br.close();
	}

	static int R;
	static int C;
	static char[][] board;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[] alpha;
	static int max = Integer.MIN_VALUE;
	
	// DFS
	static void DFS(int y, int x, int depth) {
		max = Math.max(max, depth); // 최대값 구하기
		
		for(int d=0;d<4;d++) {	// 4방향 탐색
			int ny = y + dy[d];
			int nx = x + dx[d];
			
			if(ny < 0 || ny >= R || nx < 0 || nx >= C) { // 범위를 벗어나면 제외
				continue;
			}
			
			if(alpha[board[ny][nx] - 'A'] == true) { // 이미 사용한 알파벳이면 제외
				continue;
			}
			
			alpha[board[ny][nx] - 'A'] = true;
			DFS(ny, nx, depth+1); // DFS
			alpha[board[ny][nx] - 'A'] = false;
		}
	}
}
