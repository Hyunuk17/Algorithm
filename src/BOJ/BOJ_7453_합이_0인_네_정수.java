package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_7453_합이_0인_네_정수 {
	/*
	 * BOJ 7453. 합이 0인 네 정수
	 * ----------------------
	 * 
	 * [문제 설명]
	 * 정수로 이루어진 크기가 같은 배열 A, B, C, D
	 * A[a], B[b], C[c], D[d]의 합이 0이 되는 (a,b,c,d) 쌍의 개수 구하기
	 * 
	 * [제한사항]
	 * 1 <= n <= 4,000
	 * -2^28 <= arr[i] <= 2^28 (long?, int)
	 * 
	 */

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[4][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			int D = Integer.parseInt(st.nextToken());
			
			arr[0][i] = A;
			arr[1][i] = B;
			arr[2][i] = C;
			arr[3][i] = D;
		}
		
		// 문제풀이
		// 배열에서 4개 원소를 선택하는 방법
		// 완전 탐색의 경우 : (4*10^3) ^ 4 => 4^4*10^12(안됨)
		// DP? 이분탐색? 투포인터? 누적합?
		// 정렬 후 양쪽에서 탐색? 근데 배열이 4개라서...
		// 2개로 계산 후 계산이 가능한가?
		// AB 두 배열로 가능한 모든 경우의 수를 가지는 배열 만들기
		// CD 두 배열로 가능한 모든 경우의 수를 가지는 배열 만들기
		AB = new int[N*N];
		CD = new int[N*N];
		int idx = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {				
				AB[idx] = arr[0][i] + arr[1][j];
				CD[idx] = arr[2][i] + arr[3][j];
				idx++;
			}
		}
		
		// 두 배열로 만들고 (N*N)^2의 경우의 수 : (4000^2)^2 TLE
		// O(N^2)이 안됨, O(NlogN)으로 단축
		// AB를 순회하면서 O(N)
		// CD를 이분탐색으로 찾는다면 O(logN)
		// O(NlogN)으로 단축 가능
		
		Arrays.sort(CD); // 이분 탐색을 하기 위함 
		for(int num : AB) {
			// 순수 이분 탐색을 한다면, CD에 정답이 여러개 있는 경우를 찾을 수 없음 
//			binarySearch(-num); 
			
			int upper = upperBound(-num);
			int lower = lowerBound(-num);
			// 개수를 인덱스로 파악 upper - lower
			cnt += (upper - lower);
		}
		
		sb.append(cnt);
		
		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}
	
	static int N;
	static int[][] arr;
	static int[] AB;
	static int[] CD;
	static long cnt;
	
	static int upperBound(int num) {
		int left = 0;
		int right = N*N-1;
		
		while(left <= right) {
			int mid = (left + right)/2;
			if(CD[mid] <= num) { // num 포함
				left = mid + 1;
				
			}
			else {
				right = mid - 1;
			}
		}
		
		return left;
	}
	
	static int lowerBound(int num) {
		int left = 0;
		int right = N*N-1;
		
		while(left <= right) {
			int mid = (left + right) /2;
			if(CD[mid] < num) { // num보다 작으나 가장 큰 위치
				left = mid + 1;
			}
			else {
				right = mid - 1;
			}
		}
		
		return left;
	}
}
