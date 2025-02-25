package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class BOJ_12026_BOJ_거리 {
	/*
	 * BOJ 12026. BOJ 거리 
	 * ------------------
	 * 
	 * [문제 설명] 
	 * BOJ 거리 
	 * - 1~N개 블록 일렬로 놓여진 도로 
	 * - 각 블록에서는 'B', 'O', 'J' 중 하나가 쓰여 있음 
	 * - 1번 블록은 'B' 고정
	 * 
	 * 점프 
	 * - 다른 블록으로 이동 
	 * - k칸을 점프하기 위해 k*k 에너지의 양이 필요 
	 * - B -> O -> J -> B 순서로 이동 가능
	 * 
	 * [입력] 
	 * N
	 * 
	 * [출력] 
	 * 1~N에 도탈하기 위해 필요한 에너지의 양의 최솟값 
	 * 만날 수 없는 경우 -1
	 *
	 * [제한사항] 
	 * 1 <= N <= 1,000
	 *
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		String str = br.readLine();
		arr = new char[N + 1];
		for (int i = 1; i <= N; i++) {
			arr[i] = str.charAt(i - 1);
		}

		// 문제풀이
		/*
		 * 점프 
		 * - 현재 지점의 문자 다음 값 탐색 : B -> O -> J -> B 
		 * - 그 지점에 도달하기 위한 에너지 누적
		 * 
		 * 에너지의 최솟값 
		 * - dp[i] : i지점에 도달하기 위해 필요한 에너지의 최솟값 
		 * - Math.min으로 갱신
		 * 
		 * 
		 */

		dp = new int[N + 1];
		Arrays.fill(dp, INF);
		dp[1] = 0; // 시작점
		for (int i = 1; i < N; i++) {
			char next = ' ';
			if (arr[i] == 'B') {
				next = 'O';
			} else if (arr[i] == 'O') {
				next = 'J';
			} else if (arr[i] == 'J') {
				next = 'B';
			}

			for (int j = i + 1; j <= N; j++) {
				if (arr[j] == next) {
					int k = j - i;
					dp[j] = Math.min(dp[j], dp[i] + k * k);
				}
			}
		}

		sb.append(dp[N] == INF ? -1 : dp[N]);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static char[] arr;
	static int[] dp;
	static int INF = 123_456_789;
}