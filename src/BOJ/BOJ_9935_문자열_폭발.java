import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 9935. 문자열 폭발
     * --------------------
     * 
     * [문제 설명]
     * 폭발 문자열
     * - 폭발 문자열이 폭발하면 문자열 삭제
     * - 남은 문자열들은 합쳐짐
     * - 같은 문자를 2개 이상 포함하지 않음
     * 
     * 폭발 과정
     * - 문자열에 폭발 문자열이 포함되어 있다면, 폭발
     * - 폭발 이후 남은 문자열을 결합
     * - 새로 생성된 문자열에 폭발 문자열이 포함되어 있다면 재폭발
     * - 반복
     * 
     * 
     * [입력]
     * S : 문자열
     * boom : 폭발 문자열
 	 *  
     * [출력]
     * 폭발 후 남은 문자열 출력
     * 남은 문자열이 없으면 "FRULA" 출력
	 *
     * [제한사항]
     * 1 <= S.length() <= 1,000,000
     * 1 <= boom.length() <= 36
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        S = br.readLine();
        boom = br.readLine();
        
        // 문제풀이
        /*
            문자열 폭발
            - 모든 문자를 순회하며, O(N) <= 1,000,000
            - 폭발 문자열을 검사, O(M) <= 36
            - 일치하면 폭발, O(1)
            - 새로운 문자열을 생성,
            - 처음으로 돌아가 반복
            => O(N^2) : 10^6 * 10^6 => TLE

            스택
            - 현재까지 push된 문자열이 폭발 문자열인지 확인 : stack.size() == boom.length()
            - 폭발 문자열이라면, 그 길이만큼 pop : O(m) <= 36
            - 폭발 문자열이 아니라면, 계속 push : O(N)
            - 반복 (자동으로 새 문자열 생성 및 압축됨)
            - O(NM)
        */
        
        Deque<Character> stack = new ArrayDeque<>();

        for(int i=0;i<S.length();i++) {
            char c = S.charAt(i);
            stack.push(c);

            // 새로 들어온 문자를 포함해 계속 커지는 stack.size()를 반영하여 >=
            if(stack.size() >= boom.length()) {
                boolean isBoom = true;
                
                Iterator<Character> it = stack.iterator();  // 순회를 위한 Iterator top부터 시작
                for (int j=boom.length()-1;j>=0;j--) {
                    if (!it.hasNext() || it.next() != boom.charAt(j)) {
                        isBoom =false;
                        break;
                    }
                }

                if(isBoom) {
                    for(int j=0;j<boom.length();j++) {
                        stack.pop();
                    }
                }
            }
        }

        if(stack.isEmpty()) {
            sb.append("FRULA");
        }
        else {
            while(!stack.isEmpty()) {
                sb.append(stack.removeLast()); // 오래된 것부터 remove
            }
        }
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static String S;
    static String boom;
}