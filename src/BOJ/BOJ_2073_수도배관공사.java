package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2073_수도배관공사 {
	/*
	 * BOJ 2073. 수도배관공사 
	 * -------------------
	 * 
	 * [문제 설명] 
	 * 파이프 
	 * - P개 
	 * - L : 길이 
	 * - C : 용량
	 * 
	 * 수도관 
	 * - 거리 D만큼 떨어진 곳에서 물을 끌어오기
	 *  - 파이프를 일렬로 연결하여 길이 D 생성 
	 *  - 용량(파이프 중 최솟값), 길이(파이프 총합)
	 * 
	 * [입력] 
	 * D : 거리 
	 * L : 파이프 길이 
	 * C : 파이프 용량
	 * 
	 * [출력] 
	 * 수도괸 길이가 정확히 D일 때, 최대 수도관 용량
	 *
	 * [제한사항] 
	 * 7 <= D <= 100,000 
	 * 1 <= P <= 350 
	 * 1 <= L_i, C_i <= 2^23
	 *
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		D = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		L = new int[P + 1];
		C = new int[P + 1];

		for (int i = 1; i <= P; i++) {
			st = new StringTokenizer(br.readLine());
			int l = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			L[i] = l;
			C[i] = c;
		}

		// 문제풀이
		/*
		 * 수도관 만들기 
		 * - i번째 파이프를 선택할지 안할지 
		 * - DP[i][j] : i번쨰 파이프까지 확인하여 길이 j를 만들었을 떄, 수도관의 최대 용량
		 * 
		 * 시간복잡도 
		 * - 350 * 100,000 : O(PD)
		 */
		dp = new int[P + 1][D + 1];
		for (int i = 0; i <= P; i++) {
			Arrays.fill(dp[i], -1);
		}

		topDown(P, D);

		sb.append(dp[P][D]);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int D;
	static int P;
	static int[] L;
	static int[] C;
	static int[][] dp;

	static int topDown(int p, int d) {
		// Base Case
		if (p == 0 || d < 0) {
			return 0;
		}

		if (d == 0) {
			return Integer.MAX_VALUE;
		}

		// Memoization
		if (dp[p][d] != -1) {
			return dp[p][d];
		}

		// Recurrence Relation
		return dp[p][d] = Math.max(topDown(p - 1, d), Math.min(topDown(p - 1, d - L[p]), C[p]));
	}
}