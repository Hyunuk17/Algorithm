package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_2001_D2파리퇴치 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 2001. 파리 퇴치 -----------------
		 * 
		 * N*N 배열 M*M 파리채 최대 파리를 잡을 수 있는 수 구하기
		 * 
		 * 5 <= N <= 15 2 <= M <= 2
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		
		// Test case
		for (int t = 1; t <= T; t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			// 초기화
			arr = new int[N + 1][N + 1];
			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 1; j <= N; j++) {
					// 누적합 구하기
					arr[i][j] = Integer.parseInt(st.nextToken()) + arr[i][j - 1] + arr[i - 1][j] - arr[i - 1][j - 1];
				}
			}

			// 탐색
			int sum = 0;
			max = 0;
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					// 파리채가 들어갈 수 있는 공간이 아니면 continue;
					if (i - M < 0 || j - M < 0) {
						continue;
					}

					// 누적합 배열에서 파리채 범위만큼만 추출하는 식
					sum = arr[i][j] - arr[i][j - M] - arr[i - M][j] + arr[i - M][j - M];
					// 최대값 구하기
					max = Math.max(max, sum);
				}
			}

			
			// 출력
			sb.append("#").append(t).append(" ").append(max).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
	}

	static int T;
	static int N;
	static int M;
	static int[][] arr;
	static int max = Integer.MIN_VALUE;

}
