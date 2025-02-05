package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1966_프린터_큐 {
	/*
	 * BOJ 1966. 프린터 큐 
	 * -----------------
	 * 
	 * [문제설몀] 프린터 기기 
	 * - 현재 Queue의 가장 앞에 있는 문서의 중요도 확인 
	 * - 나머지 문서들 중 현재 문서보다 중요도가 높은 문서가 있는지 확인 
	 * - 있으면 현재 문서를 Queue의 가장 뒤로 재배치 
	 * - 그렇지 않다면 바로 인쇄
	 * 
	 * 어떤 한 문서가 몇 번째로 인쇄되는지 알아내기
	 * 
	 * [입력] 
	 * 테스트케이스 수 T 
	 * 문서의 개수 N 
	 * 궁금한 문서의 Queue에서 위치 M
	 * 
	 * [출력] 
	 * 각 테스트 케이스에 대한 문서가 몇 번째로 인쇄되는지 출력
	 *
	 * [제한사항] 
	 * 1 <= N <= 100 
	 * 0 <= M < N
	 *
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		T = Integer.parseInt(br.readLine());

		// 문제풀이
		while (T-- > 0) {
			queue = new ArrayList<Node>();
			int idx = 0;
			int result = 0;
			int cnt = 1;
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				Node node = new Node(idx++, Integer.parseInt(st.nextToken()));
				queue.add(node);
			}

			while (!queue.isEmpty()) {
				// Queue의 가장 앞의 문서의 중요도 확인
				Node head = queue.get(0);

				// 현재 최고 중요도 갱신
				int max = 0;
				for (int i = 0; i < queue.size(); i++) {
					max = Math.max(max, queue.get(i).w);
				}

				if (head.w < max) {
					queue.remove(0);
					queue.add(head);
				} else { // head.w >= max
					queue.remove(0);
					if (head.idx == M) {
						result = cnt;
					}
					cnt++;
				}
			}
			sb.append(result).append("\n");
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int T;
	static int N;
	static int M;
	static int[] arr;
	static List<Node> queue;

	static class Node {
		int idx;
		int w;

		public Node(int idx, int w) {
			this.idx = idx;
			this.w = w;
		}
	}
}
