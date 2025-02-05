import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 31230. 모비스터디
     * ------------------
     * 
     * [문제설명]
     * 스터디 장소 정하기
     * - N개 도시
     * - M개 양방향 도로
     * - 가중치(시간) 양의 정수
     * - 연결 그래프
     * 
     * 도시 A와 B를 잇는 최단 경로 구하기
     * - 최단 경로에 속단 도시 개수 출력
     * - 모든 도시의 번호를 오름차순으로 출력
     *  
     * [제한사항]
     * 2 <= N <= 200_000
     * 1 <= M <= 300_000
     * 1 <= A, B <= N
     * A != B
     * 
     * 1 <= c <= 10^9 : Long
     * 
     */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		 
		graph = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		 
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			 
			graph[a].add(new City(b, c));
			graph[b].add(new City(a, c));
		}
		
		// 문제풀이
		// 최단 경로 구하기
		Dijkstra(A); 
		result = new ArrayList<>();
		
		// 역추적
		getPath();
		Collections.sort(result);
		sb.append(result.size()).append("\n");
		for(Integer i : result) {			
			sb.append(i).append(" ");
		}
		
		
		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}	

    static int N;
    static int M;
    static int A;
    static int B;
    static List<City>[] graph;
    static boolean[] visited;
    static long[] distance;
    static List<Integer> result;
    
    static void Dijkstra(int start) {
    	visited = new boolean[N+1];
    	distance = new long[N+1];
		Arrays.fill(distance, Long.MAX_VALUE);
		distance[start] = 0;
		 
		PriorityQueue<City> pq = new PriorityQueue<>();
		pq.add(new City(start, 0));
		
		while(!pq.isEmpty()) {
			City cur = pq.poll();
			int now = cur.destination;
			
			if(visited[now]) {
				continue;
			}

			visited[now] = true;
			for(City next : graph[now]) {
				// A -> now -> next
				if (distance[next.destination] > distance[now] + next.cost) {
					distance[next.destination] = distance[now] + next.cost;
					pq.add(new City(next.destination, distance[next.destination]));
				}
			}
		}
    }
    
    static void getPath() {
    	Queue<Integer> queue = new PriorityQueue();
    	queue.add(B);
    	result.add(B);
    	boolean[] visited2 = new boolean[N+1];
    	
    	while(!queue.isEmpty()) {
    		int cur = queue.poll();
    		
    		long nowCost = distance[cur];
    		for(int i=0;i<graph[cur].size();i++) {
    			City city = graph[cur].get(i);
    			
    			if(nowCost - city.cost == distance[city.destination]) {
    				if(!visited2[city.destination]) {
    					visited2[city.destination] = true;
    					result.add(city.destination);
    					queue.add(city.destination);
    				}
    			}
    		}
    		
     	}
    }
    
    static class City implements Comparable<City> {
    	int destination;
    	long cost;
    	
    	public City(int destination, long distance) {
    		this.destination = destination;
    		this.cost = distance;
    	}
    	
    	@Override
    	public int compareTo(City o) {
    		return Long.compare(this.cost, o.cost);
    	}
    }    
}
