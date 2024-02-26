package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_23559_밥 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 23559. 밥 ------------
		 * 
		 * 식당에 2개의 메뉴 A - 5000원 B - 1000원 각 메뉴는 가중치가 있음
		 * 
		 * N일 동안 매일 하나를 선택해 먹음
		 * 
		 * 총 X원 이하 사용 가중치(맛) 최대 구하기
		 * 
		 * 1 <= N <= 100,000 1,000*N <= X <= 5000*N 1 <= A <= 10,000 : 메뉴 A의 맛 1 <= B <=
		 * 10,000 : 메뉴 B의 맛
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		menu = new int[N][2];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			menu[i][0] = Integer.parseInt(st.nextToken()); // 5000 value
			menu[i][1] = Integer.parseInt(st.nextToken()); // 1000 value
			sum += menu[i][1]; // 항상 1000원 메뉴를 선택한 상태를 기준
			X -= 1000; // 1000*N 빼기
		}

		// 정렬
		Arrays.sort(menu, (o1, o2) -> { // 5000원 메뉴의 맛 - 1000원 메뉴의 맛
			return (o1[1] - o2[1]) - (o1[0] - o2[0]); // 내림차순 정렬
		});

		// 기본적으로 1000원을 선택했다고 가정
		for (int i = 0; i < N; i++) {
			// 돈이 허락하는 내에서 5000원 메뉴가 이득이라면
			if (X >= 4000 && menu[i][0] > menu[i][1]) {
				X -= 4000; // 4000원 추가
				sum += menu[i][0] - menu[i][1]; // 맛의 차이만큼 추가, 정렬되어 있으므로 항상 최고 value
			}
		}

		// 출력
		sb.append(sum);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int X;
	static int[][] menu;
	static int[][] dp;
	static int sum;

}
