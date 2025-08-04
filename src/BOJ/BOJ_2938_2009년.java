import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    /*
     * BOJ 2948. 2009년
     * ----------------
     * 
     * [문제 설명]
     * 2009년의 날짜를 받아 요일을 출력
     * 
     * [입력]
     * M : 월
     * D : 일
 	 *  
     * [출력]
     * 요일을 영어로 출력
	 *
     * [제한사항]
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] days = new int[] {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // 문제풀이
        /*
            2009.01.01은 목요일(Thursday)
        */

        int sum = 0;
        for(int i=0;i<M-1;i++) {
            sum += days[i];
        }
        sum += D;

        String dayOfTheWeek = "";
        int result = (sum-1) % 7;
        switch(result) {
            case 0:
                dayOfTheWeek = "Thursday";
                break;
            case 1:
                dayOfTheWeek = "Friday";
                break;
            case 2:
                dayOfTheWeek = "Saturday";
                break;
            case 3:
                dayOfTheWeek = "Sunday";
                break;
            case 4:
                dayOfTheWeek = "Monday";
                break;
            case 5:
                dayOfTheWeek = "Tuesday";
                break;
            case 6:
                dayOfTheWeek = "Wednesday";
                break;
        }

        sb.append(dayOfTheWeek);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}