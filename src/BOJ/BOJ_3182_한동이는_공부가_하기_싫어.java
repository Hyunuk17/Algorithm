import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 3182. 한동이는 공부가 하기 싫어!
     * ---------------------------------
     * 
     * [문제 설명]
     * 인맥 쌓기
     * - 선배들 중 한명에게 질문
     * - 선배의 인맥을 타고 이어 질문하기
     * 
     * [입력]
     * N : 선배들의 수
     * destination[] : 선배가 물어봐줄 다른 사람의 번호
 	 *  
     * [출력]
     * 가장 많이 이어지는 인맥 네트워크를 만드는 선배의 번호
	 *
     * [제한사항]
     * 2 <= N <= 1,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        destination = new int[N];

        for(int i=0;i<N;i++) {
            destination[i] = Integer.parseInt(br.readLine()) - 1;
        }

        // 문제풀이
        /*
            BFS
            - DFS가 더 어울림
        */

        for(int i=0;i<N;i++) {
            BFS(i);
        }
        
        sb.append(result + 1);

        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }

    static void BFS(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N];
        queue.add(start);
        visited[start] = true;
        int depth = 1;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            int next = destination[cur];
            if(visited[next] == true) {
                continue;
            }

            queue.add(destination[cur]);
            depth++;
            visited[next] = true;
        }

        if(max < depth) {
            result = start;
            max = depth;
        }
    }

    static int N;
    static int[] destination;
    static int result;
    static int max;
}