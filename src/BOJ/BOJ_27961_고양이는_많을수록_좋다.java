import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 27961. 고양이는 많을수록 좋다
     * ------------------------------
     * 
     * [문제 설명]
     * 고양이
     * - N 마리
     * 
     * 마법
     * - 생성 마법 : 고양이 1마리를 집에 생성
     * - 복제 마법 : 현재 k마리 고양이 존재 시, 0마리 이상 k마리 이하를 추가
     * 
     * [입력]
     * N : 키우기를 원하는 고양이의 수
 	 *  
     * [출력]
     * 정확히 N마리 고양이 만들기 최소 마법 횟수 
	 *
     * [제한사항]
     * 0 <= N <=10^12
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        long N = Long.parseLong(br.readLine());
        
        // 문제풀이
        /**/
        
        long cnt = 0;
        long cat = 0;

        if(N != 0) {
            cat = 1;
            cnt = 1;
            while(true) {
                if(cat == N) {
                    break;
                }
                
                if((N - cat)  > cat) {
                    cat *= 2;
                }
                else {
                    cat += (N - cat);
                }

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