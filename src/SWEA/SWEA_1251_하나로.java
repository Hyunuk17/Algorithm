package SWEA;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1251_하나로 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1251. 하나로
		 * ---------------
		 * 
		 * N개의 섬들을 연결
		 * 
		 * E: 환경 부담 세율 
		 * L: 해저터널 길이
		 * 환경 부담금 : E * L^2
		 * 
		 * 환경 부담금이 최소로 하는 모든 섬을 잇는 경로 구하기
		 * 
		 * 1 <= N <= 1,000
		 * 섬의 x좌표 : 0 <= X <= 1,000,000
		 * 섬의 y좌표 : 0 <= Y <= 1,000,000
		 * 0 <= E <= 1
		 * 
		 * 1,000,000 ^2 => long
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine()); // 섬 개수
			arr = new int[N][2]; // 섬 정보 저장 배열
			
			// x좌표들
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i][0] = Integer.parseInt(st.nextToken());				
			}
			
			// y좌표들
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				arr[i][1] = Integer.parseInt(st.nextToken()); 
			}
			
			E = Double.parseDouble(br.readLine());
			total = 0;
			
			// 문제풀이
			// E * L^2가 최소가 되도록
			// E는 고정값, L을 최소로 하는 MST 구하기
			edges = new Node[N * (N-1) / 2];
			int idx = 0; 
			for(int i=0;i<N;i++) {
				for(int j=i+1;j<N;j++) {
					double distance = Math.pow(arr[i][0] - arr[j][0], 2) + Math.pow(arr[i][1] - arr[j][1], 2);
					edges[idx++] = new Node(i, j, distance);
				}
			}
			
			// Kruskal(정점 기준)
			kruskal();
			
			
			// 출력
			double ans = Math.round(total);
			sb.append("#").append(t).append(" ").append((long)ans).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}

	static int T;
	static int N;
	static double E;
	static int[][] arr;
	static int[] parents;
	static double total;
	static Node[] edges;
	
	static void kruskal() {
		makeSet(); // 집합 생성
		
		Arrays.sort(edges); // 오름차순 정렬
		
		int cnt = 0; // 선택한 간선 개수
		for(int i=0;i<edges.length;i++) { // 오름차순 탐색 
			if(cnt == N-1) { // 선택한 간선이 정점-1이면 종료
				break;
			}
			
			Node edge = edges[i]; // 현재 간선
			if(findSet(edge.v1) != findSet(edge.v2)) { // 다른 집합인 경우
				union(edge.v1, edge.v2); // 합집합
				cnt++; // 간선 개수 +1
				total += E * edge.weight; // 가중치 합
			}
		}
	}
	
	// 집합 생성
	static void makeSet() {
		parents = new int[N+1];
		for(int i=1;i<=N;i++) {
			parents[i] = i;
		}
	}
	
	// 집합 판별
	static int findSet(int v) {
		if(parents[v] == v) {
			return v;
		}
		
		// Path Compression
		return parents[v] = findSet(parents[v]);
	}
	
	// 합집합 구하기
	static void union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		
		if(aRoot != bRoot) {
			parents[bRoot] = aRoot;
		}		
	}

	static class Node implements Comparable<Node>{
		int v1;
		int v2;
		double weight;
		
		public Node(int v1, int v2, double weight) {
			this.v1 = v1;
			this.v2 = v2;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) { // 가중치 오름차순
			return this.weight < o.weight ? -1 : 1;
		}
	}
}
