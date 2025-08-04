import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 2892. 심심한 준규
     * --------------------
     * 
     * [문제 설명]
     * 암호화 메시지
     * - OTP (One Time Pad) 방식
     * - 해독할 수 있도록 몇 개의 힌트를 첨부
     * 
     * 메시지 구성
     * - 영소문자
     * - 온점(.)
     * - 공백(' ', ASCII 32)
     * - key값 : 0~9
     * 
     * 암호화 과정
     * - 1. 메시지 원문(Plain Text)과 key를 ASCII로 변환
     * - 2. 16진수로 변환 
     * - 3. 변환한 원문과 key를 각각 XOR 연산
     * 
     * 
     * [입력]
     * N : 암호화된 메시지 길이
     * text[] : 암호화된 메시지의 16진수값
 	 *  
     * [출력]
     * i번째 글자가 문자면 '-'
     * i번째 글자가 문자가 아니라면 '.'
	 *
     * [제한사항]
     * 1 <= N <= 1,000
     * 0 <= text[] <= 127
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        encryptedText = new String[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            encryptedText[i] = st.nextToken();
        }
        
        // 문제풀이
        /**/

        // '.',' '의 ASCII값과 key의 XOR값을 저장
        exceptionSet = new HashSet<>();
        char[] exception = {'.', ' '};
        for(int i=0;i<2;i++) {
            for(int j='0';j<='9';j++) {
                exceptionSet.add(exception[i] ^ j);
            }
        }

        // 암호문을 순회
        for(int i=0;i<N;i++) {
            int decimal = Integer.parseInt(encryptedText[i], 16); //  16진수 값을 10진수로 변환
            if(exceptionSet.contains(decimal)) { // '.', ' '에 해당하는지 판단
                sb.append('.');
            }
            else {
                sb.append('-');
            }
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static String[] encryptedText;
    static Set<Integer> exceptionSet;
}