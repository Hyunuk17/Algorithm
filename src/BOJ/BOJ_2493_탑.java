package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

public class BOJ_2493_탑 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 2493. 탑
		 * -----------
		 * 
		 * 레이저를 이용한 새로운 비밀 통신 시스템 개발
		 * 
		 * 일직선 위에 N개의 높이가 서로 다른 탑을 왼->오 방향으로 세우기
		 * 각 탑의 꼭대기에 레이저 송신기 설치
		 * 
		 * 수평 직선의 왼쪽 방향으로 레이저 발사
		 * 가장 먼저 만나는 탑이 수신
		 * 
		 * 각각의 탑에서 발사한 레이저 신호를 어느 탑이 수산하는지 출력
		 * 
		 * 1 <= N <= 500,000
		 * 1 <= h <= 100,000,000
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		h = new int[N];
		result = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			h[i] = Integer.parseInt(st.nextToken());
		}
		
		// 문제풀이
		for(int i=0;i<N;i++) {
			// stack에 요소가 있다면 반복
			while(!stack.isEmpty()) {
				// 현재 스택의 peek값의 높이가 i의 높이보다 낮다면 pop
				// i의 높이보다 높은 탑을 찾는 중
				if(h[stack.peek()] < h[i]) {
					stack.pop();
				}
				// 현재 스택의 peek값의 높이가 i의 높이보다 높다면,
				// 탑이 레이저를 수신
				else {
					result[i] = stack.peek()+1; // 현재 탑의 레이저를 수신한 탑의 번호 적기
					break;
				}
			}
			// 현재 탑을 stack에 push
			stack.push(i);
		}
		
		// 출력		
		for(int i : result) {
			sb.append(result[i]).append(" ");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[] h;
	static int[] result;
	static Deque<Integer> stack = new ArrayDeque<>();

}
