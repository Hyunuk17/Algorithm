import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 1916. 최소비용 구하기
     * -----------------------
     *
     * [문제 설명]
     * 도시
     * - N개 (1~N)
     * 
     * 버스
     * - M개
     * - 한 도시에서 출발하여 다른 도시에 도착
     * 
     * 버스 비용 최소화
     * - A번째 도시에서 B번째 도시까지 최소 비용으로 이동
     * 
     * [입력]
     * N : 도시의 개수
     * M : 버스의 개수
     * bus[][] : (출발 도시 번호, 도착 도시 번호, 비용)
     * departure : 출발점
     * destination : 도착점
 	 *  
     * [출력]
	 * 출발 도시에서 도착 도시까지 가는데 드는 최소 비용
     * 
     * [제한사항]
     * 1 <= N <= 1,000
     * 1 <= M <= 100,000
     * 1 <= bus[3] == price <= 100,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        
        bus = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            bus[i] = new ArrayList<>();
        }

        for(int i=1;i<=M;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            bus[A].add(new Node(B, cost));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        departure = Integer.parseInt(st.nextToken());
        destination = Integer.parseInt(st.nextToken());
        
        // 문제풀이
        /*
            최소 비용 도착
            - Dijkstra : O(ElogV)
            - 모든 간선 : M
            - 모든 정점 : N
        */
        
        Dijkstra();
        sb.append(distance[destination]);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int M;
    static List<Node>[] bus;
    static int departure;
    static int destination;
    static int INF = 123_456_789;
    static int[] distance;
    static boolean[] visited;
    static int min = INF;

    static void Dijkstra() {
        distance = new int[N+1];
        Arrays.fill(distance, INF);
        distance[departure] = 0;
        visited = new boolean[N+1];

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(departure, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            int now = cur.v;

            if(visited[now]) {
                continue;
            }

            visited[now] = true;
            
            for(Node next : bus[now]) {
                if(distance[next.v] > distance[now] + next.cost) {
                    distance[next.v] = distance[now] + next.cost;
                    pq.add(new Node(next.v, distance[next.v]));
                }
            }
        }
    }

    static class Node implements Comparable<Node>  {
        int v;
        int cost;

        public Node(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return this.cost - other.cost;
        }
    }
}