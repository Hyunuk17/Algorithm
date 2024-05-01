package src.BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2458_키순서 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 2458. 키 순서
		 * ---------------
		 * 
		 * N명 학생, 키 모두 다름
		 * 2명씩 키 비교
		 *
		 * 자신의 키가 몇번째인지 알 수 있는 학생 수 구하기
		 * 
		 * 2 <= N <= 500
		 * 0 <= M <= N(N-1) : 비교횟수, 간선
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		distance = new int[N+1][N+1];
		for(int i=1;i<=N;i++) { // 초기화 MAX 값
			Arrays.fill(distance[i], INF);
		}
		for(int i=1;i<=N;i++) { // 자기 자신 거리 0
			distance[i][i] = 0;
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			distance[u][v] = Math.min(distance[u][v], 1); // 간선이 존재
		}
		
		// 문제풀이
		// 위상정렬인줄 알았으나 정확한 순서 판별 필요
		// - 한 학생보다 작은 학생, 큰학생을 전부 알아야 함
		// - 몇번째인지 구하는 것이 아니라, 몇번째인지 알 수 있는지를 구하는 문제
		
		// floyd-warshall
		// - 한 정점과 다른 모든 정점 사이의 거리 구하기
		// - 한 정점에서 다른 모든 정점과 이어져 있다면, 그 정점은 몇번째인지 알 수 있는 정점
		// - N^3 = 500^3 := 10^8
		
		for(int k=1;k<=N;k++) { // 중간점 k를 기준으로
			for(int i=1;i<=N;i++) { // i부터
				for(int j=1;j<=N;j++) { // j까지의 거리 갱신
					if(distance[i][j] > distance[i][k] + distance[k][j]) {
						distance[i][j] = distance[i][k] + distance[k][j];
					}
				}
			}
		}
		
		for(int i=1;i<=N;i++) {
			int cnt = 0;
			for(int j=1;j<=N;j++) {
				if(distance[i][j] != INF || distance[j][i] != INF) {
					cnt++;
				}
			}
			if(cnt == N) {
				ans++;
			}
		}
		
		
		// 출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
//	static int T;
	static int N;
	static int M;
	static int[][] distance;
	static int ans;
	static final int INF = 501; // Integer.MAX_VALUE는 Overflow 발생
}
