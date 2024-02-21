package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_4012_요리사 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 4012. 요리사 ---------------
		 * 
		 * 두 명의 손님에게 음식 제공 최대한 비슷한 음식을 만들어야 함
		 * 
		 * N개 식재료 N/2개 씩 나누어 2개의 요리
		 * 
		 * S_ij == 식재료 i와 식재료 j의 시너지 1 <= i, j <= N (i!=j)
		 * 
		 * 모든 음식 조합에서 맛 차이가 최소가 되는 값 구하기
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Test Case
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			board = new int[N][N];
			arr = new int[N];
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					board[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			// N/2개를 담기 위한 0-1 배열
			for(int i=0;i<N/2;i++) {
				arr[i] = 1;
			}
			Arrays.sort(arr);
			
			
			// 문제풀이
			while(true) { 
				if(!combination(0, 0)) { // 더이상 조합이 없다면 break
					break;
				}
				A = 0;
				B = 0;
				
				synergy(); // 현재 조합에서 각 음식의 시너지의 합을 계산
				min = Math.min(min, Math.abs(A - B)); // 각 음식의 맛 차이가 최소가 되도록
			}
				
			// 출력
			sb.append("#").append(t).append(" ").append(min).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}

		bw.close();
		br.close();
	}

	static int T;
	static int N;
	static int[][] board;
	static int[] arr;
	static int A;
	static int B;
	static int min;

	// Next_Permutation 응용 조합 구하기
	static boolean combination(int depth, int flag) {
		int pivot = N-1; // Pivot 초기값, 마지막 원소
		while(pivot > 0 && arr[pivot-1] >= arr[pivot] ) { // 뒤에서부터 증가하다가 감소하는 지점 구하기
			pivot--;
		}
		
		if(pivot == 0) { // 마지막 순열인 경우
			return false; // return false
		}
		
		int big = N-1; // pivot보다 오른쪽 요소 중
		while(arr[pivot-1] >= arr[big]) { // pivot보다 큰 가장 작은 원소
			big--;
		}
		
		// swap(pivot-1, big)
		int tmp = arr[pivot-1];
		arr[pivot-1] = arr[big];
		arr[big] = tmp;
		
		// pivot 오른쪽 원소 오름차순으로 정렬
		Arrays.sort(arr, pivot, N);
		
		return true;
	}
	
	
	// 현재 조합에서 각 음식의 맛 구하기
	static void synergy() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(i == j) { // 같은 음식끼리는 시너지 없음
					continue;
				}
				
				// 음식 A
				if(arr[i] == 1 && arr[j] == 1) {
					A += board[i][j];					
				}
				// 음식 B
				else if(arr[i] == 0 && arr[j] == 0) {
					B += board[i][j];
				}
			}
		}
	}
}
