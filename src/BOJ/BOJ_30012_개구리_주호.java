import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    /*
     * BOJ 30012. 개구리 매칭
     * ---------------------
     * 
     * [문제 설명]
     * 개구리
     * - N 마리
     * - x 축 위에 존재
     * 
     * 매칭
     * - 주호 : 위치 S에서 시작
     * - i번째 개구리 : E_i에서 시작
     * - 서로의 체력 소모의 합이 최소가 되도록 이동
     * 
     * 점프 과정
     * - 최대 점프 가능 거리 K
     * - 점프한 거리 d
     * - 체력소모 K-d
     * -
     * 
     *  이동
     * - 점프 이후 걸어서 이동 (점프가 반드시 선행)
     * - 1씩 걸을 때마다 체력 L 소모
     * 
     * 
     * [입력]
     * S : 주호 시작 위치
     * N : 개구리 수
     * E[] : 개구리들 시작 위치
     * K : 최대 점프 거리
     * L : 걷는 경우 소모하는 체력
 	 *  
     * [출력]
     * 개구리 주호와 다른 개구리가 만났을 때, 서로의 체력 소모 합이 최소인 경우
     * - 서로의 체력 소모의 합
     * - 개구리의 번호 (여러 마리인 경우 아무거나 출력)
	 *
     * [제한사항]
     * 0 <= S <= 100,000
     * 1 <= N <= 10,000
     * 0 <= E[i] <= 100,000 
     * 1 <= K <= 100,000
     * 1 <= L <= 100,000
     * 
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        E = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            E[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        
        // 문제풀이
        /*
            매칭
            - 
        */
        
        for (int i = 0; i < N; i++) {
            int position = E[i];
            int distance = Math.abs(S - position);

            int sum = 0;
            // Jump
            // - 점프로 인한 체력 소모가 0이 되는 것이 우선
            // - 0이 될 수 없으면? 
            
            // 주호
            if(distance < K) { // K보다 적게 뛸 수 있는 경우
                sum += (K-distance);
                distance = 0;
            }
            else { // K를 온전히 뛸 수 있는 경우
                sum += 0;
                distance -= K;
            }

            // 상대
            if(distance < K) { // K보다 적게 뛸 수 있는 경우
                sum += (K-distance);
                distance = 0;
            }
            else { // K를 온전히 뛸 수 있는 경우
                sum += 0;
                distance -= K;
            }

            // Walk
            // - 누가 걷던 똑같다 : 남은 거리 * L
            sum += distance * L;

            if(min > sum) {
                match = i;
                min = sum;
            }
        }

        sb.append(min).append(" ").append(match + 1);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int S;
    static int N;
    static int[] E;
    static int K;
    static int L;
    static int match;
    static int min = Integer.MAX_VALUE;
}