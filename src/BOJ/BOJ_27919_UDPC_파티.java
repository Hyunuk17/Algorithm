import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 27919. UDPC 파티
     * --------------------
     * 
     * [문제 설명]
     * 투표
     * - U, D, P, C(기권)
     * 
     * 혼동
     * - U, C가 바뀔 수 있음
     * - D, P가 바뀔 수 있음
     * 
     * [입력]
     * V : 투표 결과 문자열
 	 *  
     * [출력]
     * 선정될 수 있는 모든 결과 U, D, P, C를 출력
	 *
     * [제한사항]
     * 0 <= V.length() <= 100,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        String V = br.readLine();
        int ucCnt = 0;
        int dpCnt = 0;

        for(int i=0;i<V.length();i++) {
            char c = V.charAt(i);
            if(c == 'U' || c == 'C') {
                    ucCnt++;
            }
            else {
                dpCnt++;
            }
        }
        
        // 문제풀이
        /*
            "선정될 수 있음"
            1. U는 D와 P의 합의 1/2+1을 넘으면 선정될 수 있음
            - D와 P의 개수를 균일하게 만들어버리면 되기 때문

            2. U가 몇개든 D와 P는 1개만 넘으면 선정될 수 있음
            - U를 다 C로 간주하는 경우가 있기 때문에

            3. C는 
        */
        
        if(ucCnt > (dpCnt+1) / 2) {
            sb.append("U");
        }
        
        if(dpCnt > 0) {
            sb.append("DP");
        }

        if(sb.length() == 0) {
            sb.append("C");
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}