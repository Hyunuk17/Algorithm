package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_15831_준표의_조약돌 {
    /*
     * BOJ 15831. 준표의 조약돌
     * --------------------
     * 
     * [문제설몀]
     * 산책로
     * - 일렬로 검은색과 흰색 조약돟이 놓여 있음
     * - 총 N개의 조약돌은 1~N번까지 번호가 붙혀져 있음
     * 
     * 산책
     * - 임의의 지점에서 시작, 원하는 지점에서 빠져나옴
     * - 산책한 구간에 있는 모든 조약돌을 주움
     * - 가능한 한 더 긴 구간을 산책
     * 
     * 조건
     * - 까만색 조약돌을 B개 이하로 줍기
     * - 흰색 조약돌 W개 이상 줍기
     * 
     * 
     * [입력]
     * 총 조약돌의 개수 : N
     * 검은 조약돌의 최대개수 : B
     * 하얀 조약돌의 최소개수 : W
     * 
	 *
     * [출력]
     * 걷게 될 가장 긴 구간의 길이 출력
     * - 조건에 맞는 구간이 없다면 0
     * 
     * [제한사항]
     * 1 <= N <= 300,000
     * 0 <= B, W, B+W <= N
     * 
     * 
     */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st= new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        
        arr = new char[N];
        String str = br.readLine();
        arr = str.toCharArray();
        
        // 문제풀이
        // 300,000만 == 3*10^5
        // O(N) * O(logN) 이하로 풀어야 한다
        // 슬라이드 윈도우 : O(N)
        // - 1번에서 시작 돌을 주으며 진행,
        // - 검은 돌이 B개를 넘으면 중단
        // - 각 과정마다 검은돌, 하얀돌 개수가 조건에 부합하는지 확인
        
        int start = 0;
        int end = 0;
        int wCnt = 0;
        int bCnt = 0;
        int window = 0;
        
        while(end < N) {
        	// 검은 돌을 B개 이상 주웠다면
        	if(bCnt > B) {
        		if(arr[start] == 'W') {
        			wCnt--;
        		}
        		else {
        			bCnt--;
        		}
        		
        		window--;
        		start++;
        	}
        	// 검은 돌을 B개 이하로 주워, 더 주워도 된다면
        	else {
        		if(arr[end] == 'W') {
        			wCnt++;
        		}
        		else {
        			bCnt++;
        		}
        		end++;
        		window++;
        	}
        	
        	if(bCnt<=B && wCnt >= W) {
        		max = Math.max(max, window);
        	}
        }
       
        sb.append(max);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
    
    static int N;
    static int B;
    static int W;
    static char[] arr;
    static int max;
}
