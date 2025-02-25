package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2163_초콜릿_자르기 {
    /*
     * BOJ 2163. 초콜릿 자르기
     * --------------------
     * 
     * [문제 설명]
     * 초콜릿
     * - NxM 크기
     *
     * 쪼개기
     * - NxM 격자 모양을 따라서만 자를 수 있음
     * - 초콜릿 조각을 잘라 2개로 분할
     * 
     * [출력]
     * 1x1 초콜릿으로 모두 나누기 위한 최소 쪼개기 횟수
	 *
     * [제한사항]
     * 1 <= N, M <= 300
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
        
        
        // 문제풀이
        /*
         * 최소 쪼개기 횟수
         * - 1x1로 나누기 : 모든 선을 자르기
         */
        
        sb.append(N*M-1);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
    
    static int N;
    static int M;
}