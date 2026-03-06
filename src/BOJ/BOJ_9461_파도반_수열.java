package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_9461_파도반_수열 {
    /*
     * BOJ 9461. 파도반 수열
     * -------------------
     * 
     * [문제 설명]
     * 나선 모양 삼각형
     * - 정삼각형들이 나선으로 붙어있는 형태
     * - 초기 삼각형의 변의 길이는 1
     * - 도형의 변이 가장 큰 길이를 변으로 가지는 정삼각형을 추가
     * - 반복
     * 
     * 파도반 수열
     * - P(N)
     * - 나선에 있는 정삼각형의 변의 길이
     * - P(1) ~ P(10) : 1, 1, 1, 2, 2, 3, 4, 5, 7 ,9
     * 
     * [입력]
     * T : Test Case
     * N 
 	 *  
     * [출력]
     * 각 테스트 케이스마다 P(N) 출력
	 *
     * [제한사항]
	 * 1 <= N <= 100
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        
        // 문제풀이
        /*
         * 초기값 (나선을 이룰 때까지)
         * 1 - 1 = 1
         * 2 - 1 = 1
         * 3 - 1 = 1
         * 4 - 2 = 1 + 1
         * 5 - 2 = 2
         * 
         * Case
         * 6 - 3 = 2 + 1
         * 7 - 4 = 3 + 1
         * 8 - 5 = 4 + 1
         * 9 - 7 = 5 + 2
         * 10 - 9 = 7 + 2
         * 
         * P(N)의 규칙
         * - P[10] = P[9] + P[5]
         * - P[9] = P[8] + P[4]
         * - P[8] = P[7] + P[3] 

         * DP
         * - 점화식(Recurrence Relation)
         * - dp[i] = dp[i-1] + dp[i-5] 
         * 
         * 타입 추론
         * - 수열(P(n-1)+P(n-5))
         * - 매 단계마다 이전 값의 일정한 배수(약 1.32배)를 곱하는 형태
         * - 지수 함수(f(n) = a^n)처럼 폭발적으로 증가
         * - '이전 항들을 더한다'는 조건이 나올 때 long부터 의심
         */
        
        int MAX = 101;
        long[] P = new long[MAX];
        P[1] = 1;
        P[2] = 1;
        P[3] = 1;
        P[4] = 2;
        P[5] = 2;
        
        for(int i=6;i<MAX;i++) {
        	P[i] = P[i-1] + P[i-5];
        }
        
        while(T-- > 0) {
        	int N = Integer.parseInt(br.readLine());

        	sb.append(P[N]).append("\n");
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}