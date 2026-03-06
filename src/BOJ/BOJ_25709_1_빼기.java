import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_25709_1_빼기 {
    /*
     * BOJ 25709. 1 빼기
     * -----------------
     * 
     * [문제 설명]
     * 1 빼기
     * - 1. 가지고 있는 수에서 1을 빼기
     * - 2. 가지고 있는 수에 있는 1을 지움, 맨 앞의 0은 무시됨
     * 
     * [입력]
 	 * N : 정수
     * 
     * [출력]
     * 수 N을 0으로 만들기 위해 필요한 1 빼기 연산 횟수
	 *
     * [제한사항]
	 * 1 <= N <= 10^9
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());

        // 문제풀이
        /*
          1 빼기 연산
          - 1. N--  
          - 2. for() if(chatAt(i) == 1) 삭제

          압축
          - String -> Integer 변환

          최소횟수
          - 2번이 항상 우선 => Greedy
          - DFS로 구현
        */
        
        DFS(N, 0);
        sb.append(min);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int min = Integer.MAX_VALUE;

    static boolean DFS(int num, int count) {
        if(num == 0) {
            min = Math.min(min, count);
            return true;
        }

        if(containsOne(num)) {
            int compressed = compress(num);
            if(DFS(compressed, count+1)) {
                return true;
            }
        }

        if(DFS(num-1, count+1)) {
            return true;
        };

        return false;
    }

    static boolean containsOne(int num) {
        String str = String.valueOf(num);
        for(int i=0;i<str.length();i++) {
            char c = str.charAt(i);

            if(c == '1') {
                return true;
            }
        }

        return false;
    }

    static int compress(int num) {
        String str = String.valueOf(num);
        for(int i=0;i<str.length();i++) {
            char c = str.charAt(i);

            if(c == '1') {
                str = str.substring(0, i) + str.substring(i+1, str.length());
                break;
            }
        }

        if(str.equals("")) {
            str = "0";
        }

        return Integer.parseInt(str);
    }
}