import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2929_머신_코드 {
    /*
     * BOJ 2929. 머신 코드
     * ------------------
     * 
     * [문제 설명]
     * 머신 코드
     * - [명령, 파라미터] 구조의 연결
     * - 파라미터는 여러 개 가능
     * - 명령 : 대문자
     * - 파라미터 : 소문자
     * - 각 칸 1개당 1byte
     * 
     * 프로세서
     * - 4btye 단위로 읽음
     * - 명령의 시작은 4의 배수 위치
     * - NOP 명령을 추가하여 빈 공간을 채우기
     * 
     * [입력]
     * code : 기존의 머신 코드 문자열
 	 *  
     * [출력]
     * 실행을 위해 삽입해야하는 NOP 개수의 최솟값
	 *
     * [제한사항]
     * 1 <= code.length() <= 200
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        String code = br.readLine();

        // 문제풀이
        /**/

        int nopCount = 0;
        int remainder = 0;

        for(int i=1;i<code.length();i++) {
            char c = code.charAt(i);

            if('A' <= c && c <= 'Z') {
                int size = i - remainder;
                if(size % 4 != 0) {
                    nopCount += (4 - (size % 4));
            }   

                remainder = i;    
            }
        }

        // // 마지막 명령 처리
        // int size = code.length() - remainder; 
        // if(size % 4 != 0) {
        //     System.out.println("size: " + size + ", nopCount: " + nopCount);
        //     nopCount += (4 - (size % 4));
        // }   
                
        sb.append(nopCount);        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}