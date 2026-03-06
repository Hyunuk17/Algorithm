import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 16922. 로마 숫자 만들기
     * -------------------------
     * 
     * [문제 설명]
     * 로마 숫자
     * - I : 1
     * - V : 5
     * - X : 10
     * - L : 50
     * 
     * 수 나타내기
     * - 로마 숫자 문자열의 합
     * - 순서는 상관없음
     * 
     * [입력]
     * N : 사용할 수 있는 문자의 개수
 	 *  
     * [출력]
     * 로마 숫자 N개를 사용하여 만들 수 있는 서로 다른 수의 개수
	 *
     * [제한사항]
     * 1 <= N <= 20
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        
        // 문제풀이
        /*
            조합 
            - 순서가 상관없는 문자열
            - N <= 20, (n <= 30까지 일반적으로 가능)
            
            중복조합의 시간복잡도 
            - O(2^N) 
            - N<20 : O(4^N) => O(2^2N) => O(2^40) => TLE
            - 가지치기를 통해 경우의 수를 줄이도록 유도하는 문제
            - for문의 시작 인덱스를 start로 설정, nCstart의 효과
        */
        
        set = new HashSet<>();
        combination(0,0,0);

        sb.append(set.size());
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int cnt;
    static int[] numbers = {1, 5, 10, 50};
    static Set<Integer> set;

    static void combination(int start, int depth, int sum) {
        if(depth == N) {
            set.add(sum);
            return;
        }

        for(int i=start;i<4;i++) {
            combination(i, depth+1, sum + numbers[i]);
        }
    }
}