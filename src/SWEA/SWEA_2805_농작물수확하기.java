package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_2805_농작물수확하기 {
	/**
	 * SWEA 2805. 농작물 수확하기 ---------------------
	 * 
	 * 농장의 크기는 항상 홀수, N은 홀수 수확은 항상 농장의 크기에 딱 맞는 정사각형 마름모 형태로만 가능 수확량 출력
	 * 
	 * 1 <= N <= 49 Value : 0~5
	 * 
	 */

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Test Case
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			
			// board 초기화
			board = new int[N][N];
			for (int i = 0; i < N; i++) {
				String str = br.readLine();
				for (int j = 0; j < N; j++) {
					board[i][j] = str.charAt(j) - '0';
				}
			}

			// 문제풀이
			// +-할 값 
			int diff = 0;
			// 수확량을 저장할 값
			int sum = 0;
			// 깊이 탐색
			for (int i = 0; i < N; i++) {
				// 중간 값을 기준으로 좌우 차이만큼 출력
				for (int j = N / 2 - diff; j <= N / 2 + diff; j++) {
					sum += board[i][j];
				}
				
				// 아직 y축 중간을 넘어가지 않았다면
				if(i < N/2) {
					// 확장하는 방향
					diff++;
				}
				// 탐색이 y축 중간을 넘었다면
				else {
					// 축소하는 방향
					diff--;
				}
				
				
			}

			// 출력
			sb.append("#").append(t).append(" ").append(sum).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
	}

	static int T;
	static int N;
	static int[][] board;
	static int ans;
}
