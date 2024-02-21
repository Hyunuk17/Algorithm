package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;

public class BOJ_11286_절댓값힙 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 11286. 절댓값 힙 -----------------
		 * 
		 * 배열에 정수 x를 넣기 절댓값이 가장 작은 값 출력, 그 값 제거
		 * 
		 * 1 <= N <= 100,000 x != 0 : 값 추가 x == 0 : 값 출력/제거
		 * 
		 * 입력되는 정수는 Integer
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		// 입력 받기
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			if (n == 0) { // 입력이 0일 때,
				if (heap.isEmpty()) { // Heap에 아무것도 아니면
					sb.append(0); // 0을 출력
				} else { // Heap에 원소가 있으면
					sb.append(heap.poll()); // 루트 노드 출력
				}
				sb.append("\n");
				continue;
			}
			heap.add(n); // 입력이 0이 아닌 숫자이면 Heap에 추가
		}

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}

	static int N;
	static int[] arr;
	
	// 우선순위 큐
	// 절대값이 작은 순 -> 절대값이 같으면 음수가 먼저
	static PriorityQueue<Integer> heap = new PriorityQueue<>((o1, o2) ->  {
		if (Math.abs(o1) == Math.abs(o2)) {
			return o1 < o2 ? -1 : 1;
		}

		return Math.abs(o1) - Math.abs(o2);
	});


}
