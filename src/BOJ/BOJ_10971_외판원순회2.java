package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10971_외판원순회2 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 *  BOJ 10971. 외파원 순회2
		 *  -------------------
		 *  
		 *  Traveling Salesman Problem(TSP)
		 *  N개 도시
		 *  한 도시에서 출발해 N개의 도시를 모두 거쳐 다시 원래의 도시로 돌아오는 경로
		 *  중복 방문 불가
		 *  가장 적은 비용을 들이는 예행 계획 구하기
		 * 
		 *  양방향 그래프
		 *  2 <= N <= 10
		 *  1 <= w <= 1,000,000
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		W = new int[N][N];
		
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 문제풀이
		// 완전 탐색 : O(n!)
		// DP + 비트마스킹 : O(n^2 * 2^n)
		// N <= 10이므로 완전 탐색을 이용해도 풀 수 있을 것
			
		visited = new boolean[N]; // 방문 체크를 위한 배열
		visited[0] = true; // 시작점 방문 처리
		TSP(0, 1, 0); 
		
		// 출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
		
	}
	
	static int N;
	static int[][] W;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	static void TSP(int start, int depth, int cost) {
		if(depth == N) { // 모든 정점을 방문했다면
			if(W[start][0] != 0) { // 끝점과 시작점이 이어져 있는지 확인
				min = Math.min(min, cost + W[start][0]); // 비용 최신화
			}
			return;
		}
		
		// 다른 모든 정점에 대해 방문 가능한지 확인
		for(int i=0;i<N;i++) {
			if(visited[i] == true) { // 이미 방문한 정점
				continue;
			}
			
			if(W[start][i] == 0) { // 간선 없음
				continue;
			}
			
			visited[i] = true;
			TSP(i, depth+1, cost + W[start][i]); // 다음 정점 방문
			visited[i] = false;
		}
	}
}
