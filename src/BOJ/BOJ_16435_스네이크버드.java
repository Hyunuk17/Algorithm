package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_16435_스네이크버드 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 16435. 스네이크버드
		 * -------------------
		 * 
		 * 뱀과 새의 모습을 닮은 생명체
		 * 주요 먹이는 과일, 과일 하나를 먹으면 길이가 1만큼 증가
		 * 
		 * i번째 과일은 지상으로부터 h_i만 큼 떨어져있다.
		 * 자신의 길이보다 작거나 같은 높이에 있는 과일들을 먹을 수 있음
		 * 
		 * 과일을 먹어 늘릴 수 있는 최대 길이 구하기
		 *
		 * 스네이크버드의 처음 길이 : 1 <= L <= 10,000 
		 * 과일의 개수 : 1 <= N <= 1,000
		 * 1 <= h_i <= 10,000
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken()); // 과일들의 높이
		}
		
		// 문제풀이
		Arrays.sort(arr); // 정렬 : 과일을 먹는 순서에는 상관이 없으므로
		for(int i=0;i<N;i++) { // 가장 작은 과일부터 먹기
			if(L < arr[i]) { // 과일이 높아 더 이상 먹을 수 없다면 종료
				break;
			}
			L++; // 과일을 먹었다면 스네이크버드의 길이 증가
		}
		
		// 출력
		bw.write(L+"");
		bw.flush();
		
		bw.close();
		
		br.close();
	}

	static int N;
	static int L;
	static int[] arr;
}
