package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_18113_그르다_김가놈 {
	/*
	 * BOJ 18113. 그르다 김가놈 
	 * ---------------------
	 * 
	 * [문제 설명] 
	 * 공장 
	 * - 김밥 N개 
	 * - 양 끝 k만큼 자르기 
	 * - 김밥의 길이가 2k보다 작다면, 한쪽만 자르기 
	 * - 자른 후 김밥의 길이가 k와 같거나 작으면, 폐기
	 * 
	 * 손질된 김밥 
	 * - 일정한 길이 p로 자르기 
	 * - p로 자른 최소 M개 조각 만들기
	 * 
	 * [입력] 
	 * N : 김밥의 개수 
	 * K : 꼬다리의 길이 
	 * M : 김밥조각의 최소 개수 
	 * L : 김밥의 길이 (N개)
	 * 
	 * [출력] 
	 * 김밥조각 P의 최댓값 
	 * 만족하는 P가 없으면 -1
	 *
	 * [제한사항] 
	 * 1 <= N <= 10^6 
	 * 1 <= K, M <= 10^9 
	 * 1 <= L <= 10^9
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
		M = Integer.parseInt(st.nextToken());

		L = new int[N];
		for (int i = 0; i < N; i++) {
			L[i] = Integer.parseInt(br.readLine());
		}

		// 문제풀이
		/*
		 * 최대의 P값을 찾는 문제 
		 * - L <= 10^9 : Integer 범위를 의미 
		 * - N <= 10^6 : O(nlogn)까지 허용
		 * 
		 * 시간복잡도  
		 * - O(logn) : 이분 탐색 
		 * - O(n) : 조건 판별
		 */

		int low = 1;
		int high = 1_000_000_000;
		int max = 0;

		while (low <= high) {
			int mid = (low + high) / 2;
			int cnt = 0;

			for (int i = 0; i < N; i++) {
				int kimbob = L[i];

				if (kimbob <= K) { // 폐기
					continue;
				}

				// 김밥 손질
				int trimmedKimbob;
				if (kimbob < 2 * K) {
					trimmedKimbob = kimbob - K;
				} else {
					trimmedKimbob = kimbob - 2 * K;
				}

				// 김밥 자르기
				cnt += trimmedKimbob / mid;
			}

			// 김밥조각의 개수 파악
			if (cnt >= M) { // 더 길게 잘라볼 수 있음
				low = mid + 1;
				max = Math.max(max, mid);
			} else { // M개보다 적음, 더 짧게 잘라야 함
				high = mid - 1;
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
	static int M;
	static int[] L;
}