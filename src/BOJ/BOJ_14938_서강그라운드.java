package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_14938_서강그라운드 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 14938. 서강그라운드
		 * --------------------
		 * 
		 * 배틀그라운드 비슷한 게임
		 * 
		 * 낙하한 후, 가장 많은 아이템 먹기
		 * 
		 * i번 정점에 있는 아이템 j개 주워 가중치 w이내 최대 아이템 줍기 
		 *
		 * 지역 N : 1 <= N <= 100
		 * 범위 M : 1 <= M <= 15
		 * 길 수 R : 1 <= R <= 100
		 * 아이템 수 t : 1 <= t <= 30
		 *
		 */
		
		// Dijkstra 문제인 것을 파악했지만, 구현 방법을 몰랐음
		// 답을 찾아보고 Dijkstra 구현 방법을 숙지
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		
		
		
		t = new int[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++) {
			t[i] = Integer.parseInt(st.nextToken());
		}

		graph = new ArrayList[n+1];
		for(int i=1;i<=n;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<r;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, w));
			graph[b].add(new Node(a, w));
		}
		
		// 문제풀이
		// m은 수색 범위
		// 시작 정점에서 모든 정점까지 m보다 작은 가중치를 가지는 모든 아이템 합
		
		// 다익스트라로 시작 정점에서 모든 정점까지의 가중치를 구한다
		int max = 0;
		distance = new int[n+1];
		for(int i=1;i<=n;i++) {			
			Dijkstra(i);
			
			// 구한 값으로 가중치가 m이하인 정점의 모든 아이템 개수 합을 구한다
			int sum = 0;
			for(int j=1;j<=n;j++) {
				if(distance[j] <= m) {
					sum += t[j];
				}
			}
			
			// 모든 시작 정점에 대해 최대값을 구한다
			max = Math.max(max, sum);
		}
		
		// 출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}

	static int n;
	static int m;
	static int r;
	static int[] t;
	static List<Node>[] graph;
	static int[] distance;
	
	static void Dijkstra(int start) {
		// 최댓값 초기화
		Arrays.fill(distance, Integer.MAX_VALUE);
		distance[start]  = 0; // 자기 자신은 가중치 0
		
		PriorityQueue<Node> pq = new PriorityQueue<>();
		pq.add(new Node(start, 0)); // 시작 정점
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
						
			// queue에서 pop되었을 때를 기준으로 방문처리 - 가중치가 최소
			if(cur.w > distance[cur.v]) { // 이미 방문한 정점 - 다른 최단 경로가 존재
				continue;
			}
			
			for(Node next : graph[cur.v]) {
				
				if(distance[next.v] < distance[cur.v] + next.w) { // 최단 거리가 아님
					continue;
				}
				
				distance[next.v] = distance[cur.v] + next.w; // 가중치 갱신
				pq.add(new Node(next.v, distance[next.v]));
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
