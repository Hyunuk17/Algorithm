import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 10988. 팰린드롬인지 확인하기
     * -----------------------------
     * 
     * [문제 설명]
     * 팰린드롬
     * - 앞으로 읽을 때와 거꾸로 읽을 때 똑같은 단어
     * - 알파벳 소문자만 주어짐
     * 
     * [입력]
     * word : 주어진 단어
 	 *  
     * [출력]
     * 팰린드롬이면 1,아니면 0 출력
	 *
     * [제한사항]
     * 1 <= word.length() <= 100
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        String word = br.readLine();

        // 문제풀이
        /**/
        boolean isPalindrome = true;
        for(int i=0;i<word.length()/2;i++) {
            char c1 = word.charAt(i);
            char c2 = word.charAt((word.length()-1) - i);

            if(c1 != c2) {
                isPalindrome = false;
                break;
            }
        }

        sb.append(isPalindrome ? 1 : 0);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}