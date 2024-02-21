package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_15686_치킨배달 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 15686. 치킨 배달
		 * -----------------
		 * 
		 * NxN 도시
		 * 빈 칸(0), 집(1), 치킨집(2)
		 * (r,c)
		 * 
		 * 치킨 거리 : 집과 가장 가까운 치킨집 사이의 거리, 최소거리(BFS)
		 * 도시의 치킨 거리 : 모든 치킨 거리의 합
		 * 
		 * (r1, c1), (r2, c2) 사이의 거리 : |r1-r2| + |c1-c2|
		 * 
		 * 치킨집 중 M개 말고 나머지 폐업
		 * 폐업 후 도시의 치킨 거리가 최소가 되도록
		 * 
		 * 2 <= N <= 50 
		 * 1 <= M <= 133
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		selected = new Node[M];
		homes = new ArrayList<>();
		chikens = new ArrayList<>();
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if(board[i][j] == 1) {
					homes.add(new Node(i, j)); // 집 위치 저장
				}
				else if(board[i][j] == 2) {					
					chikens.add(new Node(i, j)); // 치킨집 위치 저장
				}
			}
		}
		
		// 문제풀이
		// 완전 탐색으로 운영할 치킵집을 M개 선택
		re(0, 0);
		
		
		// 출력
		bw.write(min+"");
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int M;
	static int[][] board;
	static List<Node> homes;
	static List<Node> chikens;
	static Node[] selected;
	static Queue<Node> queue;
	static boolean[][] visited;
	static int min = Integer.MAX_VALUE;
	
	static void re(int depth, int start) {
		if(depth == M) {
			// 선택한 치킨집 경우의 수에서 최소의 도시의 치킨 거리 구하기
			min = Math.min(min, getDistance()); 
			return;
		}
		
		// 치킨집 M개 고르기
		for(int i=start;i<chikens.size();i++) {
			Node chiken = chikens.get(i);
			selected[depth] = chiken;
			re(depth+1, i+1);
		}
	}
	
	// 거리 구하기
	static int getDistance() {
		int sum = 0;
		for(int i=0;i<homes.size();i++) { // 집
			int distance = Integer.MAX_VALUE;
			for(int j=0;j<M;j++) { // 치킨집
				int d = Math.abs(homes.get(i).r - selected[j].r) + Math.abs(homes.get(i).c - selected[j].c);
				distance = Math.min(distance, d); // 치킨 거리가 최소가 되도록
			}
			sum += distance;
		}
		
		return sum; // 도시의 치킨 거리 반환
	}
	
	// 위치 저장
	static class Node {
		int r;
		int c;
		
		public Node(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
