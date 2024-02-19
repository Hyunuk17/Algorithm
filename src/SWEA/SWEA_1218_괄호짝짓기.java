package SWEA;

import java.util.*;
import java.io.*;

public class SWEA_1218_괄호짝짓기 {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 1218. 괄호 짝짓기 ------------------
		 * 
		 * 4종류의 괄호문자 '()', '[]', '{}', '<>'
		 * 
		 * 문자열에 사용된 괄호의 짝이 모두 맞는지 판별
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

//		T = Integer.parseInt(br.readLine());

		// Test Case
		for (int t = 1; t <= 10; t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			stack = new ArrayDeque<>();
			ans = 1;
			str = br.readLine();

			// 문제풀이
			// 여는 괄호는 push
			// 닫는 괄호는 현재 맨 위 괄호와 맞으면 remove
			// 총 문자열 탐색 후에는 stack이 비어 있어야 함
			for (int i = 0; i < N; i++) { 
				char c = str.charAt(i);
				// 여는 괄호라면
				if (c == '(' || c == '[' || c == '{' || c == '<') {
					stack.addLast( c);
					continue;
				}

				// 닫는 괄호라면
				// 닫는 괄호인데 여는 괄호가 스택에 없으면 실패
				if (stack.isEmpty()) {
					ans = 0;
					break;
				}

				// 닫는 괄호가 현재  맨 위 괄호와 일치한다면
				if ((stack.peekLast() == '(' && c == ')') || (stack.peekLast() == '[' && c == ']') || (stack.peekLast() == '{' && c == '}') || (stack.peekLast() == '<' && c == '>')) {
					stack.removeLast();
					continue;
				}
				
				break;
				
			}

			// 여는 괄호가 남아있다면
			if (!stack.isEmpty()) {
				ans = 0;
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
	static String str;
	static int ans;
	static Deque<Character> stack;
}
