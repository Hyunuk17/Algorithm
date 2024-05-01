package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_17387_선분교차2 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ_17387. 선분 교차 2 
		 * --------------------
		 * 
		 * [문제 설명] 
		 * 2차원 좌표 평면 위의 두 선분 L1, L2 
		 * 두 선분이 교차하는지 구하기
		 * - L1: (x1, y1), (x2, y2) 
		 * - L2: (x3, y3), (x4, y4)
		 * 
		 * 교차(1), 교차하지 않음(0)
		 * 
		 * [제한사항]
		 * -1,000,000 <= x1, y1, ... <= 1,000,000 
		 * 선분의 길이는 0보다 크다
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		A = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		B = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		st = new StringTokenizer(br.readLine());
		C = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		D = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

		// 문제 풀이
		// CCW 알고리즘
		// L1 : A, B
		// L2 : C, D
		int l1 = ccw(A, B, C) * ccw(A, B, D);
		int l2 = ccw(C, D, A) * ccw(C, D, B);

		 
		if (l1 == 0 && l2 == 0) { // 일직선 상 판별
			if (Math.min(A.x, B.x) <= Math.max(C.x, D.x) 
					&& Math.min(C.x, D.x) <= Math.max(A.x, B.x) 
					&& Math.min(A.y, B.y) <= Math.max(C.y, D.y) 
					&& Math.min(C.y, D.y) <= Math.max(A.y, B.y)) {
				sb.append(1);
			} else {
				sb.append(0);
			}
		}
		else if (l1 <= 0 && l2 <= 0) {
			sb.append(1);
		} else {
			sb.append(0);
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static Point A;
	static Point B;
	static Point C;
	static Point D;

	static int ccw(Point a, Point b, Point c) {
		long result = (a.x * b.y + b.x * c.y + c.x * a.y) - (a.x * c.y + c.x * b.y + b.x * a.y);

		if (result == 0) {
			return 0;
		} else if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	static class Point {
		long x;
		long y;

		public Point(long x, long y) {
			this.x = x;
			this.y = y;
		}
	}
}
