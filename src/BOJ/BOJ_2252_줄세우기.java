package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2252_줄세우기 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 2252. 줄 세우기
		 * ----------------
		 * 
		 * N명의 학생들을 키 순서대로 줄 세우기
		 * 두 학생씩 키를 비교
		 * 키를 비교한 횟수 M
		 * 
		 * 일부 학생들의 키를 비교한 결과가 주어짐(A, B), A가 앞
		 * 줄 세우기
		 * 
		 * 1 <= N <= 32,000
		 * 1 <= M <= 100,000
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		inDegree = new int[N+1];
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=1;i<=M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			graph[a].add(b); // 키가 큰 -> 작은 순서대로
			inDegree[b]++;
		}
		
		// 문제풀이
		// 위상 정렬
		// O(V+E) = 32,000(N) + 100,000(M) : 문제 없음
		TopologySort();
		
		// 출력
		for(int n : result) {
			sb.append(n).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static List<Integer> result = new ArrayList<>();
	static List[] graph;
	static int[] inDegree;
	static boolean[] visited;
	
	static void TopologySort() {
		Queue<Integer> queue = new ArrayDeque();
		visited = new boolean[N+1];
		for(int i=1;i<=N;i++) {
			if(inDegree[i] == 0) { // 진입 차선이 0인 노드
				visited[i] = true;
				queue.add(i);
			}
		}
		
		while(!queue.isEmpty()) {
			int cur = queue.poll(); // 진입 차선이 0인 노드
			result.add(cur);
			
			// 현재 노드의 연결 차선 지우기
			for(int i=graph[cur].size()-1;i>=0;i--) {
				int destination = (int) graph[cur].get(i); // 연결된 노드
				graph[cur].remove(Integer.valueOf(destination)); // 간선 지우기
				inDegree[destination]--; // 집입 차선 감소
				
				if(visited[destination] == true) { // 방문한 점이면 continue
					continue;
				}
				
				if(inDegree[destination] == 0) { // 진입 차선이 0이 된 노드가 존재하면
					visited[destination] = true;
					queue.add(destination); // offer
				}
			}
		}
	}
}
