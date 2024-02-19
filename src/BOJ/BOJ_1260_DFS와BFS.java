package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1260_DFS와BFS {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1260. DFS와 BFS 
		 * ------------------
		 * 
		 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과 출력
		 * 
		 * 정점 N개 
		 * 시작 정점 V 
		 * 방문할 수 있는 정점이 여러개라면 번호가 작은 순서
		 * 
		 * 두 정점 사이에 여러 개의 간선 존재 가능 
		 * 양방향 간선
		 * 
		 * 1 <= N <= 1,000 
		 * 1 <= M <= 10,000
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N+1]; // 각각의 정점과 그 간선을 담을 리스트의 배열
		for(int i=0;i<=N;i++) {
			graph[i] = new ArrayList<>(); // 각 정점마다 리스트 할당
		}
		
		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int v = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			graph[v].add(v2); // 양방향 그래프
			graph[v2].add(v); // 양쪽 리스트에 모두 삽입
		}
		
		// 문제풀이
		// 정렬 : 번호가 작은 순으로 탐색하기 위헤
		for(List<Integer> list : graph) {
			Collections.sort(list);
		}
		
		// DFS
		sb = new StringBuilder();
		visited = new boolean[N+1];
		DFS(V); // DFS 실행
		sb.append("\n");
		bw.write(sb.toString());
		bw.flush();
		
		// BFS
		sb = new StringBuilder(); // StringBuilder 초기화
		visited = new boolean[N+1]; // 중복체크 초기화
		BFS(V); // BFS 실행
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static int V;
	static List<Integer>[] graph;
	static boolean[] visited;
	static StringBuilder sb = new StringBuilder();
	
	// DFS
	static void DFS(int V) {
		visited[V] = true; // 중복 체크
		sb.append(V).append(" "); // 출력
		
		for(int i=0;i<graph[V].size();i++) { // 현재 정점에서 시작하는 리스트에 접근
			int n = graph[V].get(i); // 간선으로 이어지는 정점
			if(visited[n] == false) { // 아직 방문하지 않은 정점이면
				DFS(n);
			}
		}
	}
	
	// BFS
	static void BFS(int V) {
		Queue<Integer> queue = new LinkedList<>(); // Queue 할당
		queue.add(V); // 시작 정점 
		visited[V] = true; // 방문 체크
		
		while(!queue.isEmpty()) { // 더 이상 방문할 정점이 없을 때까지 반복
			int n = queue.poll(); // 현재 정점
			sb.append(n).append(" "); // 출력
			 
			for(int i=0;i<graph[n].size();i++) { // 현재 정점에서 시작하는 리스트에 접근
				int nv = graph[n].get(i); // 간선으로 이어지는 정점
				if(visited[nv] == false) { // 아직 방문하지 않은 정점이면
					visited[nv] = true;
					queue.add(nv); // Queue에 추가
				}
			}
		}
	}
}
