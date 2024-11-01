import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 11983. Radio Contact
     * ------------------------
     * 
     * [문제설명]
     * 농부 John은 아끼는 소 Bell을 잃어버림
     * Bessie가 찾는 걸 도와주기로 함
     * 
     * 둘은 각각 다른 길로 농장을 수색, 각자의 라디오를 통해 연락을 유지
     * 배터리가 부족하여, 근처에서 움직이기로 함
     * 
     * 농부 John은 f(x, y)에서 시작하여 N단계 이동, N(북), E(동), S(남), W(서)
     * Bessie는 b(x, y)에서 시작하여 M단계 이동
     * 
     * 두 경로는 일부 지점에서 만날 수 있음
     * 각 단계에서 현재 위치에 머물거나, 한 칸 이동(마지막 위치가 아니라면)
     * 
     * 각 단계에서, 그들의 라디오는 에너지를 소비, 둘 사이의 거리의 제곱만큼
     * 
     * 두 사람이 마지막 단계에 도달하기 까지, 에너지 소비를 최소화하는 경로 구하기
     *      
     * [제한사항]
     * 1 <= N, M <= 1,000 : 단계
     * 0 <= fx*fy*bx*by <= 1,000 : 시작점
     * 
     * 
     */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        fx = Integer.parseInt(st.nextToken());
        fy = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        bx = Integer.parseInt(st.nextToken());
        by = Integer.parseInt(st.nextToken());
        
        fStep = br.readLine().toCharArray();
        fPositions = new ArrayList<>();
        fPositions.add(new Node(fx, fy));
        for(int i=0;i<fStep.length;i++) {
        	int nx = fPositions.get(i).x;
        	int ny = fPositions.get(i).y;
        	
        	char c = fStep[i];
        	switch(c) {
        	case 'N':
        		nx += dx[0];
        		ny += dy[0];
        		break;
        	case 'E':
        		nx += dx[1];
        		ny += dy[1];
        		break;
        	case 'S':
        		nx += dx[2];
        		ny += dy[2];
        		break;
        	case 'W':
        		nx += dx[3];
        		ny += dy[3];
        		break;
        	}
        	
        	if(nx < 0 || ny < 0 || nx >= 1000 || ny >= 1000) {
        		fPositions.add(new Node(fPositions.get(i).x, fPositions.get(i).y));
        	}
        	else {        		
        		fPositions.add(new Node(nx, ny));
        	}
        }
        
        bStep = br.readLine().toCharArray();
        bPositions = new ArrayList<>();
        bPositions.add(new Node(bx, by));
        for(int i=0;i<bStep.length;i++) {
        	int nx = bPositions.get(i).x;
        	int ny = bPositions.get(i).y;
        	
        	char c = bStep[i];
        	switch(c) {
        	case 'N':
        		nx += dx[0];
        		ny += dy[0];
        		break;
        	case 'E':
        		nx += dx[1];
        		ny += dy[1];
        		break;
        	case 'S':
        		nx += dx[2];
        		ny += dy[2];
        		break;
        	case 'W':
        		nx += dx[3];
        		ny += dy[3];
        		break;
        	}
        	
        	if(nx < 0 || ny < 0 || nx >= 1000 || ny >= 1000) {
        		bPositions.add(new Node(bPositions.get(i).x, bPositions.get(i).y));
        	}
        	else {        		
        		bPositions.add(new Node(nx, ny));
        	}
        	
        }
        
//        for(int i=0;i<=N;i++) {
//        	System.out.print("[" + fPositions.get(i).x + "," + fPositions.get(i).y + "], ");
//        }
//        System.out.println();
//        for(int i=0;i<=M;i++) {
//        	System.out.print("[" + bPositions.get(i).x + "," + bPositions.get(i).y + "], ");
//        }
//        System.out.println();

        // 문제풀이
        // distance^2 = (fx-bx)^2 + (fy-by)^2
        // i : John의 현재 단계
        // j : Bessie의 현재 단계
        // dp[i][j] : 특정 단계에서의 에너지 소비량의 최솟값, O(N*M)
        dp = new int[N+1][M+1];
        for(int i=0;i<=N;i++) {
        	Arrays.fill(dp[i], -1);
    	}
        
        // Recurrence
//        dp[i][j] = Math.min(dp[i-1][j] + f, dp[i][j-1] + b, dp[i-1][j-1] + f + b)
        // 1. f, b의 단계별 위치를 저장한다.
        // 2. 단계별 DP값을 계산한다.
        topDown(N, M);
        
//        for(int i=0;i<=N;i++) {
//        	System.out.println(Arrays.toString(dp[i]));
//        }
        
        sb.append(dp[N][M] - dp[0][0]);
                
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
    
    static int N;
    static int M;
    static int fx, fy;
    static int bx, by;
    static char[] fStep;
    static char[] bStep;
    static List<Node> fPositions;
    static List<Node> bPositions;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static int[][] dp;
    
    static int topDown(int i, int j) {
//    	System.out.println("(" + i + ", " + j + ")");
    	
    	if(dp[i][j] != -1) { // memoization
    		return dp[i][j]; 
    	}
    	
    	if(i==0 && j==0) {
    		return dp[0][0] = getDistance(fPositions.get(0), bPositions.get(0)); // 초기값
            
    	}
    	
    	if(i==0) {
//    		return dp[i][j] = dp[i][j-1] + getDistance(fPositions.get(0), bPositions.get(j));
    		return dp[i][j] = topDown(i, j-1) + getDistance(fPositions.get(0), bPositions.get(j));
    	}
    	if(j==0) {
//    		return dp[i][j] = dp[i-1][j] + getDistance(fPositions.get(i), bPositions.get(0));
    		return dp[i][j] = topDown(i-1, j) + getDistance(fPositions.get(i), bPositions.get(0));
    	}
    	
    	int a = topDown(i-1, j) + getDistance(fPositions.get(i), bPositions.get(j));
    	int b = topDown(i, j-1) + getDistance(fPositions.get(i), bPositions.get(j));
    	int c = topDown(i-1, j-1) + getDistance(fPositions.get(i), bPositions.get(j));
    	
    	return dp[i][j] = Math.min(a, Math.min(b, c));
    }
    
    static int getDistance(Node f, Node b) {
    	return (int) (Math.pow(f.x-b.x, 2) + Math.pow(f.y-b.y, 2)); 
    }
    
    static class Node {
    	int x;
    	int y;
    	
    	public Node(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    }
}
