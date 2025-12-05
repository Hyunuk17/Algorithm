import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_17211_좋은_날_싫은_날 {
    /*
     * BOJ 17211. 좋은 날 싫은 날
     * ------------------------
     * 
     * [문제 설명]
     * 구분 
     * - 좋은 날
     * - 싫은 날
     * - 오늘의 기분이 내일의 기분에 영향
     * 
     * [입력]
 	 * N : 정수
     * mood : 현재 기분 (0: 좋은날, 1: 싫은날)
     * [좋은날-좋은날], [좋은날-싫은날], [싫은날-좋은날], [싫은날-싫은날] : 확률(소숫점 둘째자리)
     *  
     * [출력]
     * N일 뒤의 기분이 좋은날일 확률 * 1,000에 소숫점 첫째자리 반올림
     * N일 뒤의 기분이 싫은날일 확률 * 1,000에 소숫점 첫째자리 반올림
	 *
     * [제한사항]
	 * 1 <= N <= 100
     * 
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int mood = Integer.parseInt(st.nextToken()); // 0: 좋은날, 1: 싫은날
        double[][] prob = new double[2][2];
        
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                prob[i][j] = Double.parseDouble(st.nextToken());
            }
        }

        // 문제풀이
        /**/

        double[][] dp = new double[N + 1][2];
        dp[0][mood] = 1.0;
        dp[0][1 - mood] = 0.0;

        for(int i=1;i<=N;i++) {
            // 좋은 날 = 좋-좋 + 싫-좋
            dp[i][0] = dp[i-1][0] * prob[0][0] + dp[i-1][1] * prob[1][0];

            // 싫은 날
            dp[i][1] = dp[i-1][0] * prob[0][1] + dp[i-1][1] * prob[1][1];
        }

        sb.append(Math.round(dp[N][0] * 1000)).append("\n");
        sb.append(Math.round(dp[N][1] * 1000)).append("\n");

        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}