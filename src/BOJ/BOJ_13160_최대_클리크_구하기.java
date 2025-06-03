package BOJ;
import java.util.*;
import java.io.*;

public class BOJ_13160_최대_클리크_구하기 {
    /*
        BOJ 13160. 최대 클리크 구하기
        ----------------------------
        
        [문제 설명]
        클리크
        - 완전 그래프인 부분 그래프
        - 정점으로 이루어진 집합 중, 모든 두 정점 사에 간선이 있는 집합

        최대 클리크
        - 클리크 중 크기가 가장 큰 집합
    
        NP-hard
        - 일반적인 그래프에서 최대 클리크를 구하는 문제풀이

        구간
        - N개
        - [S, E]

        겹치는 구간
        - 두 구간이 한 점 이상을 공유

        구간 그래프
        - N개의 정점
        - i번과 j번 구간이 겹칠 때, i번 정점과 j번 정점 사이에 간선이 존재하는 그래프 => 뭔소리?
        
        [입력]
        N : 구간의 수
        (S, E) : 구간
        
        [출력]
        최대 클리크의 크기 S
        최대 클리크 출력 정점 번호들 (출력 순서 상관 없음)
        
        [제한사항]
        1 <= N <= 300,000
        1 <= S[i] < E[i] <= 10^9
        
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); 
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        interval = new int[N][2];
        sweep = new ArrayList<>();

        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            
            // Sweeping을 위한 시작/끝 점 저장
            sweep.add(new int[] {S, 1}); 
            sweep.add(new int[] {E, -1});

            // 원래 구간 정보 저장
            interval[i][0] = S;
            interval[i][1] = E;
        }
        
        // 문제풀이
        /*
            겹치는 구간 구하기 : Sweeping
            - 시작점을 1, 끝점을 -1
            - 전체를 확인하면서 cnt가 max로 갱신되는 지점의 position을 저장
            - 각 구간이 position을 포함하는지 확인
			
           시간복잡도
           - 정렬 : O(nlogn)
           - sweep : O(n)                    
        */

        // sweep을 위한 정렬
        sweep.sort((o1, o2) -> {
        	if(o1[0] == o2[0]) {
        		return o2[1] - o1[1];
        	}
        	
        	return o1[0] - o2[0];
        });
        
        
        // sweep
        int cnt = 0;
        int max = 0;
        int position = 0;
        for(int[] c : sweep) {
        	cnt += c[1]; // 시작점이면 +1, 끝점이면 -1
        	
        	if(cnt > max) {
        		max = cnt;
        		position = c[0]; // 가장 겹치는 구간이 많은 좌표의 위치
        	}
        }
        
        sb.append(max).append("\n");
        for(int i=0;i<N;i++) {
        	// 구간이 최대 겹침 지점 position을 포함하는지 확인
        	if(interval[i][0] <= position && interval[i][1] >= position) {
        		sb.append(i+1).append(" ");
        	}
        }
      
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static int N;
    static int[][] interval;
    static List<int[]> sweep;
}