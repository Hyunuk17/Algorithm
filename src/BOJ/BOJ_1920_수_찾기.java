package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_1920_수_찾기 {
    /*
     * BOJ 1920. 수 찾기
     * ----------------
     * 
     * [문제설몀]
     * N개의 정수
     * - X라는 정수가 존재하는지 확인
     * 
     * [입력]
     * 1 <= N <= 100,000
     * 1 <= M <= 100,000
 	 *  
     * [출력]
     * 
     * [제한사항]

     */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        // 문제풀이
        Set<Integer> set = new HashSet<>();
        
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
        	set.add(Integer.parseInt(st.nextToken()));
        }
        
        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<M;i++) {
        	if(set.contains(Integer.parseInt(st.nextToken()))) {
        		sb.append(1);
        	}
        	else {
        		sb.append(0);
        	}
        	
        	sb.append("\n");
        }
        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
    
    static int N;
    static int M;
 }
