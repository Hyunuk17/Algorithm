package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1158_요세푸스 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 1158. 요세푸스 문제 ------------------
		 * 
		 * 1번부터 N번까지 N명의 사람이 원을 이루며 앉아있음 순서대로 K번째 사람 제거 이 과정을 반복
		 * 
		 * 원에서 사람들이 제거되는 순서 (N, K) - 요세푸스 순열
		 * 
		 * Ex) (7,3) : <3, 6, 2, 7, 5, 1, 4>
		 * 
		 * 1 <= K <= N <= 5000
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		sb.append("<");

		// 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		// 1~N queue에 담기
		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}

		// Queue가 empty면 종료
		while (!queue.isEmpty()) {
			// K-2번 까지 맨 앞 원소를 맨 뒤로 이동
			for (int i = 0; i < K-1; i++) {
				queue.add(queue.poll());
			}

			// K-1번 원소를 제거 및 출력
			sb.append(queue.poll());
			// 마지막 원소가 아니면 ',' 출력
			if (!queue.isEmpty()) {
				sb.append(", ");
			}
		}

		sb.append(">");
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int K;
	static Queue<Integer> queue = new LinkedList<>();
}
