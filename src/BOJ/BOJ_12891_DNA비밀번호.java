package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ_12891_DNA비밀번호 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 12891. DNA 비밀번호 --------------------
		 * 
		 * DNA 문자열 : 'A', 'C', 'G', 'T' 만으로 만들어진 문자열 임의의 DNA 문자열을 만들고, 만들어진 DNA 문자열의
		 * 부분문자열을 비밀번호로 사용
		 * 
		 * 부분문자열에서 등장하는 문자의 개수가 특정 개수 이상이여야 비밀번호로 사용할 수 있음
		 * 
		 * 임의로 만든 문자열 길이 : S 부분문자열 길이 : P 1 <= P <= S <= 1,000,000
		 * 
		 * A, C, G, T의 최소 개수 < S A+C+G+T <= S
		 * 
		 * 만들 수 있는 비밀번호의 수 출력
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());
		str = br.readLine();

		st = new StringTokenizer(br.readLine());
		// 각 DNA 문자의 최소 개수 조건 저장
		rule.put('A', Integer.parseInt(st.nextToken()));
		rule.put('C', Integer.parseInt(st.nextToken()));
		rule.put('G', Integer.parseInt(st.nextToken()));
		rule.put('T', Integer.parseInt(st.nextToken()));

		// 슬라이딩 윈도우를 위한 첫 부분문자열 세팅
		for (int i = 0; i < P; i++) {
			// P길이의 문자열 생성
			// 부분문자열에 포함된 문자 개수 세기
			password.put(str.charAt(i), password.getOrDefault(str.charAt(i), 0) + 1);
		}

		// 조건을 통과한다면 ++
		if (checkPassword()) {
			result++;
		}

		// 부분문자열을 오른쪽으로 한칸씩 이동
		// 끝점 기준
		for (int i = P; i < S; i++) {
			// 끝에 새 문자 추가
			password.put(str.charAt(i), password.getOrDefault(str.charAt(i), 0) + 1);

			// 시작 문자 제거
			int start = i - P;
			password.put(str.charAt(start), password.get(str.charAt(start)) - 1);
			
			// 새로 생성된 부분문자열에 대한 체크
			if(checkPassword()) {
				result++;
			}
		}

		
		// 출력
		bw.write(result + "");
		bw.flush();

		bw.close();
		br.close();

	}

	static int S;
	static int P;
	static String str;
	static Map<Character, Integer> password = new HashMap<>();
	static Map<Character, Integer> rule = new HashMap<>();
	static int result = 0;

	// 확인 조건
	// 부분문자열은 각 문자의 최소 개수 제한을 만족해야 함
	static boolean checkPassword() {
		if(password.getOrDefault('A', 0) < rule.get('A')) {
			return false;
		}
		if(password.getOrDefault('C', 0) < rule.get('C')) {
			return false;
		}
		if(password.getOrDefault('G', 0) < rule.get('G')) {
			return false;
		}
		if(password.getOrDefault('T', 0) < rule.get('T')) {
			return false;
		}
		
		return true;
	}
}
