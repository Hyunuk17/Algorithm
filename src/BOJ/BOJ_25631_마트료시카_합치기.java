import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 25631. 마트료시카 합치기
     * --------------------------
     * 
     * [문제 설명]
     * 마트료시카
     * - N개
     * - i번째 마트료시카의 크기 a_i
     * - 속은 비어있음
     * 
     * 마트료시카 합치기
     * - i번쨰 마트료시카를 j번째 마트료시카 속에 넣음
     * - 1. 마트료시카 j의 속이 비어있어야 함
     * - 2, 마트료시카 j가 i보다 커야함
     * - 합치면 전체 개수가 1개 줄어듬
     * 
     * [입력]
 	 * N : 마트료시카 개수
     * a[] : i번째 마트료시카 크기
     * 
     * [출력]
     * 남아있는 마트료시카의 개수
	 *
     * [제한사항]
     * 1 <= N <= 1,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N;
        N = Integer.parseInt(br.readLine());

        int[] a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }
        
        // 문제풀이
        /*
            1. 정렬?
            2. 크기 비교 : 가장 많은 크기의 마트료시카 수 == 합쳐진 마트료시카 수

        */

        Arrays.sort(a);

        boolean[] empty = new boolean[N];
        for(int i=0;i<N;i++) {
            for(int j=i+1;j<N;j++) {                
                if(a[i] < a[j] && empty[j] == false) {
                    empty[j] = true;
                    break;
                }
            }
        }

        int cnt = 0;
        for(boolean b : empty) {
            if(b == false) {
                cnt++;
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