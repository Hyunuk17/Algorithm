package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_1062_가르침 {
    /*
     * BOJ 1062. 가르침
     * --------------
     * 
     * [문제설몀]
     * 선생님이 K개의 글자를 가르칠 것
     * - 학생들은 가르친 K개의 글자로만 이루어진 단어만을 읽을 수 있음
     * - 학생이 읽을 수 있는 단어의 개수가 최대가 되도록하는 K게 글자 가르치기
     * 
     * 남극언어
     * - anta로 시작, tica로 끝
     * - N개 단어
     * - 학생이 읽을 수 있는 단어의 최대 개수 구하기
     * 
     * [입력]
     * 단어의 개수 N <= 50
     * 글자의 수 K <= 26
     * 8 <= arr[i] <= 15
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
  
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new String[N];
        alpha = new boolean[26];
        
        for(int i=0;i<N;i++) {
        	arr[i] = br.readLine();
        }
        
        alpha['a'-'a'] = true;
        alpha['n'-'a'] = true;
        alpha['t'-'a'] = true;
        alpha['i'-'a'] = true;
        alpha['c'-'a'] = true;
        
        // 문제풀이
        if(K < 5) {
        	max = 0;
        }
        else {
        	DFS(0, 0);
        }

        sb.append(max);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    } 
    
    static int N;
    static int K;
    static String[] arr;
    static boolean[] alpha;
    static int max;
    
    static void DFS(int idx, int cnt) {
    	if(cnt + 5 == K) { // K개 알파벳 선택 완료
    		countWord();
    		return;
    	}
    	
    	for(int i = idx;i<26;i++) {
    		if(alpha[i] == true) { // 이미 선택된 알파벳이면 continue
    			continue;
    		}
    		
    		
    		alpha[i] = true; // 현재 알파벳 선택
    		DFS(i+1, cnt+1); 
    		alpha[i] = false;
    	}
    }
    
    static void countWord() {
    	int cnt = 0;
    	for(int i=0;i<N;i++) { // N개 단어 순회
    		char[] word = arr[i].toCharArray();
    		boolean flag = true;
    		
    		for(char c : word) { // 각 단어의 글자 확인
    			 if(alpha[c - 'a'] == false) { // 해당 글자를 못 읽는 경우
    				 flag = false; 
    				 break;
    			 }
    		}
    		
    		// 모든 글자를 읽은 경우
    		if(flag == true) {
    			cnt++; // 읽을 수 있는 단어 개수 + 1
    		}
    	}
    	
    	max = Math.max(max, cnt);
    }
}
