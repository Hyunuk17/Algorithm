package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_1208_Flatten {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1208. Flatten ------------------
		 * 
		 * 노란색 상자들이 쌓여있는 Board
		 * 
		 * 높은 곳의 상자를 낮은 곳에 옮김 최고점과 최저점의 간격을 줄이는 평탄화
		 * 
		 * 모두 수행하고 나면, max-min의 차이가 최대 1이내
		 * 
		 * 작업 횟수 제한 있음, max-min 반환
		 * 
		 * witdh = 100 1 <= height <= 100 1 <= dump <= 1000
		 * 
		 * Test case = 10
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		for (int t = 1; t <= 10; t++) {
			// 입력
			dump = Integer.parseInt(br.readLine());
			// 상자의 높이가 100까지
			arr = new int[101];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 100; i++) {
				int n = Integer.parseInt(st.nextToken());
				// n개의 상자를 가진 부분 개수
				arr[n]++;
				// 최대, 최소 상자의 높이
				max = Math.max(max, n);
				min = Math.min(min, n);
			}

			// dump만큼 반복
			for (int i = 0; i < dump; i++) {
				// 최고점과 최저점 높이가 1이내면 중지
				if (max - min <= 1) {
					break;
				}

				// 최고점의 위치를 하나 제거
				arr[max]--;
				arr[max - 1]++;
				// max개를 가진 위치가 없으면 max를 max-1로 변경
				if (arr[max] <= 0) {
					max--;
				}

				// 최저점의 위치를 하나 제거
				arr[min]--;
				arr[min + 1]++;
				// min개를 가진 위치가 없으면 min를 min+1로 변경
				if (arr[min] <= 0) {
					min++;
				}

			}

			// 최고점 - 최저점의 차를 출력
			bw.write("#" + t + " " + (max - min) + "\n");
			bw.flush();
		}
	}

	static int dump;
	static int[] arr;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
}
