package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_14284_간선_이어가기_2 {
    /*
     * BOJ 14284. 간선 이어가기 2
     * -----------------------
     * 
     * [문제 설명]
     * 무방향 그래프
     * - 정점 N개
     * - 간선 0개
     * 
     * 간선 리스트
     * - m개의 가중치 간선 정보
     * 
     * 그래프 연결
     * - 간선 하나씩 그래프에 추가
     * - 특정 정점 s와 t가 연결이 되는 순간 연결 중지 : 간선을 따라 방문 가능한 상태
     * - 간선 순서 조정 가능
     * 
     * 
     * [입력]
     * N : 정점의 개수
     * M : 간선 수
     * (a,b,c) : a<->b, 가중치 c
     * (s, t) : 연결할 두 정점 s, t
     * 
 	 *  
     * [출력]
     * s와 t가 연결이 되는 시점의 간선의 가중치의 합의 최솟값
	 *
     * [제한사항]
	 * 2 <= N <= 5,000
	 * 1 <= M <= 100,000
	 * 1 <= a, b<= N
	 * 1 <= c <= 100
	 * 1 <= s, t <= N
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine()); 
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
        	graph[i] = new ArrayList<>(); 
        }
        
        for(int i=0;i<M;i++) {
        	st = new StringTokenizer(br.readLine());
        	int u = Integer.parseInt(st.nextToken());
        	int v = Integer.parseInt(st.nextToken());
        	int w = Integer.parseInt(st.nextToken());
        	
        	graph[u].add(new Node(v, w));
        	graph[v].add(new Node(u, w));
        }
        
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        
        // 문제풀이
        /*
         * 그래프 연결
         * - 모든 간선을 연결하면 그래프는 연결 그래프가 됨을 보장
         * 
         * s->t
         * - 모든 정점을 연결하는 문제가 아닌 s에서 t로 가는 문제
         * - 다익스트라 : s에서 모든 정점까지의 최단거리 구하기
         * - distance[t] 
         * 
         * 시간복잡도
         * - O(ElogV) : (N*N) * logN
         * 
         */

    	distance = new int[N+1];
    	visited =  new boolean[N+1];
        Dijkstra(S);
        sb.append(distance[T]);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
    
    static int N;
    static int M;
    static List<Node>[] graph;
    static int S;
    static int T;
    static int INF = 123_456_789;    
    static int[] distance;
    static boolean[] visited;
    
    static void Dijkstra(int start) {
    	Arrays.fill(distance, INF);
    	distance[start] = 0;
    	
    	PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
    		return o1.w - o2.w;
    	});
    	pq.offer(new Node(start, 0));
    	
    	while(!pq.isEmpty()) {
    		Node cur = pq.poll();
    		int now = cur.v;
    		
    		if(visited[now]) {
    			continue;
    		}
    		
    		visited[now] = true;
    		for(Node next : graph[now]) {
    			if(distance[next.v] > distance[now] + next.w) {
    				distance[next.v] = distance[now] + next.w;
    				pq.offer(new Node(next.v, distance[next.v]));
				}
    		}
    	}
    	
    }
    
    static class Node {
    	int v;
    	int w;
    	
    	public Node(int v, int w) {
    		this.v = v;
    		this.w = w;
    	}
    }
}