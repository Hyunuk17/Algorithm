import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 3212. 피자
     * --------------
     * 
     * [문제 설명]
     * 먹을 수 있는 피자의 양
     * - 1/4, 1/2, 3/4 중 하나
     * 
     * 정확히 한 조각씩 먹기
     * - 온전한 1/4, 1/2, 3/4 크기로만 받아 먹어야 함
     * 
     * [입력]
     * N : 친구의 수
     * pizza[] : 먹을 수 있는 피자의 양
 	 *  
     * [출력]
	 * 최소 시켜야 하는 피자 판 수
     * 
     * [제한사항]
	 * 1 <= N <= 10,000
     * 
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N  = Integer.parseInt(br.readLine());
        int[] pieceCnt = new int[3];
        for(int i=0;i<N;i++) {

            String str = br.readLine();

            if(str.equals("1/4")) {
                pieceCnt[0]++;
            }
            else if(str.equals("1/2")) {
                pieceCnt[1]++;
            }
            else {
                pieceCnt[2]++;
            }
        }
        
        // 문제풀이
        /**/

        int cnt = 0;
        while(pieceCnt[2] > 0) {
            if(pieceCnt[0] > 0) {
                pieceCnt[0]--;
            }

            pieceCnt[2]--;
            cnt++;
        }

        int half =  pieceCnt[1] / 2;
        pieceCnt[1] -= half *2;
        cnt += half;

        while(pieceCnt[1] > 0) {
            if(pieceCnt[0] > 1) {
                pieceCnt[0]--;
            }
            
            if(pieceCnt[0] > 0) {
                pieceCnt[0]--;
            }

            pieceCnt[1]--;
            cnt++;
        }

        cnt += pieceCnt[0] / 4 + (pieceCnt[0] % 4 > 0 ? 1 : 0);

        sb.append(cnt);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}