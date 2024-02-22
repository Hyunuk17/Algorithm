package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_17471_게리맨더링 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 17471. 게리맨더링
		 * ------------------
		 * 
		 * Gerrymandering:
		 * - 선거구를 특정 정당이나 후보자에게 유리하게 인위적으로 정하는 것
		 * - 메사추세츠 주지사 게리(Gerry)가 인위적으로 획정한 선거구의 모양이 Salamander와 비슷함에서 유래
		 * - 선거구 획정(명확히 구별하여 정하는 것, 결정)
		 * 
		 * N개의 구역
		 * 한 구역은 인접한 구역이 없을 수도 있음

		 * 2개의 선거구로 나눌 것
		 * 적어도 하나 이상의 구역을 포함
		 * 한 선거구의 모든 구역은 연결
		 * 
		 * 구역(v), 인접(e)
		 * 두 선거구의 인구차를 최소로
		 * 
		 * 2 <= N <= 10
		 * 1 <= 구역의 인구 수 <= 100
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		// 구역 수
		N = Integer.parseInt(br.readLine());
		
		// 인구 수
		populations = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			populations[i] = Integer.parseInt(st.nextToken());
			total += populations[i];
		}
		
		
		graph = new ArrayList[N];
		for(int i=0;i<N;i++) {
			graph[i] = new ArrayList<>();
		}
		
		// 인접한 구역의 정보
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int cnt = Integer.parseInt(st.nextToken()); // 인접한 구역의 수
			for(int j=0;j<cnt;j++) { 
				graph[i].add(Integer.parseInt(st.nextToken()) -1); // 인접한 구역
			}
		}
		
				
		
		// 문제풀이
		// NP로 구해진 집합에 수행해서 Connected Component가 2인 경우에만 가중치 합 계산
		permutation = new boolean[N];
		NP(0);
				
		// 출력
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	

	static int N;
	static int[] populations;
	static int total;
	static List<Integer>[] graph;
	static boolean[] permutation;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	
	// 경우의 수 구하기
	static void NP(int depth) {
		if(depth == N) {
			// true인 요소들이 모두 1개의 같은 컴포넌트인지 검사
			int componentCnt = 0;
			visited = new boolean[N];
			for(int i=0;i<N;i++) {
				if(permutation[i] == false) {
					continue;
				}
				if(visited[i] == true) {
					continue;
				}
				
				visited[i] = true;
				componentCnt++;
				ConnectedComponent(i, true);				
			}
			
			// false인 요소들이 모두 1개의 같은 컴포넌트인지 검사
			int componentCnt2 = 0;
			visited = new boolean[N];
			for(int i=0;i<N;i++) {
				if(permutation[i] == true) {
					continue;
				}
				if(visited[i] == true) {
					continue;
				}
				
				visited[i] = true;
				componentCnt2++;
				ConnectedComponent(i, false);
			}

			// 각각의 선택된 2 집합이 같은 컴포넌트이면
			if(componentCnt == 1 && componentCnt2 == 1) {
				int sum = getPopulation(); // 인구 합 구하기
				min = Math.min(min, Math.abs((total-sum) - sum)); // 인구 차의 최소 구하기
			}

			return;
		}
		
		permutation[depth] = true; // 선택
		NP(depth+1);
		permutation[depth] = false; // 선택하지 않음
		NP(depth+1);
	}
	
	// 연결 요소 확인: DFS 방식
	static void ConnectedComponent(int start, boolean option) {
		for(int i=0;i<graph[start].size();i++) {
			int destination = graph[start].get(i);
			
			// 선택 or 비선택 집합
			if(permutation[destination] == !option) {
				continue;
			}
			
			// 이미 방문한 원소
			if(visited[destination] == true) {
				continue;
			}
			
			visited[destination] = true; // 방문 탐색
			ConnectedComponent(destination, option); // 재귀
		}
	}

	// 인구 수 합 구하기
	static int getPopulation() {
		int sum = 0;
		for(int i=0;i<N;i++) {
			if(permutation[i] == true) {
				sum += populations[i]; 
			}
		}
		
		return sum;
	}
}
