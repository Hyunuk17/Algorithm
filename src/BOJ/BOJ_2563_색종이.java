package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_2563_색종이 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 2563. 색종이
		 * --------------
		 * 
		 * 가로세로 100 : 정사각형 도화지
		 * 가로세로 10: 검은 색종이
		 * 
		 * 색종이가 붙은 검은 영역의 넓이 구하기
		 * 
		 * N : 색종이의 수
		 * arr[i][0] : 색종이의 왼쪽 변과 도화지의 왼쪽 변 사이의 거리
		 * arr[i][1] : 색종이의 아래쪽 변과 도화지의 아래쪽 변의 사이
 		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		board = new int[100][100];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 문제풀이
		for(int n=0;n<N;n++) { // 각 색종이의 수
			for(int i=0;i<10;i++) { // 세로
				for(int j=0;j<10;j++) { // 가로
					// (왼쪽, 아래쪽)이지만, (왼쪽, 위쪽)으로 생각하고 풀어도 답에 영향은 없다 
					board[arr[n][0]+i][arr[n][1]+j] = 1; 
				}
			}
		}
		
		// 횟수 세기
		int ans = 0;
		for(int i=0;i<100;i++) {
			for(int j=0;j<100;j++) {
				if(board[i][j] == 1) {
					ans++;
				}
			}
		}
		
		// 출력
		bw.write(ans+"");
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[][] arr;
	static int[][] board;

}
