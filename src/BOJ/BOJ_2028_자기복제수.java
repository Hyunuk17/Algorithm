import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2028_자기복제수 {
    /*
     * BOJ 2028. 자기복제수
     * -------------------
     * 
     * [문제 설명]
     * 자기복제수
     * - 자연수 N을 제곱했을 때, 그 제곱수의 맨 뒷자리에 N이 다시 나타나는 수
     * - ex) 5^2 => 25, 76^2 => 5776
     * 
     * [입력]
     * T : Test Case
     * N : 자연수
 	 *  
     * [출력]
     * 자연수 N이 자기복제수인지 판별
	 *
     * [제한사항]
     * 1 <= T <= 20
     * 1 <= N <= 1,000
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
            int N = Integer.parseInt(br.readLine());
            String strN = String.valueOf(N);

            int squared = N * N;
            String strSquared = String.valueOf(squared);

            boolean flag = true;
            int size = strN.length();
            for(int i=0;i<size;i++) {
                if(strSquared.charAt(strSquared.length() - 1 - i) != strN.charAt(size-1 - i)) {
                    flag = false;
                    break;
                }
            }

            if(flag) {
                sb.append("YES").append("\n");
            } else {
                sb.append("NO").append("\n");
            }
        }
        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}