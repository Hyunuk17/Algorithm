import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 1719. 택배
     * --------------
     * 
     * [문제 설명]
     * 그래프
     * - 정점 : 집하장 번호
     * - 간선 : 이동 가능한 집하장
     * - 가중치 : 이동에 걸리는 시간
     * 
     * 경로표
     * - 화물이 각 집하장을 사이를 오갈 때 거치는 경로를 표현
     * - 최단 경로로 이동하기 위해 가장 먼저 들러야하는 집하장을 의미
     * 
     * [입력]
     * n : 집하장의 개수
     * m : 집하장 간 경로의 개수
     * edge[] : A, B, time
 	 *  
     * [출력]
     * 경로표 출력
     * - 자기 자신으로의 경로는 '-'로 표시
	 *
     * [제한사항]
     * 1 <= n <= 200
     * 1 <= m <= 10,000
     * 1 <= time <= 1,000
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

        distance = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            Arrays.fill(distance[i], INF);
            distance[i][i] = 0;
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            distance[A][B] = time;
            distance[B][A] = time;
        }
        
        // 문제풀이
        /*
            경로표
            - 모든 집하장에서 다른 모든 집하장까지 최단 경로를 가지는 첫번째 집하장 번호
            - 모든 정점 간 최단거리 : Floyd-warshall, O(N^3)

            역추적?
            - Dijkstra의 경우 도착점에서 진입 차선을 바라보며 역추적을 진행할 수 있음
            - Floyd-warshall은 가능한가?

            가장 가까운 정점
            - 최단 경로가 만들어지는 경우, 현재 거쳐가는 중간점이 시작 지점에서 가장 가까운 정점
        */

        shortest = new int[N+1][N+1];
        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                shortest[i][j] = j; // i에서 j로 가는 길 중, 가장 가까운 정점
            }
        }

        floyd_warshall();

        for(int i=1;i<=N;i++) {
            for(int j=1;j<=N;j++) {
                if(i == j) {
                    sb.append("-").append(" ");
                }
                else {
                    sb.append(shortest[i][j]).append(" ");
                }
            }

            sb.append("\n");
        }

        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int M;
    static int[][] distance;
    static int[][] shortest;
    static int INF = 123_456_789;

    static void floyd_warshall() {

        // 중간 지점 K를 거쳐 도달하는 길
        for(int k=1;k<=N;k++) {
            for(int i=1;i<=N;i++) {
                for(int j=1;j<=N;j++) {
                    // 최단 경로
                    if(distance[i][j] > distance[i][k] + distance[k][j]) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                        shortest[i][j] = shortest[i][k]; // i는 j보다 가까운 k를 거쳐감
                    }
                }
            }
        }
    }

    static class Node {
        int v;
        int time;

        public Node(int v, int time) {
            this.v = v;
            this.time = time;
        }
    }
}