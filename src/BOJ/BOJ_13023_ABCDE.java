package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023_ABCDE {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 13023. ABCDE
		 * ----------------
		 * 
		 * N명이 캠프에 참가중(0 ~ N-1번)
		 * 일부 사람들은 친구
		 * 
		 * 다음과 같은 친구 관계를 가진 사람 A, B, C, D, E가 존재하는지 확인
		 * - (A, B) 친구
		 * - (B, C) 친구
		 * - (C, D) 친구
		 * - (D, E) 친구
		 * 
		 * 5 <= N <= 2000
		 * 1 <= M <= 2000
		 * 
		 * 0 <= a, b <= N-1
		 * a != b
		 * 
		 * 존재(1), 존재하지 않음(0) 출력
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		list = new ArrayList[N]; // 인접 리스트
		for(int i=0;i<N;i++) {
			list[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			// 무향 그래프
			list[a].add(b); // from to
			list[b].add(a); // to from
		}

		// 문제풀이
		for(int i=0;i<N;i++) { // 시작점을 모두 고려
			visited = new boolean[N]; // 정점을 탐색했는지 체크할 배열
			visited[i] = true; // 시작점 체크
			DFS(0, i);
		}

		// 출력
		sb.append(ans);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
		
	}

	static int N;
	static int M;
	static List<Integer>[] list;
	static boolean[] visited;
	static int ans;
	
	static void DFS(int depth, int from) {
		if(ans == 1) { // Backtracking
			return;
		}
		
		if(depth == 4) { // Base Case
			ans = 1;
			return;
		}
		
		for(int i : list[from]) { // 연결된 정점 탐색
			if(visited[i] == false) { // 방문하지 않은 정점이면
				visited[i] = true;
				DFS(depth+1, i); // 다음 탐색
				visited[i] = false;
			}
		}
	}
}
