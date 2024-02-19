package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11660_구간합5 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 11660. 구간 합 구하기 5
		 * ---------------------
		 * 
		 * N*N표에 숫자가 채워져 있음
		 * (x1, y1)부터 (x2, y2)까지의 합을 구하는 프로그램
		 * 
		 * 1 <= N <= 1024
		 * 1 <= M <= 100,000
		 * 1 <= 표에 채워진 수 <= 1000
		 * x1<x2, y1<y2
		 * 
		 * M줄에 걸쳐 (x1,y1)부터 (x2,y2)의 합 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N+1][N+1]; // 입력받을 수를 저장할 배열
		result = new int[N+1][N+1]; // 누적합을 저장할 배열
		
		// 초기값 할당
		for(int i=1;i<=N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1;j<=N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 누적합 구하기
		for(int i=1;i<=N;i++) {
			for(int j=1;j<=N;j++) {
				result[i][j] = board[i][j] + result[i-1][j] + result[i][j-1] - result[i-1][j-1];
			}
		}
		
		
		while(M-- > 0 ) {
			// 좌표값 할당
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			
			// 점화식
			// 총 누적합 - 범위 밖의 값 (x) - 범위 밖의 값(y) + 겹치는 값 
			int ans = result[x2][y2] - result[x1-1][y2] - result[x2][y1-1] + result[x1-1][y1-1];
			
			// 출력
			bw.write(ans+"\n");
			bw.flush();
		}
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int M;
	static int[][] board;
	static int[][] result;
	
}
