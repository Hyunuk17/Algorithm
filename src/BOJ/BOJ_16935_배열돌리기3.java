package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16935_배열돌리기3 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 16935. 배열돌리기 ------------------
		 * 
		 * NxM 배열 연산을 R번 적용, 연산 총 6개
		 * 
		 * 1번 : 상하 반전 2번 : 좌우 반전 3번 : 오른쪽 90도 4번 : 왼쪽 90도 5번 : 4분위로 나누고 시계방향 6번 : 4분위로
		 * 나눈고 반시계방향
		 * 
		 * 2 <= N, M <= 100 1 <= R <= 1000 N, M은 짝수 1 <= A <= 10^8
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
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

		// 수행해야할 명령
		st = new StringTokenizer(br.readLine());
		while (R-- > 0) { 
			switch (Integer.parseInt(st.nextToken())) {
			case 1:
				op1();
				break;
			case 2:
				op2();
				break;
			case 3:
				op3();
				break;
			case 4:
				op4();
				break;
			case 5:
				op5();
				break;
			case 6:
				op6();
				break;
			}
		}

		// 출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(board[i][j]).append(" ");
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static int R;
	static int[][] board;

	static void op1() {
		// 상하 반전
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M; j++) {
				// swap
				int tmp = board[i][j];
				board[i][j] = board[(N - 1) - i][j];
				board[(N - 1) - i][j] = tmp;
			}
		}
	}

	static void op2() {
		// 좌우 반전
		for (int j = 0; j < M / 2; j++) {
			for (int i = 0; i < N; i++) {
				int tmp = board[i][j];
				board[i][j] = board[i][(M - 1) - j];
				board[i][(M - 1) - j] = tmp;
			}
		}
	}

	static void op3() {
		// 오른쪽 90도

		int[][] tmpBoard = new int[M][N]; // 새 배열 생성

		// 바꾸기 전 배열의 인덱스
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpBoard[j][(N - 1) - i] = board[i][j];
			}
		}

		// N과 M 변경 && Board 변경
		int temp = N;
		N = M;
		M = temp;

		board = tmpBoard;
	}

	static void op4() {
		// 오른쪽 90도

		int[][] tmpBoard = new int[M][N]; // 새 배열 생성

		// 바꾸기 전 배열의 인덱스
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				tmpBoard[(M - 1) - j][i] = board[i][j];
			}
		}

		// N과 M 변경 && Board 변경
		int temp = N;
		N = M;
		M = temp;

		board = tmpBoard;
	}

	static void op5() {
		// 4분위로 나눈 뒤 시계방향

		// 2사분면 저장
		int[][] tmpBoard = new int[N / 2][M / 2];
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				tmpBoard[i][j] = board[i][j];
			}
		}

		// 3사분면 -> 2사분면
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				board[i - N / 2][j] = board[i][j];
			}
		}

		// 4사분면 -> 3사분면
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				board[i][j - M / 2] = board[i][j];
			}
		}
		// 1사분면 -> 4사분면
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				board[i + N / 2][j] = board[i][j];
			}
		}
		// 2사분면 -> 1사분면
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				board[i][j + M / 2] = tmpBoard[i][j];
			}
		}

	}

	static void op6() {
		// 4분위로 나눈 뒤 반시계방향

		// 2사분면 저장
		int[][] tmpBoard = new int[N / 2][M / 2];
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				tmpBoard[i][j] = board[i][j];
			}
		}

		// 1사분면 -> 2사분면
		for (int i = 0; i < N / 2; i++) {
			for (int j = M / 2; j < M; j++) {
				board[i][j - M / 2] = board[i][j];
			}
		}

		// 4사분면 -> 1사분면
		for (int i = N / 2; i < N; i++) {
			for (int j = M / 2; j < M; j++) {
				board[i - N / 2][j] = board[i][j];
			}
		}

		// 3사분면 -> 4사분면
		for (int i = N / 2; i < N; i++) {
			for (int j = 0; j < M / 2; j++) {
				board[i][j + M / 2] = board[i][j];
			}
		}
		// 2사분면 -> 3사분면
		for (int i = 0; i < N / 2; i++) {
			for (int j = 0; j < M / 2; j++) {
				board[i + N / 2][j] = tmpBoard[i][j];
			}
		}
	}

}
