package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_1247_최적경로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1247. 최적 경로
		 * -----------------
		 * 
		 * 회사에서 출발하여 N명의 고객을 방문하고 집에 돌아감
		 * 
		 * 2 <= N <= 10
		 * 0 <= x, y <= 100
		 * 두 위치 사이의 거리 : |x1-x| + |y1-y2|
		 * 
		 * 경로중 가장 짧은 것 찾기
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			min = Integer.MAX_VALUE;
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			arr = new int[N+2][2]; // 회사 + 집 + 고객... 
			visited = new boolean[N+2];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N+2;i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
 			}
			
			// 문제풀이
			re(arr[0][0], arr[0][1], 0, 0); // 회사에서 출발
			
			// 출력
			sb.append("#").append(t).append(" ").append(min).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}

	static int T;
	static int N;
	static int[][] arr;
	static boolean[] visited;
	static int min;
	
	static void re(int y, int x, int depth, int distance) {
		if(distance > min) { // Backtracking : 누적 거리가 최소거리를 넘으면 더 볼 필요 없다
			return;
		}
		if(depth == N) { // N명의 고객을 모두 돌았다면
			// 집으로
			// 현재 위치에서 집까지 거리 구하기
			int result = distance + Math.abs(y - arr[1][0]) + Math.abs(x - arr[1][1]); 
			min = Math.min(min, result);
			return;
		}
		
		// 고객을 모두 방문하기
		for(int i=2;i<=N+1;i++) {
			if(visited[i] == false) { // 방문하지 않은 고객만 방문
				int ny = arr[i][0];
				int nx = arr[i][1];
				int d = Math.abs(y - ny) + Math.abs(x - nx); // 현재 위치와의 거리
				
				visited[i] = true;
				re(ny, nx, depth+1, distance+d); // 거리 누적
				visited[i] = false;
			}
		}
	}
}
