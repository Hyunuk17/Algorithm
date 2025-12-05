import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_1758_알바생_강호 {
    /*
     * BOJ 1758. 알바생 강호
     * --------------------
     * 
     * [문제 설명]
     * 손님
     * - N명
     * - 입장 순서대로 번호를 부여 (1~N)
     * 
     * 팁
     * - 원래 주려고 생각했던 돈 - (받은 등수 - 1)
     * - 음수라면 팁을 주지 않음
     * 
     * [입력]
     * N : 손님의 수
     * tip[] : 각 사람이 주려고 하는 팁
 	 *  
     * [출력]
     * 손님의 순서를 바꿀 수 있을 때, 받을 수 있는 최대의 팁 구하기
	 *
     * [제한사항]
     * 1 <= N <= 100,000
     * 1 <= tip[i] <= 100,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = Integer.parseInt(br.readLine());
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<N;i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        
        // 문제풀이
        /*
            최대 팁 구하기
            - [큰 팁-빠른 등수]일 때 최대

            자료형
            - 사람 : 10*5
            - 팁 : 10^5
            - 최댓값 : 10^5 * 10^5 = 10^10 => Long
        */

        Collections.sort(list, (o1, o2) -> {
            return o2 - o1;
        });
        
        long sum = 0;
        for(int i=1;i<=list.size();i++) {
            int tip = list.get(i-1) - (i - 1);
            if(tip < 0) {
                tip = 0;
            }

            sum += tip;
        }

        sb.append(sum);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}