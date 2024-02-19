package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2839_설탕배달 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 2839. 설탕 배달 ----------------
		 * 
		 * 사탕가게에 정확하게 N 킬로그램 배달 3kg, 5kg
		 * 
		 * 최대한 적은 설탕 봉지 수 구하기
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());


		// 문제풀이
		// 5와 3은 배수가 아니다
		int min = 0;
		while (true) {
			// 5로 나누어 떨어지는지 검사
			if (N % 5 == 0) {
				min += N / 5; // 5로  나눈 몫 더하기
				break; // 최적해 구하기 성공
			}
			
			if(N < 0) { // 최적해를 구하지 못한 경우
				min = -1;
				break;
			}
			
			N -= 3; // 3개수를 하나씩 늘리기
			min++;
		}

		
		// 출력
		bw.write(min + "");
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
}
