package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_1992_쿼드트리 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1992. 쿼드트리
		 * ---------------
		 * 
		 * 흑백 영상을 압축하여 포현하는 데이터 구조
		 * 
		 * 흰점(0), 검은점(1) 
		 * 섞여있는 경우 왼쪽 위, 오른쪽 위, 왼쪽 아래, 오른쪽 아래 4가지로 나누어 압축 
		 * 압축한 결과를 괄호 안에 묶어서 표현
		 * 
		 * 영상의 크기 : 1 <= N <= 64
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		N = Integer.parseInt(br.readLine());
		board = new int[N + 1][N + 1];

		for (int i = 0; i < N; i++) {
			String str = br.readLine();
			for (int j = 0; j < N; j++) {
				board[i][j] = str.charAt(j) - '0';
			}
		}

		// 문제풀이
		// 분할 정복
		 re(N, 0, 0);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static StringBuilder sb = new StringBuilder();
	static int[][] board;

	static void re(int n, int sy, int sx) {

		// 현재 보고 있는 범위가 같은 색인지 체크
		boolean flag = true;
		int tmp = board[sy][sx]; // 초기 색 
		for (int i = sy; i < sy + n; i++) {
			for (int j = sx; j < sx + n; j++) {
				if (tmp != board[i][j]) { // 다른 색이 존재한다면
					flag = false; // flag = false;
					break;
				}
			}
		}

		// 범위가 모두 같은 색이라면
		if (flag == true) {
			sb.append(tmp); // 현재 색으로 압축
			return;
		}
		
		// 범위가 다른 색이라면 4분면으로 분할
		sb.append("(");

		int size = n /2;
		// 왼쪽 위
		re(size, sy, sx);

		// 오른쪽 위
		re(size, sy, sx + size);

		// 왼쪽 아래
		re(size, sy + size, sx);

		// 오른쪽 아래
		re(size, sy + size, sx + size);

		
		sb.append(")");
	}
}
