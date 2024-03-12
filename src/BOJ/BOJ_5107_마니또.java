package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_5107_마니또 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * BOJ 5107. 마니또 ---------------
		 * 
		 * N명의 사람 마니또 활동
		 * 
		 * 마니또 체인 : 마니또 활동을 통한 연결 요소
		 * 
		 * 연결 요소 개수 찾기
		 * 
		 * 3 <= N <= 20
		 */

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		int tc = 1;
		while (true) {
			// 입력
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}

			// 루트 노드를 저장하는 배열
			parents = new int[20];
			rank = new int[20];

			// 각 노드 생성
			make();

			// Map<이름,번호>
			map = new HashMap<>();

			int idx = 0; // 사람 - 번호 매핑
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				String A = st.nextToken();
				String B = st.nextToken();

				// map.put()을 통해 값이 들어가면 return null이 반환됨
				if (map.put(A, map.getOrDefault(A, idx)) == null) {
					idx++; // put 되었으니 다음 idx++
				}
				if (map.put(B, map.getOrDefault(B, idx)) == null) {
					idx++;
				}

				// 연결 요소 생성
				union(map.get(A), map.get(B));
			}

			// 문제풀이
			// 연결요소의 개수를 구하는 문제
			// union-find 알고리즘을 통해 연결요소를 구하기

			// 연결 요소를 저장할 Set
			set = new HashSet<>();
			for (int i = 0; i < N; i++) {
				set.add(find(parents[i])); // 루트 노드를 set에 넣기
			}

			// 연결 요소의 개수를 출력
			sb.append(tc++).append(" ").append(set.size()).append("\n");
		}
		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();

	}

	static int N;
	static String[][] names;
	static Set<Integer> set;
	static Map<String, Integer> map;
	static int[] parents;
	static int[] rank;

	static void make() {
		for (int i = 0; i < N; i++) {
			parents[i] = i;
			rank[i] = 1;
		}
	}

	static int find(int x) {
		if (parents[x] == x) {
			return x;
		}

		// Path Compression
		return parents[x] = find(parents[x]);
	}

	static void union(int u, int v) {
		int uRoot = find(u);
		int vRoot = find(v);

		if (uRoot == vRoot) {
			return;
		}

		// Unioin by Rank
		// u와 v의 높이가 같으면
		if (rank[uRoot] == rank[vRoot]) {
			rank[uRoot]++; // 둘 중 아무거나 +1
		}

		// u의 트리 높이가 더 크면
		if (rank[uRoot] > rank[vRoot]) {
			parents[vRoot] = uRoot; // v를 u에 붙힘
		} else { // v의 트리 높이가 더 크면
			parents[uRoot] = vRoot; // u를 v에 붙힘
		}

	}
}
