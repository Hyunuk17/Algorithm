import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 5361. 전투 드로이드 가격
     * --------------------------
     * 
     * [문제 설명]
     * 부품 가격
     * - 블래스터 라이플 : $350.34
     * - 시각 센서 : $230.90
     * - 청각 센서 : $190.55
     * - 팔 : $125.30
     * - 다리 : $180.90
     * 
     * [입력]
     * T : 테스트 케이스 개수
     * A : 필요한 블래스터 라이플의 개수
     * B : 필요한 시각 센서의 개수
     * C : 필요한 청각 센서의 개수
     * D : 필요한 팔의 수
     * E : 필요한 다리의 수
 	 *  
     * [출력]
     * 테스트 케이스 당 총 비용을 소숫점 둘째 자리까지 출력
     * $표시를 포함
	 *
     * [제한사항]
     * cost <= 100_000_000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int T;
        double A, B, C, D, E;

        T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            A = Double.parseDouble(st.nextToken()) * 350.34;
            B = Double.parseDouble(st.nextToken()) * 230.90;
            C = Double.parseDouble(st.nextToken()) * 190.55;
            D = Double.parseDouble(st.nextToken()) * 125.30;
            E = Double.parseDouble(st.nextToken()) * 180.90;

            double sum = A + B + C + D + E;

            sb.append("$").append(String.format("%.2f", sum)).append("\n");
        }
        
        // 문제풀이
        /**/
        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}