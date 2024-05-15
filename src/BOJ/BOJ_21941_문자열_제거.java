package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_21941_문자열_제거 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 21941. 문자열 제거 
		 * --------------------
		 * 
		 * [문제 설명] 
		 * 지우고 싶은 문자열 S 
		 * 지울 수 있는 문자열 A1, A2 ... Am 
		 * - 각 문자열 Ai는 점수 Xi를 가짐
		 * 
		 * 문자열 S를 '삭제 연산'을 이용하여 모두 제거 
		 * - 문자열 S의 부분 문자열 중, 문자열 Ai가 존재한다면 해당하는 부분 삭제, Xi 점수 획득 
		 * - 문자열 S에서 문자 하나를 지우고 점수 1점 획득 
		 * - '_' : 문자가 지워진 자리
		 * 
		 * 문자열 S를 지워 얻을 수 있는 최대 점수 계산
		 * 
		 * [제한사항] 
		 * 1 <= S.length <= 1,000 
		 * 1 <= M <= 100 
		 * 1 <= Ai.length <= 100 
		 * 1 <= Xi <= 10,000
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		String S = br.readLine();
		int M = Integer.parseInt(br.readLine());
		String[] A = new String[M];
		int[] X = new int[M];

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String a = st.nextToken();
			int x = Integer.parseInt(st.nextToken());
			A[i] = a;
			X[i] = x;
		}

		// 문제풀이
		// DP
		// S의 특정 문자부터 A[i]를 지우기 : + X[i]
		// S의 특정 문자를 지우기 : + 1

		int[] dp = new int[S.length() + 1];
		
		for (int i = 0; i < S.length(); i++) {
			dp[i + 1] = Math.max(dp[i + 1], dp[i] + 1); // 한문자 지우기  
			
			for (int j = 0; j < M; j++) {
				if (S.startsWith(A[j], i)) { // i번째부터 문자열이 A[j]인지 확인
					dp[i + A[j].length()] = Math.max(dp[i + A[j].length()], dp[i] + X[j]);
				}
			}
		}

		// 출력
		sb.append(dp[S.length()]);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}

}
