package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_26093_고양이_목에_리본_달기 {
    /*
     * BOJ 26093. 고양이 목에 리본 달기
     * ----------------------------
     * 
     * [문제 설명]
     * 고양이 
     * - N마리
     * 
     * 리본 
     * - K 종류
     * - 각 리본의 개수는 무한
     * - 각 고양이마다 리본의 종류에 따른 만족도가 다름
     * 
     * 리본 달기
     * - 각 고양이는 이웃한 고양이와 다른 리본 달기
     * - 만족도의 총합의 최대가 되도록 달기
     * 
     * [입력]
     * N : 고양이의 수
     * K : 리본 종류의 수
 	 * a : 만족도 2차원 배열 
 	 *  
     * [출력]
     * 리본을 달았을 때 만족도의 최댓값
	 *
     * [제한사항]
	 * 1 <= N <= 100
	 * 2 <= K <= 10,000
	 * 1 <= a[i][j] <= 10,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        a = new int[N][K];
        for(int i=0;i<N;i++) {
        	st = new StringTokenizer(br.readLine());
        	for(int j=0;j<K;j++) {
        		a[i][j] = Integer.parseInt(st.nextToken());
        	}
        }
        
        // 문제풀이
        /*
         *  리본달기
         *  - 고양이의 양 옆에 같은 리본을 달 수 없음
         *  
         *  DP
         *  - dp[i][j] : i-1번 고양이까지 리본을 달았고, i번 고양이가 j번 리본을 달 때 만족도의 최댓값
         *  - for(int k=0;k<K;k++) : i-1번 고양이가 j번이 아닌 리본을 단 경우 중, 최대를 선택
         *  - O(N*K*K) : 10^2 * 10^4 * 10^4 = 10^10 => TLE
         *  
         *  시간복잡도 갱신
         *  - 상위 2개만 저장해가며 사용
         *  - N * 2K
         *  - O(N *K) : 10^2 * 10^4 = 10^6
         */
        
        dp = new int[N][K];
        top2 = new int[N][2]; // i번째 꽃에서 최대 만족도/그 다음 만족도를 가지는 꽃 번호
        for(int i=0;i<K;i++) {
        	dp[0][i] = a[0][i];
    	}
        
        // Bottom-up
        for(int i=1;i<N;i++) {
        	int t1 = -1;
        	int t2 = -1;
        	int pre = -1;
        	
        	// 이전 top2 찾기
        	for(int j=0;j<K;j++) {
        		if(dp[i-1][j] > t1) { // t1 갱신 필요
        			t2 = t1; // t1 -> t2로 변경
        			t1 = dp[i-1][j]; // t1 갱신
        			pre = j;
        		}
        		else if(dp[i-1][j] > t2) { // t1에는 못미치지만, t2보다 큼
        			t2 = dp[i-1][j];
        		}
        	}
        	
        	// Recurrence Relation
        	for(int j=0;j<K;j++) {
        		if(j == pre) { // 이전에 사용한 꽃이랑 겹치는 경우
        			dp[i][j] = t2 + a[i][j];
        		}
        		else {
        			dp[i][j] = t1 + a[i][j];
        		}
        	}
        }
        
        for(int i=0;i<K;i++) {
        	max = Math.max(max,  dp[N-1][i]);
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
    static int[][] a;
    static int[][] dp;
    static int[][] top2;
    static int max;
    
}