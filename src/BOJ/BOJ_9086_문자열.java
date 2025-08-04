import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 9086. 문자열
     * ----------------
     * 
     * [문제 설명]
     * 문자열의 첫 글자와 마지막 글자를 출력
     * 
     * [입력]
     * T : 테스트 케이스
 	 * S : 문자열
	 *
     * [제한사항]
     * 1 <= T <= 10
     * 0 <= S.length() <= 1,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());

        // 문제풀이
        /**/
        while(T-- > 0) {
            String S = br.readLine();
            char start = S.charAt(0);
            char end = S.charAt(S.length() - 1);
            
            sb.append(start).append(end).append("\n");
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}