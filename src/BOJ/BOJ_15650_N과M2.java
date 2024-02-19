package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_15650_N과M2 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 15650. N과 M(2)
		 * ------------------
		 * 
		 * 1 <= M <= N <= 8
		 * 
		 * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
		 * 고른 수열은 오름차순
		 * 
		 */
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		// 길이가 M인 수열을 저장할 배열 할당
		result = new int[M];
		
		// 문제풀이
		combination(0, 1);
		
		// 출력
		bw.write(sb.toString());
		bw.flush();
	}
	
	static int N;
	static int M;
	static int[] result;
	static StringBuilder sb;

	// start: 중복제거
	static void combination(int depth, int start) {
		// 기저조건 
		if(depth == M) {
			// 출력
			for(int i : result) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		// 현재 수 부터 시작
		for(int i=start;i<=N;i++) {
			result[depth] = i; // 수열에 현재 수 저장
			combination(depth+1, i+1); // 중복을 허용하지 않으므로 i+1
		}
	}
}
