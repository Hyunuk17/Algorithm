package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.StringTokenizer;

public class BOJ_1010_다리놓기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 1010. 다리 놓기
		 * -----------------
		 * 
		 * 서쪽(N) - 강 - 동쪽(M)
		 * 서쪽의 사이트와 동쪽의 사이트를 연결
		 * 
		 * N개 다리 짓기
		 * 다리는 겹칠 수 없음
		 * 
		 * 다리를 지을 수 있는 경우의 수 구하기
		 * 
		 * 0 < N <= M <= 30
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		while(T-- > 0) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			// 문제풀이
			// 앞에서부터 M개 중 N개를 뽑은 집합
			// 조합 : 30! / N!(30-N)!
			// i번째 원소를 뽑거나 뽑지 않거나
			// 시간제한 0.5초 M <= 30이라 시간복잡도 상으로 불가능

			// DP
			// Memoization을 이용한 시간 단축, O(N^2)
			// dp[i][j] = dp[i-1][j-1] + dp[i-1][j]
			dp = new int[M+1][N+1];
			topDown(M, N);
			
			// 출력
			sb.append(dp[M][N]).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}
	
	static int T;
	static int N;
	static int M;
	static int cnt;
	static int[][] dp;
	
	static int topDown(int i, int j) {
		if(dp[i][j] != 0) { // Momoization
			return dp[i][j];
		}
		
		if(i == j || j == 0) { // 안뽑거나, 전부 뽑는 경우
			return dp[i][j] = 1;
		}
		
		// 재귀
		return dp[i][j] = topDown(i-1, j-1) + topDown(i-1, j);
	}

}
