package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1225_암호생성기 {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 1225. 암호생성기
		 * ------------------
		 * 
		 * 8자리 암호 생성
		 * 
		 * 
		 * 8개 숫자 입력 받음
		 * 
		 * 한 사이클
		 * 첫 번째 숫자 1 감소한 뒤, 맨 뒤
		 * 두 번쨰 숫자 2 감소한 뒤, 맨 뒤
		 * 세 번재 숫자 3 감소 ...
		 * 
		 * 숫자가 0보다 작아지면 0 유지, 프로그램 종료
		 * 이 때의 8자리 숫자 값이 암호
		 * 
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int t = 10;
		while(t-- > 0) {
			// 입력
			sb = new StringBuilder();
			stack = new ArrayDeque<>();
			N = Integer.parseInt(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			// 초기 8개 숫자 입력
			for(int i=0;i<8;i++) {
				stack.addLast(Integer.parseInt(st.nextToken()));
			}
			
			// Cycle 진행
			while(true) {
				if(cycle() == false) { 
					break;
				}
			}
			
			// 출력
			sb.append("#").append(N).append(" ");
			for(int i : stack) {
				sb.append(i).append(" ");
			}
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
	}
	static int N;
	static Deque<Integer> stack;
	static StringBuilder sb;
	
	// Cycle
	static boolean cycle() {
		// 숫자 5까지 감소
		for(int i=1;i<=5;i++) {
			int num = stack.removeFirst() - i; // 맨 앞 숫자 꺼내서 감소
			if(num < 0) { // 0보다 작아지면, 0으로 
				num = 0;
			}
			stack.addLast(num); // 맨 뒤로 보내기
			if(num <= 0) { // 프로그램 종료 조건
				return false;
			}
		}
		
		return true;
	}
}
