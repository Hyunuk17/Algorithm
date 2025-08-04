import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 1388. 바닥 장식
     * ------------------
     * 
     * [문제 설명]
     * 나무 판자
     * - 너비 1
     * - 길이 양수
     * 
     * 바닥 장식 모양
     * - '-', '|'
     * 
     * [입력]
     * N : 방 바닥의 세로 크기
     * M : 방 바닥의 가로 크기
 	 *  
     * [출력]
     * 방 바닥을 장식하는데 필요한 나무 판자의 개수
	 *
     * [제한사항]
     * 1 <= N, M <= 50
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][];

        for(int i=0;i<N;i++) {
            String line = br.readLine();
            board[i] = line.toCharArray();
        }

        // 문제풀이
        /*
            가로, 세로 Sweep
        */

        int cnt = 0;
        for(int i=0;i<N;i++) {
            char pre = ' ';
            for(int j=0;j<M;j++) {
                char c = board[i][j];
                if(pre != c && c == '-') {
                    cnt++;
                }
                pre = c;
            }
        }
        
        for(int j=0;j<M;j++) {
            char pre = ' ';
            for(int i=0;i<N;i++) {
                char c = board[i][j];
                if(pre != c && c == '|') {
                    cnt++;
                }
                pre = c;
            }
        }

        sb.append(cnt);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}