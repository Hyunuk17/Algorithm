package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_11659_구간합4 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 11659. 구간 합 구하기 4
		 * ---------------------
		 * 
		 * 수 N개
		 * i번째 수 부터 j번째 수 까지 합을 구하기
		 * 
		 * 1 <= N <= 100000
		 * 1 <= M <= 100000
		 * 1 <= i <= j <= N
		 * 
		 * M개의 줄에 i~j까지 수의 합을 출력
		 * 
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N+1];
		
		// 문제풀이
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			// 전까지의 값에 현재 입력값을 더해 저장
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken());
		}

		while(M-- > 0) {
			st = new StringTokenizer(br.readLine());
			// 시작점 i와 끝점 j
			start = Integer.parseInt(st.nextToken());
			end = Integer.parseInt(st.nextToken());
			
			// j까지의 누적합에서 i-1까지의 누적합의 차
			bw.write(arr[end]-arr[start-1]+"\n");
			bw.flush();
		}
	}
	
	static int N;
	static int M;
	static int[] arr;
	static int start;
	static int end;

}
