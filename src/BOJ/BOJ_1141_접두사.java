package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1141_접두사 {
	/*
	 * BOJ 1141. 접두사 
	 * ---------------
	 * 
	 * [문제 설명] 
	 * 접두사x 집합 
	 * - 어떤 한 단어가, 다른 단어의 접두어가 되지 않는 집합
	 * 
	 * 단어 
	 * - 알파벳 소문자로 구성 
	 * - 중복된 단어가 있을 수 있음
	 * 
	 * [입력] 
	 * N : 단어의 개수 
	 * word : 단어
	 * 
	 * [출력] 
	 * 단어 N개로 이루어진 집합에서, 접두사X 집합의 최대 크기
	 *
	 * [제한사항] 
	 * 1 <= N <= 50 
	 * 1 <= word[i].length <= 50
	 * 
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		word = new String[N];
		for (int i = 0; i < N; i++) {
			word[i] = br.readLine();
		}

		// 문제풀이
		/*
		 * 1. 부분집합 만들기 
		 * - 완전탐색 : 2^50 => TLE 
		 * - 정렬(그리디)
		 * 
		 * 2. 부분집합이 접두사X 집합인지 확인하기 
		 * - Set
		 * 
		 * 3. 최대 크기인 접두사X 집합의 크기 구하기
		 */

		// 부분집합 만들기
		// 1. 역순 정렬
		// 2. 부분집합의 원소를 순회
		// 3. 현재 문자가 접두어로 속한 문자가 있는지 확인
		set = new HashSet<>();
		Arrays.sort(word, (o1, o2) -> {
			return o2.compareTo(o1);
		});

		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (!set.contains(word[i])) {
				addWord(i);
				cnt++;
			}
		}

		sb.append(cnt == 0 ? 1 : cnt);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static String[] word;
	static Set<String> set;

	static void addWord(int idx) {
		String str = "";
		for (int i = 0; i < word[idx].length(); i++) {
			str += word[idx].charAt(i);
			set.add(str);
		}
	}
}