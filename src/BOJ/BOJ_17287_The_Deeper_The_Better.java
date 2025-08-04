import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 17287. The Deeper, The Better
     * ---------------------------------
     * 
     * [문제 설명]
     * 문자열 S
     * - {숫자} 형태, 항상 올바른 괄호 닫기를 이룸
     * 
     * 점수 획득
     * - [n] : 3점
     * - {n} : 2점
     * - (n) : 1점
     * 
     * [입력]
     * S : 문자열
 	 *  
     * [출력]
     * 가장 높은 점수를 받은 숫자의 점수
	 *
     * [제한사항]
     * 0 <= S.length() <= 100
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        String S = br.readLine();

        // 문제풀이
        /*
            Stack
        */

        int max = 0;
        int score = 0;
        for(int i=0;i<S.length();i++) {
            char c = S.charAt(i);

            switch(c) {
                case '(':
                    score += 1;
                    break;
                case ')': 
                    score -= 1;
                    break;
                case '{':
                    score += 2;
                    break;
                case '}':
                    score -= 2;
                    break;
                case '[':
                    score += 3;
                    break;
                case ']':
                    score -= 3;
                    break;
                default:
                    max = Math.max(max, score);
                    break;
            }
        }

        sb.append(max);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}