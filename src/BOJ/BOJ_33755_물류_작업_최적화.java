package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_33755_물류_작업_최적화 {
	/*
	 * BOJ 33755. 물류 작업 최적화 
	 * ------------------------
	 * 
	 * [문제 설명] 
	 * 물류 창고 
	 * - 1~N시간
	 * 
	 * 순수 물류랑 
	 * - A_i 
	 * - 시각 i에서, (들어온 물류량 - 나간 물류랑)
	 * 
	 * 아르바이트 고용 
	 * - 해당 시각 t를 포함하는 [l, r]구간 
	 * - 처리하는 총 물류량을 최대로 만들기
	 * 
	 * 
	 * [입력] 
	 * N : 총 시간 
	 * A[] : 각 시각에 해당되는 순수 물류량
	 * 
	 * [출력] 
	 * 시각 t(1,2...,N)에 대해 각각, 해당 시각을 포함하는 구간 중 총 물류량이 최대가 되는 값
	 *
	 * [제한사항] 
	 * 1 <= N <= 300,000 
	 * -1,000 <= A_i <= 1,000
	 *
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		A = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}

		// 문제풀이
		/*
		 * 카데인 알고리즘(Kadane's Algorithm) 
		 * - 구간 [l, r]에서 r번째 원소를 포함할 때, 
		 * - 1. 이전 최대 부분합 + 현재 원소 
		 * - 2. 현재 원소 
		 * - 중 최대 값을 가지는 것을 최대 부분합으로 선택
		 * 
		 * T를 포함하는 구간에서 최대 값 찾기 
		 * - 1. [L, T]까지의 최대 부분합 
		 * - 2. [T, R]까지의 최대 부분합 
		 * - 3. 중복된 (-T) 제거
		 * 
		 */

		leftMax = new int[N];
		leftMax[0] = A[0];
		for (int i = 1; i < N; i++) {
			leftMax[i] = Math.max(A[i], leftMax[i - 1] + A[i]);
		}

		rightMax = new int[N];
		rightMax[N - 1] = A[N - 1];
		for (int i = N - 2; i >= 0; i--) {
			rightMax[i] = Math.max(A[i], rightMax[i + 1] + A[i]);
		}

		for (int i = 0; i < N; i++) {
			int max = leftMax[i] + rightMax[i] - A[i];
			sb.append(max).append(" ");
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int[] A;
	static int[] leftMax;
	static int[] rightMax;
}