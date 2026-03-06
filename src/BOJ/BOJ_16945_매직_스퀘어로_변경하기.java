import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    /*
     * BOJ 16945. 매직 스퀘어로 변경하기
     * ------------------------------
     * 
     * [문제 설명]
     * 매직 스퀘어
     * - NxN 배열
     * - 1~N^2까지의 수가 하나씩 채워져 있음
     * - 모든 행, 열, 길이가 N인 대각선의 합이 같음
     * 
     * 매직 스퀘어 만들기
     * - 3x3 배열 A
     * - 한 칸에 있는 수 a를 b로 변경하는 비용 |a-b|
     * 
     * [입력]
     * A[][] : 기본 3x3 배열
 	 *  
     * [출력]
     * 배열 A를 매직 스퀘어로 변경하는 비용의 최솟값
	 *
     * [제한사항]
     * 1 <= A[i][j] <= 9
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        A = new int[3][3];
        for(int i=0;i<3;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0;j<3;j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        // 문제풀이
        /*
            BF
            - 모든 경우의 수 중 매직 스퀘어인 경우와 비교
            - 9!
        */

        tmp = new int[3][3];
        visited = new boolean[9];
        brute_force(0);
        
        sb.append(min);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int[][] A;
    static int[][] tmp;
    static int min = Integer.MAX_VALUE;
    static boolean[] visited;

    static void brute_force(int depth) {
        if(depth == 9) {
            if(checkMagicSquare()) {
                min = Math.min(min, calc());
            }

            return;
        }

        for(int i=0;i<9;i++) {
            if(visited[i] == true) {
                continue;
            }

            int c = depth % 3;
            int r = depth / 3;

            tmp[r][c] = i+1;
            visited[i] = true;
            brute_force(depth+1);
            visited[i] = false;
        }
    }

    static boolean checkMagicSquare() {
        int standard = tmp[0][0] + tmp[0][1] + tmp[0][2];

        // 열
        for(int i=0;i<3;i++) {
            int sum  = 0;
            for(int j=0;j<3;j++) {
                sum += tmp[i][j];
            }

            if(sum != standard) {
                return false;
            }
        }

        // 행
        for(int j=0;j<3;j++) {
            int sum  = 0;
        
            for(int i=0;i<3;i++) {
                sum += tmp[i][j];
            }

            if(sum != standard) {
                return false;
            }
        }

        // 대각
        int sum1 = tmp[0][0] + tmp[1][1] + tmp[2][2];
        if(sum1 != standard) {
            return false;
        }

        int sum2 = tmp[0][2] + tmp[1][1] + tmp[2][0];
        if(sum2 != standard) {
            return false;
        }

        return true;
    }

    static int calc() {
        int cost = 0;

        for(int i=0;i<3;i++) {
            for(int j=0;j<3;j++) {
                cost += Math.abs(A[i][j] - tmp[i][j]);
            }
        }

        return cost;
    }
}