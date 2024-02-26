import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_14865_곡선_자르기 {

	public static void main(String[] args) throws IOException {
		/**
		 * 산악 지도 그리기 := BOJ 곡선 자르기
		 * -----------
		 * 
		 * 직사각형 캔버스
		 * 중앙 원점(0, 0), (y, x)
		 * Cycle이 있는 그래프
		 * 
		 * x축을 기준으로 양분
		 * x축 위 부분으로 봉우리 판단
		 * 
		 * 포함되지 않는 봉우리 개수
		 * 포함하지 않는 봉우리 개수
		 * 출력
		 * 
		 * 4 <= N <= 10^6
		 * -10^9 <= x, y <= 10^9 : int
		 * y != 0
		 */

		
//		System.setIn(new FileInputStream("3번_Input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test case
//		T = 2;
//		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
//			br.readLine(); // 주석 건너뛰기
			N = Integer.parseInt(br.readLine());
			
			int cnt = 0; // 전체 봉우리
			int cnt1 = 0; // 포함되지 않는 봉우리
			int cnt2 = 0; // 포함하는 봉우리
			int cnt3 = 0; // 포함되는 봉우리
			int cnt4 = 0; // 포함하는 봉우리
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			int preX = Integer.parseInt(st.nextToken());
			int preY = Integer.parseInt(st.nextToken());
			
			int d = 5; // {0, 1, 2, 3}; // 상 하 좌 우 
			int preD = 5;
			boolean contain = false;
			
			arr = new int[N][2];
			for(int i=0;i<N-1;i++) {
				st = new StringTokenizer(br.readLine());
				arr[i][0] = Integer.parseInt(st.nextToken());
				arr[i][1] = Integer.parseInt(st.nextToken());
			}
			arr[N-1][0] = preX;
			arr[N-1][1] = preY;
			
			for(int i=0;i<N;i++) {
				
				int x = arr[i][0];
				int y = arr[i][1];

//				현재 진행 방향
				// 위로 간다면 방향 바꾸기
				if(preY < y) {
					d = 0;
				}
				// 아래로 간다면 방향 바꾸기
				if(preY > y) {
					d = 1;
				}
				// 왼쪽으로 간다면 방향 바꾸기
				if(preX < x) {
					d = 2; // 좌
				}
				// 오른쪽으로 간다면 방향 바꾸기
				if(preX > x ) { 
					d = 3; // 우
				}
				
				// y값이 - > +로 변환 시 봉우리 개수 +1
				if(preY < 0 && y > 0) {
					cnt++;
				}
				
				// x값이 우 -> 좌로 간다면 포함되지 않음
				if(y > 0 && preX < x) { // ->
					if(preD != 2 && d == 2) { // 처음 왼쪽으로 왔을 때만
						cnt2++;
					}
					
					// 포함
					if(contain == true) {
						contain = false;
					}
				}

				
				// x값이 좌 -> 우로 간다면 포함됨
				if(y > 0 && x < preX) { // <-
					if(preD != 3 && d == 3) { // 처음 오른쪽으로 왔을 때만
						cnt3++;
						
						// 이전에 왼쪽에서 오다가 오른쪽으로 처음 바뀌면 1개 포함하는 거 있음
						if(contain == false) {
							contain = true;
							cnt4++;
						}
					}
				}

				
				preY = y;
				preX = x;
				preD = d;
			}
			
			
			// 출력
//			sb.append("#").append(t).append(" ");
			// 포함되지 않는 : 전체 - 포함되는
			// 포함하지 않는	: 전체 - 포함하는  
			sb.append(cnt - cnt3).append(" " ).append(cnt - cnt4);
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
//		}
		bw.close();
		br.close();
	}

	static int T;
	static int N;
	static int[][] arr;
	
	static class Node {
		int y;
		int x;
		
		public Node(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	
}
