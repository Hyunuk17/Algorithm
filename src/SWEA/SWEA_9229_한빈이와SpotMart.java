package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_9229_한빈이와SpotMart {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 9229. 한빈이와 Spot Mart --------------------------
		 * 
		 * 과자 2봉지 양 손에 하나씩 들기
		 * 
		 * 마트에 N개의 과자 봉지가 있음, 각 과자 봉지는 a_i그램 최대한 무게가 많이 나가는 과자 봉지 고르기 두 봉지의 무게가 M그램을 초과할
		 * 수 없음
		 * 
		 * 들고 다닐 수 있는 과자들의 최대 무게 합, 방법이 없으면 -1 과자를 정확히 두 봉지 사야 함
		 * 
		 * 2 <= N <= 1000 2 <= M <= 2,000,000 1 <= a_i <= 10,000,000
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N];
			max = -1;

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			// 문제풀이
			solve();

			// 출력
			sb.append("#").append(t).append(" ").append(max).append("\n");

			bw.write(sb.toString());
			bw.flush();
		}

		bw.close();
		br.close();
	}

	static int T;
	static int N;
	static int M;
	static int[] arr;
	static int max;

	static void solve() {
		// 완전 탐색
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 다른 번호 일 때
				if (i != j) {
					// 과자 봉지의 합이
					int sum = arr[i] + arr[j];
					// 최대 무게를 넘기지 않을 때
					if(M >= sum) {		
						// 최대 값 구하기
						max = Math.max(sum, max);
					}
				}
			}
		}
		// 좋은 코드는 아닌 것 같음
	}
}
