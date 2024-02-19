package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2023_신기한소수찾기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 2023. 신기한 소수
		 * -----------------
		 * 
		 * 신기한 소수 7331: 733, 73, 7이 전부 소수
		 *
		 * N자리의 숫자 중에서 신기한 소수를 모두 찾기
		 * 1 <= N <= 8
		 * 
		 * 신기한 소수 오름차순 정렬, 한줄에 하나씩 출력
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();
		
		// 입력
		N = Integer.parseInt(br.readLine());
		
		// 문제풀이
		re(0, 0);
		
		// 출력
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static StringBuilder sb;
	
	
	// 재귀
	static void re(int depth, int now) {
		// 기저조건
		if(depth == N) {
			sb.append(now).append("\n");
			return;
		}
		
		// 숫자 선택
		for(int i=1;i<10;i++) {
			// 다음에 넣을 숫자
			int next = now*10 + i;
			// 소수 판별
			if(isPrime(next)) {
				// 다음 재귀 진행
				re(depth+1, next);
			}
		}
	}
	
	// 소수 판별 메서드
	static boolean isPrime(int num) {
		// 1인 경우 예외
		if(num == 1) {
			return false;
		}
		
		// num의 제곱근까지 탐색, 소수가 아니면 return false
		for(int i=2;i*i<=num;i++) {
			if(num % i == 0) {
				return false;
			}
		}
		
		// 소수면 return true
		return true;
	}
}
