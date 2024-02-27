package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_1463_1로만들기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 1463. 1로 만들기
		 * -----------------
		 * 
		 * 정수 N
		 * 3가지 연산
		 * - %3 == 0이면 3으로 나눈다
		 * - %2 == 0이면 2로 나눈다
		 * - 1을 뺀다
		 * 
		 * 1 <= M <=  10^6
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		
		// DP 배열
		dp = new int[N+1][3];
		for(int i=0;i<=N;i++) {
			Arrays.fill(dp[i], Integer.MAX_VALUE); // 최솟값을 구할 것 이므로 큰 값으로 초기화
		}
		
		// 초기값
		dp[1][0] = 0;
		dp[1][1] = 0;
		dp[1][2] = 0;
		
		// 문제풀이
		for(int i=2;i<=N;i++) { // 1을 N으로 만드는 경우
			if(i % 3 == 0) { // 1번: 3으로 나눌 수 있는 경우
				dp[i][0] = Math.min(dp[i/3][0], Math.min(dp[i/3][1], dp[i/3][2])) + 1;				
			}
			
			if(i % 2 == 0) { // 2번: 2로 나눌 수 있는 경우
				dp[i][1] = Math.min(dp[i/2][0], Math.min(dp[i/2][1], dp[i/2][2])) + 1; 
			}
			
			// 3번: -1을 하는 경우
			dp[i][2] = Math.min(dp[i-1][0], Math.min(dp[i-1][1], dp[i-1][2])) + 1;
		}
		
		// N을 만드는 데 연산한 횟수의 최소 값을 선택
		min = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
		

		// 출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[][] dp;
	static int min = Integer.MAX_VALUE;
}
