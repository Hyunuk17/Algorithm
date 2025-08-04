import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 33961. 체스평평설
     * --------------------
     * 
     * [문제 설명]
     * 나숍(Knishop)
     * - 나이트 + 비숍
     * - 1. 2x1만큼 대각선 이동
     * - 2. 원하는 만큼 대각선 이동
     * 
     * Knishop-tour
     * - 모든 체스판의 칸을 정확히 1번씩 순회
     * - 시작 위치 제한 없음
     * 
     * 체스판
     * - 2xM
     * 
     * [입력]
     * M : 체스판의 크기
     * 
 	 *  
     * [출력]
     * knishop-tour가 가능하다면 YES, 불가능하다면 NO
     * Knishop-tour의 경로 출력 (x_i, y_i)
	 *
     * [제한사항]
	 * 1 <= M <= 10,000
     * 1 <= x_i <= 2
     * 1 <= y_i <= M
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        sb = new StringBuilder();
        
        M = Integer.parseInt(br.readLine());
        board = new boolean[2][M];

        // 문제풀이
        /*
            Knishop-tour
            - 모든 시작점에서 시작 : 2*10^4
            - 2xM의 모든 칸을 순회 : 2*10^4
            - Greedy?

            M = 1, 3의 배수가 아닌 2의 배수 : 불가능
            M = 3의 배수 : 항상 가능
        */
        
        if(M % 3 != 0) {
            sb.append("NO");
        }
        else {
            sb.append("YES").append("\n");

            for(int i=0;i<M/3;i++) {
                traversal(i * 3 + 1);
            }
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int M;
    static boolean[][] board;   
    static StringBuilder sb;
    static String result;

    static void traversal(int start) {
        sb.append(1).append(" ").append(start).append("\n");
        sb.append(2).append(" ").append(start+2).append("\n");
        sb.append(1).append(" ").append(start+1).append("\n");
        sb.append(2).append(" ").append(start).append("\n");
        sb.append(1).append(" ").append(start+2).append("\n");
        sb.append(2).append(" ").append(start+1).append("\n");
    }
}