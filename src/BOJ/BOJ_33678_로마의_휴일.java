package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_33678_로마의_휴일 {
	/*
	 * BOJ 33678. 로마의 휴일 
	 * --------------------
	 * 
	 * [문제 설명] 
	 * 휴가 
	 * - N일 중, 연속된 기간을 선택하여 휴가
	 * 
	 * 일급 
	 * - 휴가 전 : a_i * X(보너스) 
	 * - 휴가 중 : 일급 없음 
	 * - 휴가 후 : a_i
	 * 
	 * 콘서트 
	 * - 티켓 비용 K 
	 * - 일급의 총합이 K 이상이 되어야 함 
	 * - K가 될 수 없다면 휴가를 나가지 않음
	 * 
	 * [입력] 
	 * N : 주어진 일 수 
	 * K : 콘서트 티켓 비용 
	 * X : 읽브 보너스 
	 * a : 일급
	 * 
	 * [출력] 
	 * 최대 다녀올 수 있는 휴가의 일 수 
	 * 휴가를 다녀올 수 없다면 -1
	 *
	 * [제한사항] 
	 * 2 <= N <= 200,000 
	 * 1 <= K <= 10^9 
	 * 1 <= X <= 10 
	 * 1 <= a_i <= 1,000
	 *
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		a = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		// 문제풀이
		/*
		 * 휴가 
		 * - i~j까지의 윈도우(가변 크기) 
		 * - 0~a_i, a_j~N-1까지의 합이 K 이상이 되도록 
		 * - 휴가일을 최대로 하기, Parametric Search
		 * 
		 * 이분탐색 
		 * - O(logN) 
		 * - K
		 */

		prefixSum = new int[N + 1];
		for (int i = 0; i < N; i++) {
			prefixSum[i + 1] = prefixSum[i] + a[i];
		}

		int max = -1;
		int left = 0;
		int right = N;
		while (left <= right) {
			// 휴가 일 수 구하기
			int mid = (left + right) / 2;

			if (check(mid)) { // 가능
				max = Math.max(max, mid);
				left = mid + 1; // 휴가 일 수 늘리기
			} else {
				right = mid - 1; // 휴가 일 수 줄이기
			}
		}

		sb.append(max == 0 ? -1 : max);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int K;
	static int X;
	static int[] a;
	static int[] prefixSum;

	static boolean check(int vacation) {
		// 특정 연속 휴가일 vacation이 가능한 모든 구간을 확인
		for (int start = 0; start <= N - vacation; start++) {
			int end = start + vacation - 1;

			int salary = prefixSum[start] * X + (prefixSum[N] - prefixSum[end + 1]);

			if (salary >= K) {
				return true;
			}
		}
		
		return false;
	}
}