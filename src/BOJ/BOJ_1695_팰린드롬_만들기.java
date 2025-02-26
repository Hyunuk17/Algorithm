package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_1695_팰린드롬_만들기 {
    /*
     * BOJ 1695. 팰린드롬 만들기
     * ----------------------
     * 
     * [문제 설명]
     * 팰린드롬
     * - 앞->뒤, 뒤->앞으로 보았을 때 같은 수열
     * 
     * 수열
     * - N길이
     * - 최소 개수의 수를 끼워 넣어 팰린드롬을 만들기
     * 
     * [입력]
     * N : 수열의 길이
     * 수열
 	 *  
     * [출력]
     * 끼워 넣어야 하는 수의 최소 개수 
	 *
     * [제한사항]
	 * 1 <= N <= 5,000
	 * 
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
		}
        
        
        // 문제풀이
        /*
         * 팰린드롬 만들기
         * - 특정 기준을 중간으로 양쪽이 일치
         * - 투 포인터 : 양쪽 비교
         * 
         * 양쪽이 일치하지 않는 경우
         * - 1. 왼쪽(left)값과 같은 숫자를 추가
         * - 2. 오른쪽(right)값과 같은 숫자를 추가
         * 
         * 최솟값 구하기
         * - dp[left][right] : left부터 right까지 중, 끼워 넣은 수가 최소인 경우의 수
         *  
         *  시간복잡도
         *  - O(N*N)
         */
        
        dp = new int[N][N];
        for(int[] i : dp) {
        	Arrays.fill(i, -1);
        }
        
        int left = 0;
        int right = N-1;
        
        sb.append(topDown(left, right));
         
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
    
    static int N;
    static int[] arr;
    static int[][] dp;
    
    static int topDown(int left, int right) {
    	// Base Case
    	if(left > right) {
    		return 0;
    	}
    	
    	// Memoization
    	if(dp[left][right] != -1) {
    		return dp[left][right];
    	}
    	
    	// Recurrence Relation
    	if(arr[left] == arr[right]) {
    		dp[left][right] = topDown(left+1, right-1);
    	}
    	else {
    		dp[left][right] = Math.min(topDown(left+1, right) + 1, topDown(left, right-1) + 1);
    	}
    	
    	return dp[left][right];
    }
}