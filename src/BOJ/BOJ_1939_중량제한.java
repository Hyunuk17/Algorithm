package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1939_중량제한 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1939. 중량제한 ---------------
		 * 
		 * 섬나라 다리로 연결 다리마다 중량 제한이 있음 => 양방향 가중치 그래프
		 * 
		 * 한 번의 이동에서 옮길 수 있는 물품들의 중량의 최댓값 구하기
		 * 
		 * 2 <= N <= 10,000 1 <= M <= 100,000 1 <= A, B <= N 중량제한 C : 1 <= C <=
		 * 1,000,000,000
		 * 
		 */

		// 답을 봤습니다
		// BFS에서 이분탐색을 적용하는 과정을 떠올리지 못했음

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		graph = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++) {
			graph[i] = new ArrayList<>();
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			graph[a].add(new Node(b, weight));
			graph[b].add(new Node(a, weight));
			high = Math.max(high, weight);
		}
		low = 1;

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		// 문제풀이
		// 이분탐색을 하면서 현재 중량 mid가 가능한 값인지 BFS
		binarySearch();

		// 출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}

	static int N;
	static int M;
	static List<Node>[] graph;
	static int high;
	static int low;
	static int mid;
	static int start;
	static int end;
	static int max;
	static Queue<Node> queue;
	static boolean[] visited;

	static void binarySearch() {
		queue = new ArrayDeque<>();
		visited = new boolean[N + 1];

		while (low <= high) {
			mid = (low + high) / 2; // 물건의 중량 값
			queue.add(new Node(start, 0));
			visited[start] = true;

			if (check() == true) { // 목적지에 방문가능하면
				max = Math.max(max, mid); // 최대 중량제한 반영
				low = mid + 1; // 더 큰 범위에서 다시 탐색
			} else { // 목적지에 방문 불가능하면
				high = mid - 1; // 더 작은 범위에서 다시 탐색
			}

			// 다음 탐색을 위한 초기화
			queue.clear();
			Arrays.fill(visited, false);
		}

	}

	static boolean check() {
		while (!queue.isEmpty()) {
			Node now = queue.poll();

			if (now.v == end) { // 도착
				return true;
			}

			for (Node next : graph[now.v]) {
				if (next.weight < mid) { // 중량제한 초과
					continue;
				}
				if (visited[next.v] == true) { // 이미 방문
					continue;
				}

				visited[next.v] = true; // 방문처리
				queue.add(next);
			}
		}

		return false; // 도착 실패
	}

	static class Node {
		int v;
		int weight;

		public Node(int v, int weight) {
			this.v = v;
			this.weight = weight;
		}
	}
}
