package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_20955_민서의_응급_수술 {
	/*
	 * BOJ 20955. 민서의 응급 수술 
	 * -----------------------
	 * 
	 * [문제 설명] 
	 * 뇌 속의 모든 뉴런을 하나의 트리 형태로 연결 
	 * - 사이클이 존재하지 않는 연결 그래프
	 * 
	 * 가능한 연산 
	 * - 연결되지 않은 두 뉴런 연결 
	 * - 이미 연결된 두 뉴런의 연결 끊기
	 * 
	 * 모든 뉴런을 하나의 트리 형태로 연결하는 최소 연산 횟수 구하기
	 * 
	 * [입력] 
	 * 뉴런의 개수 : N 
	 * 시냅스의 개수 : M 
	 * 시냅스로 연결된 두 뉴런의 번호 : u, v
	 * 
	 * [제한사항] 
	 * 2 <= N <= 100,000 
	 * 1 <= M <= min(N * (N-1) / 2, 100,000) 
	 * 1 <= u, v <= N
	 * u != v 두 뉴런 사이는 최대 1개의 시냅스(edge)만 존재
	 * 
	 * 
	 */

	public static void main(String[] args) throws IOException {
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		makeSet();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			create(u, v);
		}

		// 문제풀이
		// 트리 : 사이클이 존재하지 않는 연결 그래프 만들기
		// Connected Component 연결 문제
		// Union-Find
		// 이미 연결된 C.C를 끊을 수도 있어야 한다

		// 사이클 끊기 -> Union-Find 실행
		// 입력받으면서 CC를 확인할 때, Cycle이면 애초에 연결하지 않기(끊는연산+1)
		// Union 연산 하면서 +1
		for (int i = 1; i < N; i++) {
			union(i, i + 1);
		}
		sb.append(cnt);

		// 출력
		bw.write(sb.toString());
		bw.flush();

		bw.close();
		br.close();
	}

	static int N;
	static int M;
	static int[] parent;
	static int cnt;

	static void makeSet() {
		parent = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			parent[i] = i;
		}
	}

	static int findSet(int x) {
		if (parent[x] == x) {
			return x;
		} else {
			return parent[x] =  findSet(parent[x]); // Path Compression
		}
	}

	static void create(int x, int y) {
		int xRoot = findSet(x);
		int yRoot = findSet(y);

		if (xRoot == yRoot) {
			cnt++;
		} else {
			parent[yRoot] = xRoot;
		}
	}

	static void union(int x, int y) {
		int xRoot = findSet(x);
		int yRoot = findSet(y);

		if (xRoot != yRoot) {
			parent[yRoot] = xRoot;
			cnt++;
		}
	}
}
