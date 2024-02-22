package SWEA;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class SWEA_1238_Contact {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 1238. Contact
		 * ------------------
		 * 
		 * 비상연락망(방향 그래프)와 당번(시작 정점)이 주어짐
		 * 가장 나중에 연락을 받게 되는 사람 중 번호가 가장 큰 사람 구하기
		 * 
		 * 1 <= N <= 100
		 * tc = 10
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T= 10;
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st  = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			// 그래프 생성
			graph = new ArrayList[101];
			for(int i=1;i<=100;i++) {
				graph[i] = new ArrayList<>();
			}
			
			// 그래프 간선 입력
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<N/2;i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				graph[from].add(to);
			}
			
			
			
			
			// 문제풀이
			ans = 0;
			queue = new ArrayDeque<>(); // 방문 노드를 담을 Queue
			visited = new boolean[101]; // 방문 노드 중복처리 배열
			queue.add(start); // 시작 정점 세팅
			visited[start] = true; // 방문처리
			
			while(!queue.isEmpty()) { // 더이상 탐색 불가능할 때까지 반복
				int size = queue.size(); // 현재 Time에 들어있는 노드의 수 
				
				int max = 0; // 현재 Time에서의 가장 큰 방문 노드의 숫자
				for(int i=0;i<size;i++) { // 현재 Time에서의 모든 노드를 반복
					int now = queue.poll();
					
					for(int j=0;j<graph[now].size();j++) { // 인접한 모든 노드를 동시에 방문
						int destination = graph[now].get(j); // 목적지
						
						if(visited[destination] == true) { // 이미 방분한 노드라면 건너뛰기
							continue;
						}
						
						visited[destination] = true; // 방문처리
						queue.add(destination); // Queue에 추가
						max = Math.max(max, destination); // 가장 큰 숫자 구하기
					}
				}
				if(max != 0) { // max == 0이면, 마지막 노드였다는 뜻
					ans = max; // max != 0일 때만, ans 갱신
				}
			}
			
			
			// 출력
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
		
	}

	static int T;
	static int N;
	static int start;
	static List<Integer>[] graph;
	static Queue<Integer> queue;
	static boolean[] visited;
	static int ans;
}
