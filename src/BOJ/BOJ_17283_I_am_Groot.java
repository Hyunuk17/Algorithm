import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_17283_I_am_Groot {
    /*
     * BOJ 17283. I am Groot
     * ---------------------
     * 
     * [문제 설명]
     * 나뭇가지
     * - 자랄 때 마다 가지가 2개로 갈라짐
     * - N번째 나뭇가지의 길이 : N-1번째 나뭇가지의 길이 * R/100에 소숫점 이하 버림
     * - N번째 나뭇가지의 길이가 5cm 이하인 경우, N-1번째 가지에서 성장 멈춤
     * 
     * 
     * [입력]
 	 * L : 중심 줄기의 길이 (0번째)
     * R : 비율
     * 
     * [출력]
     * 중심 줄기를 제외한 나뭇가지의 길이의 합
	 *
     * [제한사항]
     * 6 <= L <= 10,000
     * 1 <= R <= 99
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int L = Integer.parseInt(br.readLine());
        int R = Integer.parseInt(br.readLine());
        
        // 문제풀이
        /**/

        int sum = 0;
        int branchCount = 1;

        while(true) {
            L = (L * R) / 100;
            branchCount *= 2;
            if(L <= 5) break;

            sum += L * branchCount;
        }

        sb.append(sum);        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}