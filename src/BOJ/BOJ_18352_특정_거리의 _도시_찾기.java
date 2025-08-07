import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.util.*;

public class Main {
    /*
     * BOJ 18352. 특정 거리의 도시 찾기
     * ------------------------------
     * 
     * [문제 설명]
     * 도시
     * - 1~N
     * 
     * 도로
     * - 단방향
     * - M개
     * - 거리는 1 (가중치 1)
     * 
     * 최단 거리
     * - 도시 X에서 출발
     * - 최단 거리가 정확히 K인 도시들 찾기     
     * 
     * [입력]
 	 * N : 도시의 개수
     * M : 도로의 개수
     * K : 거리 정보
     * X : 출발 도시의 번호 
     * edge[] : (A, B)
     * 
     * [출력]
     * X로부터 출발하여 도달할 수 있는 도시 중에서, 최단 거리가 K인 모든 도시의 번호
     * - 한 줄에 하나씩 출력
     * - 오름차순
     * - 도시가 존재하지 않으면 -1
	 *
     * [제한사항]
     * 2 <= N <= 300,000
     * 1 <= M <= 1,000,000
     * 1 <= X <= N
     * 1 <= A, B <= N, (A != B)
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
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];
        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B); // 단방향
        }

        // 문제풀이
        /*
            도시 X에서 출발하는 최단거리 K의 도시 구하기
            - BFS : O(V+E), 300,000 + 1,000,000
            - Dijkstra : O(ElogV), 10^6 * 3*5 = 15*10^6

            Dijkstra
            - BFS가 유리하지만, 연습을 위해 Dijkstra를 사용
        */
        
        Dijkstra(X);

        result = new ArrayList<>();
        for(int i=1;i<=N;i++) {
            if(distance[i] == K) {
                cnt++;
                result.add(i);
            }
        }

        if(cnt != 0) {
            Collections.sort(result);
            for(int i=0;i<result.size();i++) {
                sb.append(result.get(i)).append("\n");
            }
        }
        else {
            sb.append(-1);
        }

        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int M;
    static int K;
    static int X;
    static List<Integer>[] graph;
    static int INF = 123_456_789;
    static int[] distance;
    static int cnt;
    static List<Integer> result;

    static void Dijkstra(int start) {
        distance = new int[N+1];
        Arrays.fill(distance, INF);
        distance[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            
            if(distance[cur.v] < cur.cost) { // 이미 최적이 있으면
                continue;
            }

            for(Integer next : graph[cur.v]) {
                if(distance[next] > distance[cur.v] + 1) {
                    distance[next] = distance[cur.v] + 1;
                    pq.add(new Node(next, distance[next]));
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