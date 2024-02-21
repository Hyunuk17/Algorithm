package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759_암호만들기 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1759. 암호만들기
		 * -----------------
		 * 
		 * 암호 : 서로다른 L개 알파벳 소문자
		 * 최소 1개의 모음(a, e, ,i ,o, u)과 2개의 자음으로 구성
		 * 암호는 정렬되어있음
		 * 
		 * C개 문자가 주어짐
		 * 가능성이 있는 암호 모두 출력
		 *
		 * 3 <= L, C <= 15
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[C]; // 문자를 저장할 배열
		result = new char[L]; // 암호를 저장할 배열
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) {
			arr[i] = st.nextToken().charAt(0);
		}
		
		// 문제풀이
		Arrays.sort(arr); // 정렬
		NP(0, 0, 0, 0); // 다음 순열 구하기
		
		// 출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}

	static int L;
	static int C;
	static char[] arr;
	static char[] result;
	static StringBuilder sb;
	
	// 암호 구하기 : 다음 순열
	static void NP(int depth, int start, int vowel, int consonant) {
		if(depth == L) {
			// 모음 1개 이상, 자음 2개 이상 조건을 만족하지 않은 경우
			if(vowel < 1 || consonant < 2) {
				return; // 그대로 종료
			}
			
			// 조건을 만족했다면 출력
			for(char c : result) {
				sb.append(c);
			}
			sb.append("\n");
			return;
		}
		
		// 문자 선택 : 정렬된 순서에서 현재 알파벳보다 큰 알파벳만 고를 수 있음
		for(int i=start;i<C;i++) {
			result[depth] = arr[i]; // 문자 선택
			int v; // 모음
			int c; // 자음
			// 선택한 문자가 모음이면
			if(arr[i] == 'a' || arr[i] == 'e' || arr[i] == 'i' || arr[i] == 'o' || arr[i] == 'u') {
				v = vowel + 1; // 모음 더하기 +1
				c = consonant;
			}
			// 선택한 문자가 자음이면
			else {
				v = vowel;
				c = consonant + 1; // 자음 더하기 +1
			}
			NP(depth+1, i+1, v, c); // 재귀
		}
	}
}
