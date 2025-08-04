import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 14425. 문자열 집합
     * --------------------
     * 
     * [문제 설명]
     * 집합 S
     * - 총 N개 문자열로 구성
     * 
     * [입력]
     * 문자열 M개
 	 *  
     * [출력]
     * 입력 M개 문자열 중, 집합 S에 포함되어 있는 것의 개수
	 *
     * [제한사항]
	 * 1 <= N <= 10,000
     * 1 <= M <= 10,000
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        Set<String> S = new HashSet<>();
        for(int i=0;i<N;i++) {
            String str = br.readLine();
            S.add(str);
        }

        int result = 0;
        for(int i=0;i<M;i++) {
            String str = br.readLine();
            if(S.contains(str)) {
                result++;
            }
        }

        // 문제풀이
        /**/
        sb.append(result);
        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}