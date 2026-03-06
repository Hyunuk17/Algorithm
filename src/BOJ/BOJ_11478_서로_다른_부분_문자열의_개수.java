import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 11478. 서로 다른 부분 문자열의 개수
     * ------------------------------------
     * 
     * [문제 설명]
     * 부분 문자열
     * - 문자열 S의 연속된 일부분
     * - 길이가 1 이상
     * 
     * [입력]
 	 * S : 문자열, 알파벳 소문자로 구성
     *  
     * [출력]
	 * S의 서로 다른 부분 문자열의 개수
     * 
     * [제한사항]
     * 1 <= S.length() <= 1,000
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
            부분 문자열 구하기
            - 중복 제거 : Set
            - N <= 1,000
            - (start, end) : O(N^2)
            - substring 생성 : O(N)
            - O(N^3) : 10^9
        */

        Set<String> set = new HashSet<>();
        for(int i=0;i<S.length();i++) {
            for(int j=i;j<S.length();j++) {
                String substring = S.substring(i, j+1);
                set.add(substring);
            }
        }

        sb.append(set.size());
        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}