package BOJ;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_15961_회전초밥 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 15961. 회전초밥
		 * ----------------
		 * 
		 * 회전 초밥집 매상 올리기
		 * 1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹는다면 할인
		 * 2. 각 고객에게 초밥 종류 하나 쓰인 쿠폰 제공, 1번 행사 시 쿠폰 초반 하나 무료 제공
		 * - 현재 벨트에 없으면 만들어줌
		 * 
		 *  손님이 먹을 수 있는 초밥 가짓수의 최댓값
		 *  
		 *  벨트에 놓인 접시 수 N : 2 <= N <= 3,000,000
		 *  초잡 가짓수 d : 2 <= d <= 3,000
		 *  연속해서 먹는 접시의 수 K : 2 <= K <= 3,0000
		 *  쿠폰 번호 C : 1 <= C <= d
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 개수
		d = Integer.parseInt(st.nextToken()); // 초밥 가지 수
		k = Integer.parseInt(st.nextToken()); // 한번에 먹는 개수
		c = Integer.parseInt(st.nextToken()); // 쿠폰
		
		arr = new int[N+k];
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}
		for(int i=N;i<N+k;i++) { // 연결
			arr[i] = arr[i-N];
		}
		
		// 문제풀이
		int start = 0; // 시작 번호
		int end = k-1; // 끝번호
		int cnt = 0;
		count = new int[d+1]; // 먹은 초밥 개수
		for(int i=start;i<=end;i++) { // 처음 윈도우 구하기
			count[arr[i]]++; // 먹은 초밥 count+1
			if(count[arr[i]] == 1) { // 한개만 먹은 상태라면
				cnt++; // 먹은 가지 수 +1
			}
		}
		
		while(start < N) { // N <= 3,000,000			
			int result = cnt; // 결과
			if(count[c] == 0) { // 쿠폰을 쓸 수 있다면
				result++; // 결과 +1
			}
			
			max = Math.max(max, result); // 최대 값 구하기
			
			start++; // start 인덱스 +1
			count[arr[start-1]]--; // 먹은거 취소
			if(count[arr[start-1]] == 0) { // 1개도 안먹은 상태가 되었다면
				cnt--; // 먹은 가지 수 -1
			}
			
			end++; // end 인덱스 +1
			count[arr[end]]++; // 새로 먹기
			if(count[arr[end]] == 1) { // 처음 먹는 종류라면
				cnt++; // 먹은 가지 수 +1
			}
		}
		
		// 출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int d;
	static int k;
	static int c;
	static int[] arr;
	static int[] count;
	static int max = 0;
	
}
