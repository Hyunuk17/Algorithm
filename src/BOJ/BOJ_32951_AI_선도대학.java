import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_32951_AI_선도대학 {
    /*
     * BOJ 32951. AI 선도대학
     * --------------------
     * 
     * [문제 설명]
     * 
     * [입력]
 	 *  
     * [출력]
	 *
     * [제한사항]
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        int start = 2024;
        
        // 문제풀이
        /**/
        
        sb.append(N - start);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}