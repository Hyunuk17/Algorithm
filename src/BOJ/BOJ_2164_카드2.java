package BOJ;

import java.io.*;
import java.util.*;

public class BOJ_2164_카드2 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 2164. 카드2
		 * --------------
		 * 
		 * N장의 카드, 1~N까지의 번호
		 * 1번 카드 맨위, N번 카드 맨 아래
		 * 
		 * 반복
		 * 제일 위 카드 바닥에 버림
		 * 제일 위 카드를 제일 아래에 있는 카드 밑으로 옮김
		 * 
		 * 제일 마지막에 남는 카드 구하기
		 * 
		 * 1 <= N <= 500,000
		 * 
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		// 스택에 수 N까지 순서대로 추가
		for(int i=1;i<=N;i++) {
			// push()는 addFirst, 
			// add(), offer()는 addLast
			stack.add(i);
		}
		
		// 문제 풀이
		// 카드가 1개 남을 때 까지 반복
		while(stack.size() != 1) {
			stack.removeFirst(); // 맨 위 카드 삭제
			int num = stack.removeFirst(); // 그 다음 카드 꺼내기
			stack.add(num); // 맨 밑에 추가
		}
		
		// 출력
		bw.write(stack.peek()+"");
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	// 스택 구현 arrayDeque
	static Deque<Integer> stack = new ArrayDeque<>();
	
}
