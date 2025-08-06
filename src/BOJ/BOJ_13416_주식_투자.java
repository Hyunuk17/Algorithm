import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    /*
     * BOJ 13416. 주식 투자
     * -------------------
     * 
     * [문제 설명]
     * 투자 규칙
     * - 3개 회사 A, B, C
     * - 하루에는 최대 1개 회사의 주식만 구매 가능
     * - 장이 닫힐 때, 그날 산 주식을 모두 판매
     * - 수익이 나지 않을 거 같으면, 구매하지 않아도 됨
     * 
     * 투자 복기
     * - 지난 N일 간의 주식 투자 데이터
     * - 각 회사별로 장이 열릴 때 매입, 장이 닫힐 때 매도
     * - 양수(+) : 이익, 음수(-) 손해
     * 
     * [입력]
     * T : 테스트 케이스
     * N : 주식 데이터의 일수
     * profit[] : A, B, C 회사별 손익
 	 *  
     * [출력]
     * 최적의 투자를 했을 때 얻을 수 있었던 최대 이윤
	 *
     * [제한사항]
     * 1 <= N <= 1,000
     * -1,000,000 <= A, B, C <= 1,000,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        T = Integer.parseInt(br.readLine());
        
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());
            profit = new int[N][3];

            for(int i=0;i<N;i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int C = Integer.parseInt(st.nextToken());

                profit[i][0] = A;
                profit[i][1] = B;
                profit[i][2] = C;
            }

            // 문제풀이
            /*
                3^1000? => TLE
            */

            int max = 0;
            for(int i=0;i<N;i++) {
                int A = profit[i][0];
                int B = profit[i][1];
                int C = profit[i][2];

                max += Math.max(0, Math.max(A, Math.max(B, C)));
            }

            sb.append(max).append("\n");
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int T;
    static int N;
    static int[][] profit;
}