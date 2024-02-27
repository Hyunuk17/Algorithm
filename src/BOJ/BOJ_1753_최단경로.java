package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1753_최단경로 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1753. 최단경로
		 * ----------------
		 * 
		 * 방향 그래프
		 * 시작점 주어짐 -> 다른 모든 정점으로의 최단 경로 구하기
		 * 
		 * 가중치 w: 1 <= w <= 10
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		
		graph = new ArrayList[V+1];
		for(int i=1;i<=V;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			graph[u].add(new Node(v, w));
		}
		
		// 문제풀이
		distance = new int[V+1]; // 촤단경로의 값을 저장할 배열
		Arrays.fill(distance, Integer.MAX_VALUE); // 초기화
		distance[K] = 0;  // 시작점 : 자기 자신의 경로는 0
		dijkstra(); // 다익스트라
		
		for(int i=1;i<=V;i++) { // 모든 정점으로의 최단 경로 값 출력
			if(distance[i] == Integer.MAX_VALUE) {
				sb.append("INF"); 
			}else {
				sb.append(distance[i]);
			}
			sb.append("\n");
		}
		
		
		// 출력
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int V;
	static int E;
	static int K;
	static List<Node>[] graph;
	static int[] distance;
	
	// 다익스트라 
	static void dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐 사용
		pq.add(new Node(K, 0)); // 시작 점
		boolean[] visited = new boolean[V+1]; // 방문 처리용 배열
		
		// pq가 null일 때까지 
		while(!pq.isEmpty()) {
			int now = pq.poll().v; // 현재 정점
			
			if(visited[now] == true) { // 방문한 점이면 Pass
				continue;
			}
			
			visited[now] = true; // 방문 처리
			
			for(Node next : graph[now]) { // 인접한 정점 탐색
				if(distance[next.v] > distance[now] + next.w) { // 거리 갱신
					distance[next.v] = distance[now] + next.w;
					pq.add(new Node(next.v, distance[next.v]));
				}
			}
		}
	}

	static class Node implements Comparable<Node>{
		int v;
		int w;
		
		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Node o) {
			return this.w - o.w;
		}
	}
}
