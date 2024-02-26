package SWEA;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_3124_최소스패닝트리_Prim {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 3125. 최소 스패닝 트리
		 * 
		 * MST : 주어진 그래프의 모든 정점을 연결하는 부분 그래프(신장 트리) 중 가중치의 합이 최소
		 * 
		 * 1 <= T <= 10
		 * 1 <= V <= 100,000
		 * 1 <= E <= 200,000
		 * 
		 * 정점 A, 정점 B, 간선 C
		 * |C| <= 1,000,000
		 * 
		 * MST의 가중치 출력
		 * 
		 * Prim을 이용한 구현 실습
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			total = 0; // MST의 가중치 값
	
			// 그래프 생성
			graph = new ArrayList[V+1];
			for(int i=1;i<=V;i++) {
				graph[i] = new ArrayList<>();
			}
			
			// 간선 삽입
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken()); // 가중치
				graph[a].add(new Node(b, weight));
				graph[b].add(new Node(a, weight));
			}
			
			// 문제풀이
			Prim(1); // Prim을 이용, 임의의 정점 1에서 시작
			
			// 출력
			sb.append("#").append(t).append(" ").append(total).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
					
		bw.close();
		br.close();

	}

	static int T;
	static int V;
	static int E;
	static long total;
	static boolean[] visited;
	static List<Node>[] graph;
	static PriorityQueue<Node> pq;
	
	
	// Prim: MST 구하기
	static void Prim(int start) {
		pq = new PriorityQueue<>(); // 우선순위 큐 이용
		visited = new boolean[V+1]; // 방문체크
		pq.add(new Node(start, 0)); // 시작 정점 삽입
		
		while(!pq.isEmpty()) { // pq가 빌때까지 
			Node now = pq.poll(); // 현재 정점
			if(visited[now.vertex] == true) { // 이미 방문한 정점이면 Pass
				continue;
			}
			
			visited[now.vertex] = true; // 방문 체크
			total += now.weight; // 가중치 합
			
			// 인접 노드 탐색
			for(Node destination : graph[now.vertex]) {				
				if(visited[destination.vertex] == true) { // 방문한 정점이면 continue
					continue;
				}
			
				// pq에 Push 
				pq.add(destination);
			}
		}
		
	}
	
	// 간선(목적지, 가중치)
	static class Node implements Comparable<Node>{
		int vertex;
		int weight;
		
		public Node(int vertex, int weight) {
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return this.weight - o.weight;
		}
	}
}
