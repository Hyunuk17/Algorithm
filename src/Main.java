import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	/*
	 * BOJ 21757. 나누기 
	 * ---------------
	 * 
	 * [문제 설명] 
	 * N개의 정수 수열 A1, A2 ... AN 
	 * - 수열을 각각이 연속된 네 부분으로 분할 
	 * - 각 부분은 최소 하나의 수를 포함
	 * - 각 부분의 합은 모두 같음
	 * 
	 * 가능한 방법의 개수를 구하기
	 * 
	 * 
	 * [입력] 
	 * 수열의 길이 N 
	 * N개의 정수 A1, A2 ... AN
	 * 
	 * [제한사항] 
	 * 4 <= N <= 100,000 
	 * -1,000 <= Ai <= 1,000
	 * 
	 */

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 문제풀이
		// 배열을 4개로 나누기
		// - 100,000에서 3가지 수 뽑기, 10^5 ^ 3 TLE
		// - N=100,000 => O(NlogN) 안쪽으로 해결해야 함

		// 1. 누적합은 4의 배수여야 함
		// 2. 중간에 0이라면?
		// 3. DP
		prefix = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			prefix[i] = prefix[i - 1] + arr[i - 1];
		}
		dp = new long[N + 1][4];

		long ans = 0;
		// 총 합이 4로 나누어 떨어져야 함
		if (prefix[N] % 4 == 0) {
			// 총합이 0인 경우
			if (prefix[N] == 0) {
				// 누적합이 0인 부분 3개 선택
				long zero = 0;
				for (int i = 1; i <= N; i++) {
					if (prefix[i] == 0) {
						zero++;
					}
				}
				ans = (zero - 1) * (zero - 2) * (zero - 3) / 6; // zeroC3
			}
			// 총합이 0이 아닌 경우
			else {
				x = prefix[N] / 4; // 누적합을 4로 나눈값
				for (int i = 0; i <= N; i++) { // 누적합 초기화
					Arrays.fill(dp[i], -1);
				}

				for (int i = 1; i <= N; i++) {
					if (prefix[i] == x) { // 탐색 시작
						ans += topDown(i + 1, 1); // 1번쨰
					}
				}
			}
		}

		sb.append(ans);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int[] arr;
	static int[] prefix;
	static int x;
	static long[][] dp; // i번쨰까지 수를 j개로 나누는 경우의 수

	static long topDown(int idx, int cnt) {
		if (dp[idx][cnt] >= 0) { // Memoization
			return dp[idx][cnt];
		}

		if (idx > N) {
			return dp[idx][cnt] = 0;
		}

		if (cnt == 3) { // 마지막 탐색의 경우
			if (prefix[N] - prefix[idx - 1] == x) { // 남은 구간의 합이 prefix[N-1]/4인지 확인
				return dp[idx][cnt] = 1; // 맞으면
			} else {
				return dp[idx][cnt] = 0;
			}
		}

		// 마지막 탐색이 아닌 경우
		long result = 0;
		for (int i = idx; i <= N; i++) {
			long tmp = prefix[i] - prefix[idx - 1]; // i~idx-1구간 계산

			if (tmp == x) { // 배수이면
				result += topDown(i + 1, cnt + 1); // 다음 탐색 진행
			}
		}

		return dp[idx][cnt] = result;
	}
}
