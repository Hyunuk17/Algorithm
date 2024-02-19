package SWEA;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class SWEA_2806_NQueen {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 2806. N-Queen
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// Test Case
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			// 입력
			n = Integer.parseInt(br.readLine());
			cnt = 0;
			arr = new int[n];
			
			// 문제 풀이
			solve(0);

			// 출력
			bw.write("#" + t + " " + cnt + "\n");
			bw.flush();
		}
	}

	static int n;
	static int cnt;
	static int[] arr;

	static void solve(int depth) {
		if (depth == n) { // 종료 조건: 끝 행에 도착하면 좋료
			cnt++; // 개수 추가
			return;
		}

		// 
		for (int i = 0; i < n; i++) {
			arr[depth] = i; // 행의 열

			if (check(depth)) {
				solve(depth + 1);
			}
		}
	}

	static boolean check(int depth) {
		for (int i = 0; i < depth; i++) {

			// 같은 열에 있는 경우
			if (arr[depth] == arr[i]) {
				return false;
			}
			// 같은 대각선에 있는 경우
			// 기울기 비교
			else if (Math.abs(depth - i) == Math.abs(arr[depth] - arr[i])) {
				return false;
			}
		}
		return true;
	}

}
