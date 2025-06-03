package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_33756_88888 {
	/*
	 * BOJ 33756. 88888 
	 * ----------------
	 * 
	 * [문제 설명] 
	 * 8-넘버 
	 * - 십진법으로 표현했을 때, 모든 자릿수가 8인 수
	 * 
	 * 행운의 수 
	 * - 8개 이하의 8-넘버의 합으로 표현 가능한 양의 정수
	 * 
	 * [입력] 
	 * T : 테스트 케이스의 수 
	 * N : 양의 정수
	 * 
	 * [출력] 
	 * 주어진 N이 행운의 수라면 Yes, 아니라면 No
	 *
	 * [제한사항] 
	 * T <= 100,000 
	 * 1 <= N <= 1,000,000,000,000,000,000
	 *
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());
		// 문제풀이
		/*
		 * 8개 이하, 8-넘버의 수 
		 * - depth가 8 이하 
		 * - N 이하의 8-넘버 18개 => 18*8, 약 11억 => TLE
		 * 
		 * 시간복잡도 
		 * - T : 10^5 
		 * - N : 10^18 
		 * - O(T * logN) : 18*10^5 
		 * - logN 이하로 풀어야 함 => Greedy? 
		 * 
		 */

		number = new long[18];
		number[0] = 8;
		for (int i = 1; i < 18; i++) {
			number[i] = number[i-1] * 10 + 8;
		}

		while (T-- > 0) {
			N = Long.parseLong(br.readLine());
			flag = false;
			DFS(0, N);

			if (flag == true) {
				sb.append("Yes").append("\n");
			} else {
				sb.append("No").append("\n");
			}
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int T;
	static long N;
	static long[] number;
	static boolean flag;

	static void DFS(int depth, long remain) {
		if (depth > 8) {
			return;
		}

		if (remain == 0) {
			flag = true;
			return;
		}

		for (int i = 17; i >= 0; i --) {
			if (remain < number[i]) {
				continue;
			}

			DFS(depth + 1, remain - number[i]);
			break; // Greedy : 가능한 가장 큰수를 뺴면, 이후는 고려하지 않아도 된다
		}
	}
}