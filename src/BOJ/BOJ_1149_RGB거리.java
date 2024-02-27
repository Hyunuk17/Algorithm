package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1149_RGB거리 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 1149. RGB 거리
		 * -----------------
		 * 
		 * 집이 N개 있음
		 * 거리는 선분으로 나타낼 수 있음
		 * 
		 * 집을 빨강(0), 파랑(1), 초록(2) 중 하나의 색으로 칠
		 * 규칙을 만족하면서 모든 집을 칠하는 최솟값 구하기
		 * 1 != 2
		 * N != N-1
		 * i(2<=i<=N-1)번 집의 색은 i-1번, i+1번 집의 색과 같지 않아야 한다
		 * 
		 * 2 <= N <= 1,000
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][3];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<3;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 문제풀이
		dp = new int[N][3];
		dp[0][0] = arr[0][0];
		dp[0][1] = arr[0][1];
		dp[0][2] = arr[0][2];
		
		for(int i=1;i<N;i++) {
			// 빨강 : 이전까지 파랑 or 초록 + 현재 빨강
			dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
			
			// 파랑 : 이전까지 빨강 or 초록 + 현재 파랑
			dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
			
			// 초록 : 이전까지 빨강 or 파랑 + 현재 초록
			dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
		}
		
		// N-1번째 집에서의 비용 합의 최솟값 구하기
		min = Math.min(dp[N-1][0], Math.min(dp[N-1][1], dp[N-1][2]));
		
		// 출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}

	static int N;
	static int[][] arr;
	static int[][] dp;
	static int min = Integer.MAX_VALUE;
}
