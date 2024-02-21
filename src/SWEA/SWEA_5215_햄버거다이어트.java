package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_5215_햄버거다이어트 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 5215. 햄버거 다이어트 ---------------------
		 * 
		 * 햄버거의 맛은 최대한 유지하면서, 정해진 칼로리를 넘지 않도록
		 * 
		 * 준비해놓은 재료로 고객이 조합을 선택 햄버거의 재료에 대한 본인의 점수
		 * 
		 * 가장 좋아하면서(max), 정해진 칼로리 이하의 햄버거 조합
		 * 
		 * 같은 재료 여러번 안됨
		 * 
		 * 1 <= N <= 20 : 재료 수 1 <= L <= 10000 : 제한 칼로리 1 <= T_i <= 1000 : 재료 맛 1 <= K_i
		 * <= 1000 : 재료 칼로리
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Test Case
		tc = Integer.parseInt(br.readLine());
		for (int t = 1; t <= tc; t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			T = new int[N];
			K = new int[N];
			result = new int[N];
			max = Integer.MIN_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				T[i] = Integer.parseInt(st.nextToken());
				K[i] = Integer.parseInt(st.nextToken());
			}

			// 문제풀이
			combination(0);

			// 출력
			sb.append("#").append(t).append(" ").append(max).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}

		bw.close();
		br.close();
	}

	static int tc;
	static int N;
	static int L;
	static int[] result;
	static int[] T;
	static int[] K;
	static int max;

	static void combination(int depth) {
		if (depth == N) { 
			int kal = 0; // 현재 조합의 칼로리
			int favorite = 0; // 현재 조합의 선호도
			for (int i = 0; i < N; i++) {
				if (result[i] == 0) { // 조합에 포함되지 않는 원소이면 continue;
					continue;
				}

				kal += K[i];
				favorite += T[i];
			}
			
			if(kal <= L) { // 칼로리가 다이어트용 한계 칼로리 이하일 때
				max = Math.max(max, favorite); // 최고 선호도 구하기
			}
			return;
		}

		result[depth] = 1; // 현재 재료를 선택한 경우
		combination(depth + 1);

		result[depth] = 0; // 현재 재료를 선택하지 않은 경우
		combination(depth + 1);

	}
}
