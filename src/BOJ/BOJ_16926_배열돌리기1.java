package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16926_배열돌리기1 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 16926. 배열 돌리기 1 --------------------
		 * 
		 * 크기 NxM 배열, 반시계 방향으로 R번 돌리기
		 * 
		 * 2 <= N. M <= 300 1 <= R <= 1000 min(N,M) mod 2 = 0 1 <= A_ij = 10^8
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());

		board = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 문제풀이
		for (int i = 0; i < R; i++) {
			rotate(); // R만큼 회전
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}

		// 출력
		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static int R;
	static int[][] board;

	static void rotate() {
		// 회전을 위해 들어갈 수 있는 깊이 까지(Math.min(N,M)/2) 반복
		for (int i = 0; i < Math.min(N,M)/2; i++) {

			// 우
			int tmp = board[i][i]; // 임시저장
			for (int j = i; j < (M - 1)-i; j++) {
				board[i][j] = board[i][j + 1];
			}

			// 상
			for (int j = i; j < (N - 1)-i; j++) {
				board[j][(M - 1)-i] = board[j + 1][(M - 1)-i];
			}

			// 좌
			for (int j = (M - 1)-i; j > i; j--) {
				board[(N - 1)-i][j] = board[(N - 1)-i][j - 1];
			}

			// 하
			for (int j = (N - 1)-i; j > i; j--) {
				board[j][i] = board[j - 1][i];
			}

			board[1+i][i] = tmp; // 임시저장한 값 저장
		}
	}
}
