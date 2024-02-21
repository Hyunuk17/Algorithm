package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2961_도영이음식 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 2961. 도영이가 만든 맛있는 음식
		 * --------------------------
		 * 
		 * 재료 N
		 * 각 재료의 신맛(S)와 쓴맛(B)
		 * 
		 * 음식의 신맛 = 재료의 신맛의 곱
		 * 음식의 쓴맛 = 재료의 신맛의 합
		 * 재료는 적어도 1개 이상 사용  
		 * 
		 * 요리의 신맛과 쓴맛의 차이를 최대한 작게 
		 * -> 부분집합의 합 문제
		 * 
		 * 1 <= N <= 10
		 * 1 <= 요리의 신맛과 쓴맛 < = 1,000,000,000
		 * 
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N][2]; // 신맛, 쓴맛
		
		for(int i=0;i<N;i++) {
			String[] str = br.readLine().split(" ");
			arr[i][0] = Integer.parseInt(str[0]);
			arr[i][1] = Integer.parseInt(str[1]);			
		}
		
		// 문제풀이: 바이너리 카운팅
		binaryCounting(0);
		
		// 출력
		bw.write(min+"");
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[][] arr;
	static int min = Integer.MAX_VALUE;
	
	// 바이너리 카운팅
	static void binaryCounting(int depth) {
		// 1~N개가 포함된 부분집합의 수
		// 재료를 최소 1개는 사용해야 함
		for(int i=1;i<(1<<N);i++) {
			int s = 1; // 신맛은 곱하기이므로 1
			int b = 0; // 쓴맛은 더하기 이므로 0
			
			// 원소의 개수 N개 탐색
			for(int j=0;j<N;j++) {
				// 현재 부분집합에 그 원소가 있으면
				if((i & (1<<j)) != 0) {
					s *= arr[j][0]; // 재료의 신맛 곱하기
					b += arr[j][1]; // 재료의 쓴맛 더하기
				}
			}
			
			// 최솟값 갱신
			min = Math.min(min, Math.abs(s-b));
		}
	}
}
