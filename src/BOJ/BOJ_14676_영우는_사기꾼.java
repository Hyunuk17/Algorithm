package BOJ;

import java.util.*;
import java.io.*;

public class BOJ_14676_영우는_사기꾼 {
    /*
        BOJ 14676. 영우는 사기꾼?
        ------------------------

        [문제 설명]
        우주 전쟁
        - 1대1 RTS 게임
        
        건물 짓기
        - 관계도
        - 진입 간선이 있는 건물(노드)가 먼저 지어져야 건설 가능
        - 한 건물은 최대 3개의 건물에게만 영향(3개 간선)

        치트키
        - 원하는 건물을 마음대로 설치 가능
        
        게임
        - 영우가 영선이와의 게임에서 모두 승리
        - 게임의 건물 건설/파괴 정보를 보고 치트키 사용 여부 판단

        [입력]
        N : 건물 종류의 개수
        M : 건물 사이 관계의 개수
        K : 영우의 게임 정보의 개수
        (X, Y) : 건물의 관계
        (i, a) : 영우의 게임 정보, i(1:건설, 2:파괴), a(건물 번호)

        [출력]
        King-God-Emperor : 영우가 정상적으로 건물을 건설하여 승리한 경우
        Lier! : 건설할 수 없는 건물을 건설, 건설한적 없는 건물이 파괴된 경우

        [제한사항]
        1 <= N <= 100,000
        1 <= M <= 100,000
        1 <= K <= 100,000
        1 <= X, Y <= N
        1 <= a <= N

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

        graph = new ArrayList[N+1];
        indegrees = new int[N+1];
        for(int i=1;i<=N;i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());

            graph[X].add(Y); // X->Y 단방향 그래프
            indegrees[Y]++;
        }

        infos = new Node[K];
        for(int i=0;i<K;i++) {
        	st = new StringTokenizer(br.readLine());
            int type = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            infos[i] = new Node(type, number);
        }

        // 문제풀이
        /*
            치트키 사용 여부 판단
            - 게임 정보를 순회하면서
            - 1. 각 건물이 지을 수 있는지 (진입 간선 판단)
            - 2. 파괴된 건물이 지어진적 있는지 (건물의 현재 개수 판단)

            건물 배열
            - 지어진 건물의 개수를 보관
            
            진입차수 배열
            - 현재 건물로 진입하는 간선의 개수 보관
            - 0인 경우에만 새로이 건설 가능

            시간복잡도
            - O(K) 
            
        */

        buildings = new int[N+1]; 

        boolean flag = true;
        for(Node info : infos) {
            int type = info.type;
            int number = info.number;

            // 건물 건설
            if(type == 1) {
            	if(indegrees[number] != 0) { // 진입차선이 있음 : 해당 건물에 선행하는 건물이 필요
            		flag = false;
            		break;
            	}
            	
            	// 건물을 지을 수 있는 경우
            	if(buildings[number] == 0) { // 현재 처음 건물을 지으려는 경우
            		for(Integer next : graph[number]) {
            			indegrees[next]--; // 필요한 간선을 제거
            		}
            	}

                buildings[number]++;
            }
            // 건물 파괴
            else if(type == 2) {
                if(buildings[number] == 0) {
                    flag = false;
                    break;
                }
                else if(buildings[number] == 1) { // 파괴하려는 건물이 1개만 남았다면
                	for(Integer next : graph[number]) {
                		indegrees[next]++;
                	}
                }

                buildings[number]--;
            }
        }

        sb.append(flag == true ? "King-God-Emperor" : "Lier!");

        // 출력
        bw.write(sb.toString());
        bw.flush();
        
        bw.close();
        br.close();

    }

    static int N;
    static int M;
    static int K;
    static List<Integer>[] graph;
    static Node[] infos;
    static int[] indegrees;
    static int[] buildings;

    static class Node {
        int type;
        int number;

        public Node(int type, int number) {
            this.type = type;
            this.number = number;
        }

    }
}