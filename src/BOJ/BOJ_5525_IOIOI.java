import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 5525. IOIOI
     * ----------------
     * 
     * [문제 설명]
     * 문자열 P_N
     * - 두 문자 'I'와 'O'가 교대로 나오는 문자열
     * - N+1개의 I
     * - N개의 O
     * 
     * 
     * [입력]
     * N : 정수
     * M : S의 길이
     * S : 문자열
 	 *  
     * [출력]
     * 문자열 S 안에 P_N이 몇 군데 포함되어 있는지 구하기
	 *
     * [제한사항]
     * 1 <= N <= 1,000,000
     * 2N+1 <= M <=1,000,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        S = br.readLine();

        // 문제풀이
        /*
            BF : O(NM)
            - 10^6 * 10^6 => TLE
            - 서브태스크 내에서는 가능 : N <= 100, M <= 10,000

            시간복잡도
            - O(N) 

        */

        int cnt = 0; // OI 단위 카운트
        for(int i=1;i<M-1;) {
            if(S.charAt(i) == 'O' && S.charAt(i+1) == 'I') {
                cnt++;

                if(cnt == N) { // OI 반복이 N번
                    if(S.charAt(i- (2*cnt - 1)) == 'I') { // P 완성
                        result++;
                    }

                    cnt--; // 연속된 패턴을 찾아내기 위함
                }

                i += 2;
            }
            else {
                cnt = 0;
                i++;
            }
        }

        sb.append(result);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int M;
    static String S;
    static int result;
}