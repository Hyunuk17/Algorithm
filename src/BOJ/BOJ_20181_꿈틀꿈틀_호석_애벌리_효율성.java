package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_20181_꿈틀꿈틀_호석_애벌리_효율성 {
	/*
	 * BOJ 20181. 꿈틀꿈틀 호석 애벌레 - 효율성 
	 * ----------------------------------
	 * 
	 * [문제 설명] 
	 * N개의 먹이가 일렬로 나열된 나뭇가지을 오른쪽으로 기어감 
	 * - 호석 애벌레 0위치에서 시작 
	 * - i번째 먹이는 오른쪽으로 i초 기어가 도달 
	 * - 매초 1만큼 오른쪽으로 무조건 진행
	 * 
	 * 애벌레가 먹이를 먹음 
	 * - 한 번 먹기 시작하면 연속적으로 계속 먹어야 함 
	 * - 누적 만족도가 K 이상이 되거나 먹을 먹이가 없어야 멈춤 
	 * - 최소 만족도 이상이 되면 K를 초과한 만큼 탈피 에너지 축적, 에너지 0이 됨
	 * 
	 * 축적된 탈피 에너지가 최대가 되도록
	 * 
	 * [입력] 
	 * 먹이 개수 N 
	 * 최소 만족도 K 
	 * 1~N번 먹이 만족도 배열
	 * 
	 * [출력] 
	 * - 축적된 탈피 에너지의 최댓값 출력 
	 * - 탈피를 한번도 할 수 없다면 0 출력
	 * 
	 * [제한사항] 
	 * 1 <= N <= 100,000 
	 * 1 <= K <= 10^8 
	 * 0 <= arr[i] <= 10^8
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
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		// 문제풀이
		// 누적합
		// - 특정 구간의 합이 k보다 크도록 선택해야 함
		// - N * N(최대) : 10^8 * 10^8 불가능
		// - 탈피 에너지는 누적됨 : int를 넘을 수 있음
		// dp[i] : i초에 에너지의 최댓값
		// 구간의 합을 구하는 방법 : 투 포인터
		dp = new long[N + 1]; // dp에서 시간을 나타낼 때 N까지로 배열 크기를 잡도록 하자

		int sum = arr[0]; // left~right 구간합
		int left = 0;
		int right = 1; // 시간

		while (right <= N) {
			if (sum >= K) { // 탈피 에너지가 있다면
				while (sum >= K) { // 탈피 에너지보다 작아질 때까지
					dp[right] = Math.max(dp[right], dp[left] + sum - K); // 최댓값을 기록하면서
					sum -= arr[left++]; // 왼쪽을 먹지 않는 경우 세기
				}
			} else { // 탈피 에너지가 없다면
				dp[right] = Math.max(dp[right], dp[right - 1]); // 오른쪽 먹이를 먹었는데 먹지 않은것이 더 큰 경우 판별

				if (right == N) { // right가 N이면 더 못먹기 때문에 break
					break;
				}

				sum += arr[right++]; // 오른쪽 먹기
			}
		}

		sb.append(dp[N]);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int K;
	static int[] arr;
	static long[] dp;
}
