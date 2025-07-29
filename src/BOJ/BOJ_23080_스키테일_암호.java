import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 23080. 스키테일 암호
     * ----------------------
     * 
     * [문제 설명]
     * 스키테일(Scytale) 암호
     * - 고정 굵기 X의 원통형 막대
     * - 종이 리본을 감아 옆으로 글자 X개 메시지 적기
     * 
     * [입력]
     * K : 막대의 굵기
     * S : 암호문
 	 *  
     * [출력]
     * 암호문을 해독한 결과
	 *
     * [제한사항]
     * 3 <= K <= 10
     * 1 <= S.length <= 1,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int K = Integer.parseInt(br.readLine());
        char[] str = br.readLine().toCharArray();

        // 문제풀이
        /*
            K칸씩 띄어서 읽기
        */

        for(int i=0;i<str.length;i+=K) {
            sb.append(str[i]);
        }
        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}