package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1253_좋다 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ_1253. 좋다
		 * -------------
		 * 
		 * N개의 수
		 * 좋은(GOOD) 수 : 다른 두 수 2개의 합으로 나타낼 수 있는 수
		 * 
		 * 좋은 수의 개수 찾기
		 * 수의 위치가 다르면 값이 같아도 다른 수
		 * 
		 * 1 <= N <= 2,000
		 * |A_i| <= 1,000,000,000
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		int max = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, arr[i]);
		}
		
		// 문제풀이
		// 정렬
		Arrays.sort(arr);
		// N * 2 = 4,000,00
		
		// 현재 값 arr[idx]
		// 현재 값 = 다른 2개의 수의 합
		// 최저 + 최대 
		// 크면 최대를 낮춤
		// 작으면 최저를 높힘
		// left < right 까지 
		
		for(int i=0;i<N;i++) {			
			binarySearch(i);
		}
		
		// 출력
		sb.append(cnt);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[] arr; 
	static int cnt;
	
	static void binarySearch(int idx) {
		int left = 0;
		int right = N-1;
		
		while(left < right) {
			// 나 자신을 포함하지 않는 수들 선택
			if(left == idx) {
				left++;
			}
			if(right == idx) {
				right--;
			}
			// break;
			if(left >= right) {
				return;
			}
			
			// 합
			int sum = arr[left] + arr[right];
			
			if(sum == arr[idx] ) { // 두 수의 합이 현재 수가 되는 경우
				cnt++;
				return;
			}
			else if(sum < arr[idx]) { // 두 수의 합이 현재 수보다 작은 경우
				left++; // 작은 값보다 더 큰 값 선택하기
				
			}
			else { // 두 수의 합이 현재 수보다 큰 경우
				right--; // 큰 값보다 더 작은값 선택하기
			}
		}
	}
}
