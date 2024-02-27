import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1939. 중량제한
		 * ---------------
		 * 
		 * 섬나라 다리로 연결
		 * 다리마다 중량 제한이 있음
		 * => 양방향 가중치 그래프
		 * 
		 * 한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값 구하기
		 * 
		 * 2 <= N <= 10,000
		 * 1 <= M <= 100,000
		 * 1 <= A, B <= N
		 * 중량제한 C : 1 <= C <= 1,000,000,000
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[N+1];
		for(int i=1;i<=N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, weight));
			graph[b].add(new Node(a, weight));
		}
		
		// 문제풀이
		
		// 출력
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
		
	}

	static int N;
	static int M;
	static List<Node>[] graph;
	
	static class Node {
		int v;
		int weight;
		
		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}
}
