package SWEA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SWEA_2383_점심식사시간 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 모의 역량 테스트. 점심 식사시간
		 * --------------------------
		 * 
		 * NxN 방
		 * 점심을 먹기 위해 아래층으로 내려가야 함
		 * 빨리 먹기 위해 최대한 빨리
		 * 
		 * 사람(P), 계단 입구(S) 2개
		 * 
		 * 이동 완료 시간 : 모든 사람들이 계단을 내려가 이동을 완료한 시간
		 * 아래층으로 이동하는 시간 = 계단 입구까지 이동 시간 + 계단 내려가는 시간
		 * - 계단 입구까지 이동 시간 : |PR-SR| + |PC-SC|, 맨해튼 거리
		 * - 계단을 내려가는 시간 : K, 동시에 3병까지 가능
		 *
		 * 모든 사람이 이동이 완료하는 최소 시간 구하기
		 * 
		 * 최대 50개 모두 통과하는데 3초 -> ?어떤 의미
		 * 4 <= N <= 10
		 * 1 <= 사람 수 <= 10
		 * 계단 입구 2개
		 * 2 <= K <= 10
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test Case
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			
			people = new ArrayList<>();
			stairs = new ArrayList<>();
			
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
					if(board[i][j] == 0) {
						continue;
					}
					else if(board[i][j] == 1) {
						people.add(new Node(i, j, 0));
					}
					else {
						stairs.add(new Node(i, j, board[i][j])); // K
						
					}
				}
			}
			
			stair = new ArrayList[2]; // 계단을 목표로하는 사람 정보
			stair[0] = new ArrayList<>(); // 계단 1
			stair[1] = new ArrayList<>(); // 계단 2
			min = Integer.MAX_VALUE; 
			
			// 문제풀이
			// N <= 10
			// 2^10*N <= 10^8 : 완전탐색 가능
			// 사람들이 2개의 계단 중 어떤 것을 선택할지 구하기
			subset(0); 
			
			
			// 출력
			sb.append("#").append(t).append(" ").append(min+1).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}
	
	static int T;
	static int N;
	static int[][] board;
	static List<Node> people;
	static List<Node> stairs;
	static List<Node>[] stair;
	static int min;
	
	// 계단을 선택하는 경우의 수 구하기
	static void subset(int depth) {
		if(depth == people.size()) {
			// 구한 결과로 시간 계산
			min = Math.min(min, getTime()); // 거리 계산
			return;
		}
		
		Node now = people.get(depth); // 현재 사람 정보
		
		// 계단 1선택
		people.get(depth).d = Math.abs(now.r - stairs.get(0).r) + Math.abs(now.c - stairs.get(0).c);
		stair[0].add(people.get(depth));
		subset(depth+1); // 다음 재귀
		stair[0].remove(stair[0].size()-1);
		
		// 계단 2선택
		people.get(depth).d = Math.abs(now.r - stairs.get(1).r) + Math.abs(now.c - stairs.get(1).c);
		stair[1].add(people.get(depth));
		subset(depth+1); // 다음 재귀
		stair[1].remove(stair[1].size()-1);
	}
	
	// 시간 구하는 연산
	static int getTime() {
		int max = 0;
		for(int i=0;i<2;i++) {
			PriorityQueue<Node> pq = new PriorityQueue<>(); // 우선순위 큐, 거리 가까운 순
			int[] time = new int[100]; // 시간을 나타내는 배열
			
			// 현재 계단의 원소를 pq에 넣기
			for(int j=0;j<stair[i].size();j++) {
				pq.add(stair[i].get(j));
			}
			
			int end = 0; // 끝나는 시간
			while(!pq.isEmpty()) {
				Node now = pq.poll();
				int start = now.d; // 계단까지의 거리
				end = start + stairs.get(i).d; // 계단까지 거리 + 계단 길이(K)
				
				for(int j=start;j<end;j++) { // 걸리는 시간
					if(time[j] == 3) { // 현재 시간에 3명이 계단에 존재하면
						end++; // 1분 연장
						continue;
					}
					time[j]++; // 현재 시간에 계단에 존재하는 인원 +1
				}
				
				// 끝나는 시간 갱신
				if(max < end) {
					max = end;
				}
			}
		}
		
		return max;
	}
	
	// 위치 정보
	static class Node implements Comparable<Node>{
		int r;
		int c;
		int d;
		
		public Node(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}

		@Override
		public int compareTo(Node o) {
			return this.d - o.d;
		}
	}
}
