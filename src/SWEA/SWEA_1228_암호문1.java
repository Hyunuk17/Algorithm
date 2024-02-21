package SWEA;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class SWEA_1228_암호문1 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/**
		 * SWEA 1228. 암호문1
		 * ----------------
		 * 
		 * 0 ~ 999999 사이의 수를 나열하여 만든 암호문
		 * 
		 * 암호문을 특수 제작된 처리기로 수정
		 * I(삽입) x, y, s 
		 * 앞에서부터 x의 위치 바로 다음에 y개의 숫자를 삽입
		 * s는 덧붙일 숫자들
		 * 
		 * 수정된 결과의 처음 10개 숫자를 출력
		 * 
		 * 원본 암호문의 길이 N (10 <= N <= 20)
		 * 원본 암호문
		 * 명령어의 개수 (5 <= N <= 10)
		 * 명령어
		 * 
		 * 총 10개의 Test case
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		for(int t=1;t<=10;t++) {
			StringBuilder sb = new StringBuilder();
			// 1번째 줄
			N = Integer.parseInt(br.readLine());
			list = new ArrayList<>();
			
			// 2번째 줄
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0;i<N;i++) {
				list.add(Integer.parseInt(st.nextToken()));
			}
			
			// 3번째 줄
			M = Integer.parseInt(br.readLine());
			
			// 4번째 줄
			st = new StringTokenizer(br.readLine());
			for(int i=0;i<M;i++) {
				st.nextToken(); // I : 필요없는 입력 넘기기
				int x = Integer.parseInt(st.nextToken()); // X 
				int y = Integer.parseInt(st.nextToken()); // Y
				// X부터 Y개 삽입
				for(int j=0;j<y;j++) {
					int num = Integer.parseInt(st.nextToken()); // 삽입할 번호
					list.add(x + j, num); // X부터 삽입할 숫자의 위치
				}
			}
			
			// 출력
			sb.append("#").append(t).append(" ");
			for(int i=0;i<10;i++) {
				sb.append(list.get(i)).append(" ");
			}
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int M;
	static List<Integer> list;
	static int[] pw;
}
