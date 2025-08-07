import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 16936. 나3곱2
     * -----------------
     * 
     * [문제 설명]
     * 나3곱2 게임
     * - 정수 X
     * - 연산 N-1번
     *      1. 나3 : x를 3으로 나누기, x는 3으로 나누어 떨어져야 함
     *      2. 곱2 : x에 2를 곱하기
     * 
     * 수열 A
     * - 나3곱2 게임을 진행하면서 만들어진 수들의 나열
     * 
     * 수열 B
     * - 수열 A의 순서를 섞은 배열
     * 
     * [입력]
     * N : 수열의 크기
     * B[] : 수열 B
 	 *  
     * [출력]
     * 수열 A를 출력
     * - 정답이 여러가지라면 아무거나 출력
	 *
     * [제한사항]
     * 2 <= N <= 100
     * 1 <= B[i] <= 10^18 (long)
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        B = new long[N];
        A = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            B[i] = Long.parseLong(st.nextToken());
        }
        
        // 문제풀이
        /*
            수열 A 찾기
            - 수열의 길이 N <= 100
            - 수열 B로 모든 수열 A 찾기 : O(N!) => TLE

            연산
            - 나3, 곱2 중 뭘, 몇번 사용해도 상관 없음
            - 수열 B의 모든 수를 대상으로, 순회 : O(N)
            - 수열 만들기 : O(N!)
            - /3, *2 조건으로 가지치기 : Pruning
            - 수열을 만들 수 있으면 종료 : Backtracking

            B에 중복이 없음을 보장
            - 소인수 *2, /3은 겹치지 않음
        */

        for(int i=0;i<N;i++) {
            visited = new boolean[N];
            visited[i] = true;
            A[0] = B[i];
            DFS(1, A[0]);

            if(flag) {
                break;
            }
        }
        
        for(long a : A) {
            sb.append(a).append(" ");
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static long[] B;
    static long[] A;
    static boolean[] visited;
    static boolean flag;

    static void DFS(int depth, long pre) {
        // Pruning
        if(flag == true) {
            return;
        }

        // Base Case
        if(depth == N) {
            flag = true;
            return;
        }

        // Loop
        for(int i=0;i<N;i++) {
            if(visited[i] == true) {
                continue;
            }

            if((pre % 3 == 0 && pre / 3 == B[i]) || (pre * 2 == B[i])) {
                visited[i] = true;
                A[depth] = B[i];
                DFS(depth+1, A[depth]);
                visited[i] = false;
            }
        }
    }
}