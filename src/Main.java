import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	/*
	 * [문제설몀] 작업 N개 각 작업들은 선행 관계로 이어져 있음(1번 제외) 모든 작업을 완료하기 위해 필요한 최소 시간 구하기
	 *
	 * [제한사항] 3 <= N <= 10,000 1 <= N[i] <= 100
	 */
	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		works = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			works[i] = new ArrayList<>();
		}

		T = new int[N + 1];
		indegrees = new int[N + 1];
		result = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			int indegree = Integer.parseInt(st.nextToken());

			T[i] = time;
			indegrees[i] = indegree;
			for (int j = 0; j < indegree; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				works[tmp].add(i);
			}
		}

		// 문제풀이
		// 1. 위상정렬로 각 작업의 순서를 구하기
		// 2. 모든 작업이 끝나는데 걸리는 시간값 구하기

		topologySort();

		// 출력
		for (int i = 1; i <= N; i++) {
			max = Math.max(max, result[i]);
		}
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int[] T;
	static int[] indegrees;
	static List<Integer>[] works;
	static int[] result;
	static int max = Integer.MIN_VALUE;

	static void topologySort() {
		Queue<Integer> queue = new ArrayDeque<>();

		// 선행 노드가 없는 노드를 queue에 삽입
		for (int i = 1; i <= N; i++) {
			if (indegrees[i] == 0) {
				queue.add(i);
			}

			// result 시간 초기화
			result[i] = T[i];
		}

		while (!queue.isEmpty()) {
			int cur = queue.poll();

			// 인접 노드 탐색
			for (Integer next : works[cur]) {
				// 연결 끊기
				indegrees[next]--;

				// 인접 노드의 개수가 0이되면 추가
				if (indegrees[next] == 0) {
					queue.add(next);
				}

				// next 작업이 완료되는 시간 구하기
				result[next] = Math.max(result[next], result[cur] + T[next]);
			}
		}
	}

}
