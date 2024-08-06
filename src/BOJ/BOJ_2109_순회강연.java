package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class BOJ_2109_순회강연 {
    /*
     * BOJ 2109. 순회강연
     * ---------------
     * 
     * [문제설몀]
     * 한 저명한 학자에게 N개 대학에서 강연 요청
     * - d일 안에 와서 강연을 해주면 p 강연료 지불
     * - 가장 돈을 맣이 버는 순회강연
     * - 하루에 최대 한 곳에서만 강연 가능
     * 
     * [입력]
     * 0 <= N <= 10,000
     * 1 <= P <= 10,000
     * 1 <= D <= 10,000
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
        
        N = Integer.parseInt(br.readLine());

        lecture = new int[N][2];
        for(int i=0;i<N;i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int p = Integer.parseInt(st.nextToken());
        	int d = Integer.parseInt(st.nextToken());
        	lecture[i][0] = p;
        	lecture[i][1] = d;
        }
  
        // 문제풀이
        // greedy
        // 강연은 며칠에 걸쳐서 하는 게 아니라 단 하루만 진행
        // 어떤 강연을 먼저 선택할 것인지를 정하는 문제
        // 정렬을 통해 비용이 큰 순서대로 정렬
        
        Arrays.sort(lecture, (o1, o2) -> {
        	if(o1[0] == o2[0]) {
        		return o2[1] - o1[1];
        	}
        	
        	return o2[0] - o1[0];
        });

        
        day = new boolean[10001];
        for(int i=0;i<N;i++) {
        	for(int j=lecture[i][1];j>=1;j--) {
        		if(day[j] == false) {
        			day[j] = true;
        			result += lecture[i][0];
        			break;
        		}
        	}
        }
        
        sb.append(result);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
    
    static int N;
    static int[][] lecture;
    static boolean[] day;
    static int result;
}
