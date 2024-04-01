package src.SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_5607_조합 {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 5607. 조합
		 * --------------
		 * 
		 * 페르마 소정리를 이용한 문제풀이
		 * 
		 * 자연수 N, R
		 * N Combination R의 값을 1234567891로 나눈 나머지 출력
		 * 
		 * 1 <= T <= 20
		 * 1 <= N <= 100,000
		 * 1 <= R <= N
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			
			// 문제풀이
			// x(밑), y(지수), p(소수)
			// nCr % p
			// 연산 과정의 값이 크기 때문에 % 연산 분배법칙을 적용
			// n! / (r! * (n-r)!) : 나누기 연산에 모듈러 분배법칙을 적용할 수 없음
			// 페르마의 소정리를 통해 곱셈 형식으로 변환 후 연산
			// ((A % p) * (B % p)) % p
			// n! * (r! * (n-r)!)^{p-2} (mod p)
			// n!/r!  * (n-r)!^ {p-2}  (mod p)
			
			long min = Math.min(R, N-R); // 작은 값으로 최적화 
			long a = 1;
			long b = 1;
			for(int i=0;i<min;i++) {
				a = a * (N-i) % p; // n! / r!
				b = b * (min-i) % p; // (n-r)!
			}
			
			// 페르마의 소정리를 이용하여 나누기 연산을 곱셈으로 변환
			ans = (a%p * power(b, p-2) %p) %p;
			
			// 출력
			sb.append("#").append(t).append(" ").append(ans).append("\n");
			bw.write(sb.toString());
			bw.flush();
			
		}
		bw.close();
		br.close();
	}
	
	static int T;
	static int N;
	static int R;
	static int p = 1234567891;
	static long ans;

	static long power(long x, int y) {
		long result = 1L;
		x = x % p;
		while(y > 0) {
			if(y % 2 == 1) { // 지수가 홀수인 경우
				result = (result * x) % p; // x를 분리하여 reust에 곱셈
			}
			
			// 지수가 짝수인 경우
			y = y/2;
			x = (x * x) % p; // 제곱수로 mod 연산 
		}
		
		return result;
	}
}
