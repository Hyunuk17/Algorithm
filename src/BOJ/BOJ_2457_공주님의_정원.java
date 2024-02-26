package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2457_공주님의_정원 {

	public static void main(String[] args) throws IOException {
		/**
		 * 
		 * 프리랜서 := BOJ 2457. 공주님의 정원 -----
		 * 
		 * N개의 프로젝트 같은 해에 시작해서 같은 해에 마무리 [)
		 * 
		 * 조건을 만족하는 프로젝트 선택 1) 3.1 ~ 11.30까지 매일 한 가지 이상의 프로젝트에 참여 2) 프로젝트의 수를 가능한 적게
		 * 
		 * 1 <= N <= 100,000
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[N][2];

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sMonth = Integer.parseInt(st.nextToken()) * 100;
			int sDay = Integer.parseInt(st.nextToken());
			int eMonth = Integer.parseInt(st.nextToken()) * 100;
			int eDay = Integer.parseInt(st.nextToken());
			arr[i][0] = sMonth + sDay;
			arr[i][1] = eMonth + eDay;
		}

		// 문제풀이
		Arrays.sort(arr, (o1, o2) -> {

			if (o1[0] == o2[0]) {
				return o1[1] - o2[1]; // 같으면 끝
			}
			return o1[0] - o2[0]; // 시작
		});

		check();

		// 출력
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int[][] arr;
	static int min;

	static void check() {
		int cnt = 0;
		int end = 301;

		int max = 0;
		int idx = 0;
		while (end < 1201) {
			max = 0; // 현재 끝 시간보다 시작 시간이 빠른 원소 중 끝 시간이 가장 긴 원소
			boolean flag = false; // 변환 체크
			for (int i = idx; i < N; i++) { // idx부터 순회
				if (end < arr[i][0]) { // 중간에 빈 공간이 생김
					break;
				}

				// 현재 끝 시간보다 같거나 먼저 끝나고 끝나는 시간이 가장 긴 원소
				if (arr[i][0] <= end && max < arr[i][1]) {
					max = arr[i][1];
					idx = i + 1; // 이 원소 다음 원소부터 시작
					flag = true;
				}
			}

			// 값 변환이 이루어졌다면
			if (flag == true) {
				end = max;
				cnt++;
			} else { // 변환이 없음: 정렬했기 때문에 이후로도 없음
				break;
			}
		}

		if (max < 1201) {
			min = 0;
		} else {
			min = cnt;
		}
	}

}
