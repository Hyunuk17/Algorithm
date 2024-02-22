import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/**
		 *  BOJ 11000. 강의실 배정
		 *  ------------------
		 *  
		 *  S_i에 시작해서 T_i에 끝나는 N개의 수업
		 *  최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다
		 *  
		 *  수업이 끝난 직후에 다음 수업 시작 가능
		 *  
		 *  1 <= N <= 200,000
		 *  0 <= S_i, T_i <= 10^9 := Integer값 범위
		 *  
		 *  최소 강의실의 개수를 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		S = new int[N];
		T = new int[N];
		arr = new int[N][2];
		visited = new boolean[N];

		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			S[i] = Integer.parseInt(st.nextToken());
			T[i] = Integer.parseInt(st.nextToken());
			arr[i][0] = S[i] ;
			arr[i][1] = T[i];
		}
			

				
		// 문제풀이
		Arrays.sort(arr, new Comparator<int[]>() { // 종료시간 순 정렬

			@Override
			public int compare(int[] o1, int[] o2) {
				if(o1[1] == o2[1]) {
					return o1[0] - o1[0];
				}
				return o1[1] - o2[1];
			}
		});
		
//		for(int[] i : arr) {
//			System.out.println(Arrays.toString(i));
//		}

		int cnt = N;
		int ans=1;
//		while(cnt > 0) {			
			int startTime = 0;
			ans++;
			for(int i=0;i<N;i++) {
				if(visited[i] == true) {
					continue;
				}
				
				if(startTime > arr[i][0]) { // 기존 end > start
//					System.out.print("["+startTime+" "+ arr[i][1]+ "]");
					ans++;
					startTime = arr[i][1];
					visited[i] = true;
					cnt--;
				}
			}
//			System.out.println();
//		}
		
		
		
		// 출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[] S;
	static int[] T;
	static int[][] arr;
	static boolean[] visited;
	static int min = Integer.MIN_VALUE;
}
