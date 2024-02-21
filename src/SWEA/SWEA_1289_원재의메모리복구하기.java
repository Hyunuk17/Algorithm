package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class SWEA_1289_원재의메모리복구하기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 반복할 횟수
		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			ans = 0;
			// 정답 입력 받기
			String str = br.readLine();
			// 배열 초기화
			arr = new int[str.length()];
			result = new int[str.length()];

			// 정답 배열 받기
			for (int i = 0; i < str.length(); i++) {
				arr[i] = str.charAt(i) - '0';
			}

			// 앞에부터 탐색하면서 반복
			for (int i = 0; i < str.length(); i++) {
				// 현재 번호가 목표로 하는 수이면
				if (result[i] == arr[i]) {
					// 
					continue;
				} 
				// 현재 번호가 목표로 하는 수가 아니라서 변경이 필요
				else {
					// 변경 횟수 추가
					ans++;

					// 뒤의 배열을 전부 반대의 수로 변경
					for (int j = 0; j < str.length(); j++) {
						// 0 -> 1
						if (result[j] == 0) {
							result[j] = 1;
						} 
						// 1 -> 0
						else {
							result[j] = 0;
						}
					}
				}
			}

			// 정답 출력
			bw.write("#" + t + " " + ans + "\n");
			bw.flush();
		}
	}

	static int T;
	// 정답
	static int ans;
	// 변환하면서 비교할 배열
	static int[] result;
	// 입력받은 정답 배열
	static int[] arr;
}
