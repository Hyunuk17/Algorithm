import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 1890. 점프
     * --------------
     * 
     * [문제 설명]
     * 게임
     * - NxN 게임판
     * - 가장 왼쪽 위칸에서 가장 오른쪽 아래칸으로 점프
     * - 아래, 오른쪽 방향만 이동 가능
     * - board[i][j] : 현재 칸에서 갈 수 있는 거리
     * 
     * [입력]
     * N : 게임판의 크기
     * board[][] : 갈 수 있는 거리
     *  
     * [출력]
     * 목표에 도달할 수 있는 경로의 개수
	 *
     * [제한사항]
     * 4 <= N <= 100
     * 0 <= board[i][j] <= 9
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 문제풀이
        /*
            목표로 이동
            - 아래쪽 점프
            - 오른쪽 점프

            목표점의 경로 수
            - board[n-1][n-1]에 도달할 수 있는 거리를 가진 점들의 합
            - 이전의 문제들이 현재의 부분문제인 경우 : DP

            DP
            - 현재에 영향을 미치는 이전 것들을 알 방법이 없음 : top-down 불가
            - bottom-up : 시작점(0,0)에서 전파 

        */

        dp = new long[N][N];

        // Init
        dp[0][0] = 1;

        // Bottom-up
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                // Skip
                if(dp[i][j] == 0) {
                    continue;
                }

                // Base Case
                if(i == N-1 && j == N-1) {
                    break;
                }
                
                int distance = board[i][j];

                // Recurrence Relation
                if((i + distance) < N) {
                    dp[i+distance][j] += dp[i][j]; // 아래쪽 점프
                }

                if((j + distance) < N) {
                    dp[i][j+distance] += dp[i][j]; // 오른쪽 점프
                }
            }
        }

        sb.append(dp[N-1][N-1]);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int[][] board;
    static long[][] dp;

}