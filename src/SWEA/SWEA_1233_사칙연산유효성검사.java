package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_1233_사칙연산유효성검사 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1233. 사칙연산 유효성 검사
		 * ------------------------
		 * 
		 * 사칙 연산으로 구성된 식을 이진트리로 표현
		 * +,-,*,/와 양의 정수로 구성된 이진 트리의 유효성 검사
		 * 
		 * 계산이 가느아다면 1, 불가능하다면 0
		 * 
		 * TC = 10
		 * 1 <= N <= 200
		 * 완전 이진 트리
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t=1;t<=10;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			ans = 1;
			
			// 문제풀이
			// 완전 이진 트리를 수식으로 선언하기 위한 조건
			// 리프 노드는 모두 숫자여야 한다
			// 리프 노드가 아닌 노드는 모두 연산자여야 한다
			for(int i=0;i<N;i++) {
				StringTokenizer st= new StringTokenizer(br.readLine());
				// 해당 노드 번호
				int node = Integer.parseInt(st.nextToken());
				// 현재 노드의 값
				char c = st.nextToken().charAt(0);
				// 현재 노드의 값이 연산자라면
				if(c == '+' || c == '-' || c == '*' || c == '/') {
					// 다음 노드의 번호가 존재해야만 함
					if(st.hasMoreTokens()) { // 왼쪽 노드 존재 확인
						int next1 = Integer.parseInt(st.nextToken());
						if(st.hasMoreTokens()) { // 오른쪽 노드 존재 확인
							int next2 = Integer.parseInt(st.nextToken());
						}
						else {
							ans = 0; // 오른쪽 자식 노드가 존재하지 않다면 fail
						}
					}
					else {
						ans = 0; // 왼쪽 자식 노드가 존재하지 않다면 fail
					}
				}
				// 현재 노드의 값이 숫자 일 때
				else { 
					// 노드가 리프 노드여야 한디
					if(st.hasMoreTokens()) { 
						ans =0; // 자식 노드가 존재한다면 fail
					}
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
	
	static int N;
	static int ans;
}
