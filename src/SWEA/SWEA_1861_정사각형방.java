package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class SWEA_1861_정사각형방 {

	public static void main(String[] args) throws IOException {
		/**
		 * SWEA 1861. 정사각형 방 ------------------
		 * 
		 * NxN 형태로 방이 늘어서 있음
		 * 
		 * 위에서 i~j번째 방에 숫자 A_i,j가 적혀 있음, 모든 방에 대해 서로 다른 수
		 * 
		 * 어떤 방에 있다면 상하좌우의 방으로는 이동 가능 이동하려는 방에 적힌 숫자가 정확이 1 더 커야 한다
		 * 
		 * 처음 어떤 수가 적힌 방에 있어야 가장 많은 개수의 방을 이동할 수 있는지 -> DFS
		 *
		 * 1 <= N <= 1000 
		 * 1 <= A_i <= 1,000,000 : 수 - 시간복잡도와 관계 없다
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			result = new int[N * N + 1];

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 문제풀이
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					cnt = 0;
					DFS(i, j); // DFS를 통해 현재 방에서 갈 수 있는 최대 개수 구하기
					result[arr[i][j]] = cnt; // 결과 배열에 저장
				}
			}

			int room = 0;
			int max = result[0];
			for (int i = 1; i <= N*N; i++) { // 각 방이 가질 수 있는 수 배열 탐색
				if (max < result[i]) { // 현재 번호의 방이 이동할 수 있는 방의 크기가 더 크다면
					max = result[i]; // 최신화
					room = i;
				}
			}

			// 출력
			sb.append("#").append(t).append(" ").append(room).append(" ").append(result[room]).append("\n");
			bw.write(sb.toString());
			bw.flush();
		}

		bw.close();
		br.close();
	}

	static int T;
	static int N;
	static int[][] arr;
	static int[] result;
	static int cnt;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, -1, 1 };

	static void DFS(int y, int x) {
		cnt++;

		for (int d = 0; d < 4; d++) {
			int ny = y + dy[d];
			int nx = x + dx[d];

			if (ny < 0 || nx < 0 || ny >= N || nx >= N) {
				continue;
			}

			if (arr[ny][nx] - arr[y][x] != 1) {
				continue;
			}

			DFS(ny, nx);
		}
	}
}
