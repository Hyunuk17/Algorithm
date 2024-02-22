package SWEA;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_3124_최소스패닝트리_Kruskal {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 3124. 최소 스패닝 트리
		 * ----------------------
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
		 * Kruskal을 이용한 구현 실습
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
			min =0;
			
			parents = new int[V+1]; // 각 집합, 루트 노드를 가리킴
			edges = new Node[E]; // 간선 목록
			
			for(int i=0;i<E;i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken()); 
				int b = Integer.parseInt(st.nextToken());
				int wegiht = Integer.parseInt(st.nextToken()); // 가중치
				edges[i] = new Node(a, b, wegiht);
			}
			
			// 문제풀이
			MST_Kruskal();
			
			// 출력
			sb.append("#").append(t).append(" ").append(min).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}

	static int T;
	static int V;
	static int E;
	static int[] parents;
	static Node[] edges;
	static boolean[] visited;
	static long min;
	
	// Kruskal 알고리즘을 이용한 MST 구하기
	static void MST_Kruskal() {
		make_Set(); // 집합 생성
		
		Arrays.sort(edges); // 가중치 오름차순 정렬
		
		int cnt = 0;
		for(int i=0;i<E;i++) { // 가중치 오름차순 탐색 
			if(cnt == V-1) { // 간선을 정점-1개만큼 선택했다면 종료
				break;
			}
			
			Node edge = edges[i]; // 현재 간선
			if(find_Set(edge.v1) != find_Set(edge.v2)) { // 간선의 두 정점이 같은 집합인지 확인
				union(edge.v1, edge.v2); // 다른 집합이면 union하며 간선 선택
				cnt++; // 간선 개수 +1
				min += edge.weight; // 가중치 합산
			}
		}
	}
	
	// 각 정점의 집합을 만드는 함수
	static void make_Set() {
		for(int i=1;i<=V;i++) {
			parents[i] = i; // 루트 노드는 자기 자신 초기화
		}
	}
	
	// 원소가 포함된 집합을 식별
	static int find_Set(int vertex) {
		if(vertex == parents[vertex]) {
			return vertex;
		}
		
		// Path Compression
		return parents[vertex] = find_Set(parents[vertex]);
	}
	
	// 합집합
	static void union(int a, int b) {
		int aRoot = parents[a];
		int bRoot = parents[b];
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot; // 집합 a에 b를 붙히기
		}
	}
	
	// 간선
	static class Node implements Comparable<Node> {
		int v1;
		int v2;
		int weight; // 가중치
		
		public Node(int v1, int v2, int weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) { // 오름차순 정렬
			return this.weight - o.weight;
		}
	}
}
