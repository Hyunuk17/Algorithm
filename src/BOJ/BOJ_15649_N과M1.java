package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_15649_N과M1 {

	public static void main(String[] args) throws IOException {
		/**
		 * 백준 15649 : N과 M(1)
		 * 
		 * 자연수 N과 M
		 * 1부터 N까지 자연수 중에서 중복없이 M개를 고른 수열
		 * 
		 * 1<= M <= N <= 8
		 * 
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 숫자의 사용여부를 판단
		visited = new boolean[N+1];
		// 결과를 저장할 배열
		result = new int[M];
		
		// 재귀
		recur(0);

		bw.flush();
	}

	static int N;
	static int M;
	static boolean[] visited;
	static int[] result;
	static BufferedWriter bw;
	
	static void recur(int depth) throws IOException {
		// 수열의 길이에 도달
		if(depth == M) {
			for(int i : result) {
				bw.write(i+" ");
			}
			bw.write("\n");
			return;
		}
		
		// N까지의 수 중에 넣을 값 선택
		for(int i=1;i<=N;i++) {
			// 앞의 수열에서 사용하지 않은 값이면
			if(visited[i] == false) {
				// 사용했다고 표시하고
				visited[i] = true;
				// 현재 수열에 저장
				result[depth] = i;
				// 다음에 저장할 번호로 재귀 호출
				recur(depth+1);
				// 사용 후 반납
				visited[i] = false;
			}
		}
	}
}
