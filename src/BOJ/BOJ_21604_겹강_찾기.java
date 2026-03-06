import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_21604_겹강_찾기 {
    /*
     * BOJ 21604. 겹강 찾기
     * -------------------
     * 
     * [문제 설명]
     * 강의 듣기
     * - 회원 M명
     * - 과목 N개, 여러 분반 존재
     * - 각 회원은 모두 서로 다른 분반을 수강 : 함께 수업을 듣는 사람 없음
     * 
     * 상상 속 친구
     * - K명
     * - 회원들과 같은 종류의 과목을 수강
     * - 분반은 자유롭게 결정
     * - 각 사람별로 모든 과목에서 상상 속 친구과 같이 수업을 들음
     * - 특정 회원과 모든 과목, 모든 분반이 일치하는 상상 속 친구는 존재하지 않음
     * 
     * [입력]
     * N : 과목의 수
     * M : 회원의 수
     * A[][] : i번 회원이 수강하는 j번 과목의 분반 정보
 	 *  
     * [출력]
     * 상상 속 친구의 수 K
     * B[] : 상상 속 친구 i가 듣는 j번 과목의 분반 정보
	 *
     * [제한사항]
     * 2 <= N <= 1,000
     * 2 <= M <= 1,000
     * 0 <= K <= M
     * 
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[M][N];

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 문제풀이
        /*
            상상 속 친구
            - K명
            - 전체가 일치하지만 않으면, 사람과 상상 속 친구가 같이 들어야 함

            한칸씩 밀기
            - 이론적으로 과목당 수강을 1~N-1까지 아래/위로 밀면, 겹치는 것이 없다
            - 시간 복잡도 O(n(n+1)/2) => O(n^2) => 10^6
        */

        K = M;
        result = new int[K][N];

        for(int i=0;i<N;i++) {
            move(i, i);
        }

        sb.append(K).append("\n");
        for(int i=0;i<M;i++) {
            for(int j=0;j<N;j++) {
                sb.append(result[i][j]).append(" ");
            }
            sb.append("\n");
        }

        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int M;
    static int K;
    static int[][] board;
    static int[][] result;
    
    static void move(int line, int size) {
        for(int i=0;i<M;i++) {
            if((i - size) < 0) {
                result[i][line] = board[(M-1) - i][line];
            } 
            else {
                result[i][line] = board[i - size][line]; // 아래로 밀가
            }
        }
    }
}