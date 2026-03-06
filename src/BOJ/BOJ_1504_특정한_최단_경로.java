import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 1504. 특정한 최단 경로
     * ------------------------
     * 
     * [문제 설명]
     * 무방향 그래프
     * - 1번 정점에서 N번 정점으로 최단 거리로 이동
     * - 임의로 주어진 두 정점을 반드시 통과
     * 
     * [입력]
     * N : 정점의 개수
     * E : 간선의 개수
     * edges[] : 정점 a와 정점 b를 잇는 거리 c의 길
     * v1, v2 : 반드시 거쳐야 하는 두 다른 정점
 	 *  
     * [출력]
     * 두 정점을 지나는 최단 경로의 길이
     * 경로가 없다면 -1
	 *
     * [제한사항]
     * 2 <= N <= 800
     * 0 <= E <= 200,000
     * 1 <= c <= 1,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<E;i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[A].add(new Node(B, cost));
            graph[B].add(new Node(A, cost));
        }

        st = new StringTokenizer(br.readLine());
        v1 = Integer.parseInt(st.nextToken());
        v2 = Integer.parseInt(st.nextToken());

        // 문제풀이
        /*
            최소 거리
            - Dijkstra : 한 정점에서 다른 모든 정점까지의 최단 거리
            - Floyd-Warshall : 모든 정점에서 다른 모든 정점까지의 최단 거리

            두 점을 지나기
            - v1, v2를 대상으로 최소거리 구하기
            - 경우의 수를 보고 최적을 계산하기
                - 1 -> v1 -> v2 -> N
                - 1 -> v2 -> v1 -> N

            정점 1을 기준으로 Dijkstra
            - 1 -> v1/v2, v1->N, v2->N : 총 3번
            
            정점 v1, v2를 기준으로 Dijkstra
            - v1->1/v2/N, v2->1/v1/N : 총 2번

            시간복잡도
            - ElogV : 200,000 * log800 = 2*10^5 * 8*2 = 32*10^5, 약 300만번

            Floyd-Warshall이 1번으로 전부 끝낼 수 있을 거 같긴 함
            - O(n^3) : (8*10^2)^3, 약 5억번에 연산 가능
        */
        
        distance1 = new int[N+1];
        Dijkstra(v1, distance1);
        
        distance2 = new int[N+1];
        Dijkstra(v2, distance2);

        // 가능한 경로와 최소 거리 경로 선택
        int path1 = distance1[1] + distance1[v2] + distance2[N];
        int path2 = distance2[1] + distance2[v1] + distance1[N];
        int path = Math.min(path1, path2);

        sb.append(path >= INF ? -1 : path);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int E;
    static List<Node>[] graph;
    static int v1;
    static int v2;
    static int INF = 123_456_789;
    static int[] distance1;
    static int[] distance2;

    static void Dijkstra(int start, int[] distance) {
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(distance[cur.v] < cur.cost) {
                continue;
            }

            for(Node next : graph[cur.v]) {
                // distance[next.v] : 시작 정점에서 next.v까지의 비용
                // distance[cur.v] : 시작 정점에서 cur.v까지의 비용
                // next.cost : 현재 정점 cur.v에서 간선으로 이어진 next.v까지의 비용
                if(distance[next.v] > distance[cur.v] + next.cost) {
                    distance[next.v] = distance[cur.v] + next.cost;
                    pq.add(new Node(next.v, distance[next.v]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
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