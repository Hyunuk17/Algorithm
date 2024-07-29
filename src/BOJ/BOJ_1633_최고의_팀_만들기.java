package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_1633_최고의_팀_만들기 {
    /*
     * BOJ 1633. 최고의 팀 만들기
     * -------------------------
     * 
     * [문제설몀]
     * 체스대회 30명
     * - 흑 : 15명
     * - 백 : 15명
     * - 각 플레이어의 능력치 : 1~100
     *
     * 가장 높은 능력치 팀 만들기
     * - 흑팀과 백팀 구성하기
     * - 각 플레이어의 흑, 백 능력치 주어짐 
     *
     * [입력]
     * 플레이어의 능력치 : (백, 흑)
     *
     * [출력]
     * 만들 수 있는 팀 중 가장 큰 능력치를 갖는 팀의 능력치
     * 
     * [제한사항]
     * 30 <= 플레이어 수 <= 1,000
     */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        player = new int[1001][2];
        String line = null;
        int idx = 1;
        
        // 입력의 끝을 콘솔로 입력하는 방법 : Ctrl+Z
        while((line = br.readLine()) != null) {
        	String[] token = line.split(" ");
        	int white = Integer.parseInt(token[0]);
        	int black = Integer.parseInt(token[1]);
            player[idx][0] = white;
            player[idx++][1] = black;
        }
  
        // 문제풀이
        // Brute Force : 2^1000 TLE
        // Greedy : 흑 백을 입력과 동시에 결정하는 것은 아닌듯
        // DP? : O(N)으로 처리할 수 있을듯
        // - 선수 1000명까지 중, 30명 선택
        // - 백 15명
        // - 흑 15명
        // dp[i][j][k] : i번째까지 봤을 때 백팀 j명, 흑팀 k명인 경우의 최댓 값
        dp = new int[1001][16][16];
        for(int i=1;i<=1000;i++) {
            for(int w=0;w<=15;w++) {
                for(int b=0;b<=15;b++) {
                	int wValue = 0;
                	int bValue = 0;
                	
                	// 백과 흑 팀에 한명도 없는 경우도 고려해야 한다
                	if(w > 0) {
                		wValue = dp[i-1][w-1][b] + player[i][0];
                	}
                	
                	if(b > 0) {
                		bValue = dp[i-1][w][b-1] + player[i][1];
                	}
                    
                    dp[i][w][b] = Math.max(dp[i-1][w][b], Math.max(wValue, bValue));


                    if(w==15 && b==15) {
                        ans = Math.max(ans, dp[i][w][b]);
                    }
                }
            }
        }
        sb.append(ans);

        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }   

    static int[][] player;
    static int[][][] dp;
    static int ans;
}
