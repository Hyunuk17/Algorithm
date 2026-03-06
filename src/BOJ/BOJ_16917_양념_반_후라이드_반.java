import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    /*
     * BOJ 16917. 양념 반 후라이드 반
     * ----------------------------
     * 
     * [문제 설명]
     * 치킨
     * 1. 양념 치킨 : A원
     * 2. 후라이드 치킨 : B원
     * 3. 반반 치킨 : C원
     * 
     * 구매
     * - 양념 치킨 최소 X마리
     * - 후라이드 치킨 최소 Y마리
     * - 반반 치킨 2마리로 각 양념 치킨, 후라이드 치킨 1마리로 취급 가능
     *  
     * [출력]
     * 치키을 구매하느 금액의 최솟값
	 *
     * [제한사항]
     * 1 <= A, B, C <= 5,000
     * 1 <= X, Y <= 100,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());
        
        // 문제풀이
        /*
            반반 치킨
            - 1마리만으로는 의미가 없음
            - 2마리를 모아 2배 가격으로 계산

            양념 + 후라이드 < 반반 2마리
            - 애초에 양념과 후라이드만 사용

            양념 + 후라이드 > 반반 2마리
            - 반반 치킨을 우선하여 사용
            - 남은 양념/후라이드를 각각 계산

            재귀
            - X, Y <= 100,000
            - 3^100,000으로 TLE

            반복문
            - O(N)

            수학
            - O(1)에 해결 가능
        */

        // 반반 치킨 2마리씩 구매
        for(int i=0;i<=Math.max(X, Y)*2;i+=2) {
            int half = i/2; // 각 양념/후라이드 마리 수

            // 남은 사야하는 마리 수
            int a = Math.max(0, X - half);
            int b = Math.max(0, Y - half);

            int price = a * A + b * B + i * C;
            min = Math.min(min, price);
        }

        sb.append(min);

        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int A, B, C, X, Y;
    static int min = Integer.MAX_VALUE;

}