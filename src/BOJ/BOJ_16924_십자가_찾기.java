import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 16924. 십자가 찾기
     * ---------------------
     * 
     * [문제 설명]
     * 십자가
     * - '*'로 이루어진 십자가
     * - 빈공간을 '.'로 채움
     * - 십자가의 크기는 1 이상
     * 
     * 격자판
     * - '*'와 '.'로 이루어진 격자판
     * - NxM
     * - 1-based
     * 
     * 격자판 만들기
     * - 십자가를 사용하여 격자판의 무늬와 일치하도록 만들기
     * 
     * [입력]
     * N, M : 격자판의 크기
     * board[][] : 격자판의 상태
 	 *  
     * [출력]
     * 격자판을 만들 수 없으면 -1
     * 격자판을 만들 수 있으면 필요한 십자가의 수 k 출력
     * - 그려야 하는 십자가의 정보 "x y s"
     * - x : 십자가의 중심 행 번호
     * - y : 십자가의 중심 열 번호
     * - s : 십자가의 크기
	 *
     * [제한사항]
     * 3 <= N, M <= 100
     * 0 <= k <= NxM
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

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            board[i] = line.toCharArray();            
        }

        // 문제풀이
        /*
            격자판 만들기
            - 십자가들을 채워 격자판을 완성해야 함
            - 십자가들은 겹쳐도 상관없음

            Brute Force
            - '*'을 만나면 십자가의 중심으로 판단
            - 십자가 모양으로 뻗을 수 있는지 확인
            - board를 전부 순회하며 반복
            - N, M, K가 충분히 작기 때문에 BF 가능
        */

        result = new ArrayList<>();

        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(board[i][j] == '*') { // 중심으로 취급

                    for(int k=1;;k++) { // size
                        if(i-k < 0 || i + k >= N || j-k < 0 || j+k >= M) {
                            break; // 안됨
                        }

                        // 십자가 가능 여부 확인
                        if(board[i-k][j]=='*' && board[i+k][j]=='*' && board[i][j-k]=='*' && board[i][j+k]=='*') {
                            result.add(new Cross(i, j, k)); // 가능한 십자가를 모두 담기
                        }
                        else {
                            break;
                        }
                    }
                }
            }
        }

        // 구한 십자가 대입
        for(int i=0;i<result.size();i++) {
            Cross cross = result.get(i);

            // '*'을 '.'으로 치환
            for(int size=0;size<=cross.size;size++) {
                board[cross.r-size][cross.c] = '.';
                board[cross.r+size][cross.c] = '.';
                board[cross.r][cross.c-size] = '.';
                board[cross.r][cross.c+size] = '.';
            }
        }

        // 격자판 모양 완성 확인
        boolean isComplete = true;
        for(int i=0;i<N;i++) {
            for(int j=0;j<M;j++) {
                if(board[i][j] == '*') {
                    isComplete = false;
                    break;
                }
            }
        }
        
        // 출력
        if(isComplete) {
            sb.append(result.size()).append("\n");
            for(int i=0;i<result.size();i++) {
                Cross cross = result.get(i);
                sb.append(cross.r+1).append(" ").append(cross.c+1).append(" ").append(cross.size).append("\n");
            }
        }
        else {
            sb.append(-1);
        }

        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int M;
    static char[][] board;
    static List<Cross> result;

    static class Cross {
        int r;
        int c;
        int size;

        public Cross(int r, int c, int size) {
            this.r = r;
            this.c = c;
            this.size = size;
        }
    }
}