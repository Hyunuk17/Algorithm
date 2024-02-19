package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1074_Z {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 1074. -Z
		 * ------------
		 * 
		 * 2^Nx2^N 배열을 Z 모양으로 탐색
		 * 왼쪽 위, 오른 쪽 위, 왼쪽 아래, 오른쪽 아래 순서로 방문
		 * 
		 * r행 c열을 몇 번째로 방문하는지 구하기
		 */
		
		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st= new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		int size = (int) Math.pow(2, N); // Size는 2^N
		
		// 문제풀이
		// 분할 정복
		re(0,0,size);
	
		// 출력
		bw.write(ans+"");
		bw.flush();
		bw.close();
		br.close();

	}
	
	static int N;
	static int r;
	static int c;
	static int idx = 0;
	static int ans;

	static void re(int y, int x, int n) {
		if(n == 1) { // 끝점에 도착했을 때			
			if(y == r && x == c) { // (r,c) 점이라면 ans에 저장
				ans = idx;
			}
			idx++;
			return;
		}
		
		// 시간 제한이 0.5초
		// 찾고자 하는 값 (r,c)가 현재 탐색하고 있는 범위 내에 없으면 스킵
		if(r > y + n || r < y || c > x + n || c < x) {
			idx += Math.pow(n, 2); // 현재 탐색하는 범위의 칸의 개수를 더해줌
			return;
		}
		
		// 4분면 분할
		int size = n/2;
		re(y, x, size);
		re(y, x+size, size);
		re(y+size, x, size);
		re(y+size, x+size, size);
	}
}
