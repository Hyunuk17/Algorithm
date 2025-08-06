import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    /*
     * BOJ 10798. 세로 읽기
     * -------------------
     * 
     * [문제 설명]
     * 자석 장난감
     * - 'A' to 'Z', 'a' to 'z', '0' to '9'
     * - 수평으로 여러 글자를 붙여 단어를 만듬, 최대 15글자
     * - 총 5단어
     * 
     * 세로 읽기
     * - 맨 위부터 세로로 읽어나가기
     * - 중간에 글자가 없으면 그 다음 글자 읽기
     * 
     * [입력]
     * str[] : 입력 문자열 5개
 	 *  
     * [출력]
     * 글자를 읽은 순서대로 공백없이 연속해서 출력
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        char[][] arr = new char[5][15];
        for(int i=0;i<arr.length;i++) {
            String str = br.readLine();

            for(int j=0;j<str.length();j++) {
                arr[i][j] = str.charAt(j);
            }
        }

        // 문제풀이
        /**/

        for(int j=0;j<15;j++) {
            for(int i=0;i<5;i++) {
                if(arr[i][j] != 0) { // 기본값, unicode 0
                    sb.append(arr[i][j]);
                }
            }
        }
        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}