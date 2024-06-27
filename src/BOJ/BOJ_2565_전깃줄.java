package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2565_전깃줄 {
	/*
	 * BOJ 2565. 전깃줄 
	 * --------------
	 * 
	 * [문제 설명] 
	 * 두 전봇대 A와 B 
	 * - 두 전봇대 사이에 전깃줄 추가 
	 * - 전깃줄이 교차하지 않도록 몇개의 전깃줄을 제거 
	 * 없애야 하는 전깃줄 최소 개수 구하기
	 * 
	 * [입력] 
	 * 1 <= n <= 100 : 전깃줄 개수 
	 * 1 <= arr[i] <= 500 : 연결되는 위치, 한 위치에 최대 1개
	 *
	 */

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		arr = new int[N][2];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			arr[i][0] = A;
			arr[i][1] = B;
		}

		// 문제풀이
		// 없애야 하는 줄의 개수 = 전체 줄의 개수 - 안겹치게 놓은 줄의 개수
		// A로 정렬하고
		// i번쨰 전깃줄을 선택했을 때, 이전까지의 최대 +1
		dp = new int[N]; // 위치 N에서의 최대 전깃줄 개수
		Arrays.fill(dp, 1); // 초기화, 적어도 i번째에서 전깃줄 1개는 놓을 수 있음

		Arrays.sort(arr, (o1, o2) -> { // 정렬 A 기준
			return o1[0] - o2[0];
		});

		for (int i = 0; i < N; i++) { // 기준 : A 순회
			for (int j = 0; j < i; j++) { // 내 앞의 A까지 순회
				if (arr[i][1] > arr[j][1]) { // 현재 놓는 전깃줄의 B가 이전의 B보다 크다면(놓을 수 있다면)
					dp[i] = Math.max(dp[i], dp[j] + 1); // +1
				}
			}
		}

		for (int i = 0; i < N; i++) {
			max = Math.max(max, dp[i]);
		}
		sb.append(N - max);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int[][] arr;
	static int[] dp;
	static int max;
}
