import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_14865_곡선_자르기 {

	public static void main(String[] args) throws IOException {
		/**
		 * 산악 지도 그리기 := BOJ 곡선 자르기 -----------
		 * 
		 * 직사각형 캔버스 중앙 원점(0, 0), (y, x) Cycle이 있는 그래프
		 * 
		 * x축을 기준으로 양분 x축 위 부분으로 봉우리 판단
		 * 
		 * 포함되지 않는 봉우리 개수 포함하지 않는 봉우리 개수 출력
		 * 
		 * 서브테스크 문제 N <1,000이하 11점 N <= 10,000이하 24점 4 <= N <= 10^6이하 65점 -10^9 <= x, y
		 * <= 10^9 : int y != 0
		 * 
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 입력
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());

		List<Point> list = new ArrayList<>();
		List<Node> peak = new ArrayList<>();
		int sx = Integer.MAX_VALUE;
		int sy = Integer.MAX_VALUE;
		int x, y, idx = 0;

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());

			// 시작에 일관성을 주기위해 왼쪽 아래부터 시작
			if (x < sx && y < 0) {
				sx = x;
				sy = y;
				idx = i;
			}

			list.add(new Point(x, y));
		}

		int px = sx;
		int py = sy;
		int len = list.size();

		for (int i = 0; i < len; i++) {
			Point now = list.get((idx + i) % len); // 시작 지점을 idx부터

			// - => + 봉우리 시작지점
			if (py < 0 && now.y > 0) {
				px = now.x;
				py = now.y;
			}
			// + => - 봉우리 끝나는 지점
			else if (py > 0 && now.y < 0) {
				// 왼쪽으로 가는 봉우리 or 오른쪽으로 가는 봉우리
				int minX = Math.min(px, now.x);
				int maxX = Math.max(px, now.x);

				px = now.x;
				py = now.y;

				Node left = new Node(minX, true); // y좌표는 필요 없음, 봉우리 시작
				Node right = new Node(maxX, false); // 봉우리 끝
				peak.add(left);
				peak.add(right);
			}
		}

		// 정렬 x좌표가 커지는 순
		Collections.sort(peak);

		// 봉우리를 담는 스택
		Stack<Integer> stack = new Stack<>();
		int length = peak.size();
		int num = 0;

		for (int i = 0; i < length; i++) {
			boolean check = peak.get(i).isStart;

			if (check == true) { // 시작하는 봉우리 Push
				stack.add(num);
			} else { // 끝나는 봉우리
				int left = stack.pop(); // Pop

				if (stack.isEmpty()) { // pop했는데 스택에 남아있는게 없음 == 겹쳐있는 봉우리가 없음
					NoCover++; // 포함하지 않음
				}

				// pop했을 때 스택에 남아있는게 있음 == 봉우리가 겹쳐 있음
				if (left == num) { // push - pop이 바로 이어져있음
					NoContain++; // 포함된 봉우리, 포함하지 않음
				}

				// pop했을 때, 스택에 남아있는게 있음
				// push - pop이 연결되어 있지 않음 == 내부에 다른 봉우리가 존재함
				// 포함하면서, 포함되는 상태
				num++;
			}
		}

		// 출력
		// 포함되지 않는 : 전체 - 포함되는
		// 포함하지 않는 : 전체 - 포함하는
		sb.append(NoCover).append(" ").append(NoContain);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int NoCover;
	static int NoContain;

	static class Point {
		int x;
		int y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Node implements Comparable<Node> {
		int start;
		boolean isStart;

		public Node(int start, boolean isStart) {
			this.start = start;
			this.isStart = isStart;
		}

		@Override
		public int compareTo(Node o) {
			return this.start - o.start;
		}
	}

}
