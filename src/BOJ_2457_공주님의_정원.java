import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2457_공주님의_정원 {

	public static void main(String[] args) throws IOException {
		/**
		 * 
		 * 프리랜서 := BOJ 2457. 공주님의 정원
		 * -----
		 * 
		 * N개의 프로젝트
		 * 같은 해에 시작해서 같은 해에 마무리
		 * [)
		 * 
		 * 조건을 만족하는 프로젝트 선택
		 * 1) 3.1 ~ 11.30까지 매일 한 가지 이상의 프로젝트에 참여
		 * 2) 프로젝트의 수를 가능한 적게
		 * 
		 * 1 <= N <= 100,000
		 * 
		 */
		
		/**
		 * TC
		 * 4
1 1 3 3
2 2 2 3
3 3 9 9
9 9 12 12
		 */

		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
				
		StringBuilder sb = new StringBuilder();
			
		// 입력
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		
		arr = new int[N][4];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int sMonth = Integer.parseInt(st.nextToken());
			int sDay = Integer.parseInt(st.nextToken());
			int eMonth = Integer.parseInt(st.nextToken());
			int eDay = Integer.parseInt(st.nextToken());
			arr[i][0] = sMonth;
			arr[i][1] = sDay;
			arr[i][2] = eMonth;
			arr[i][3] = eDay;
		}
		
		// 문제풀이
		Arrays.sort(arr, (o1, o2) -> {
			if(o1[0] == o2[0] && o1[1] == o2[1] && o1[2] == o2[2]) {
				return o1[3] - o2[3];
			}
			if(o1[0] == o2[0] && o1[1] == o2[1]) {
				return o1[2] - o2[2];
			}
			
			if(o1[0] == o2[0]) {
				return o1[1] - o2[1]; // 같으면 시작 일
			}
			return o1[0] - o2[0]; // 시작 월
		});
		
//		for(int[] i : arr) {
//			System.out.println(Arrays.toString(i));
//		}
//		System.out.println();
		
		check();
		
		// 출력
		if(min == Integer.MAX_VALUE) {
			min = 0;
		}
		sb.append(min);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[][] arr;
	static int min;
	
	static void check() {
		int cnt = 0;
		int sm = arr[0][0];
		int sd = arr[0][1];
		int em = arr[0][2];
		int ed = arr[0][3];		
		
		for(int i=0;i<N;i++) {
			boolean change = false;
//			System.out.println(Arrays.toString(arr[i]));

			System.out.println("start: " + Arrays.toString(arr[i]));
			int nem = arr[i][2];
			int ned = arr[i][3];
//			System.out.println("nem : " + nem + ", end : " + ned);
			for(int j=i;j<N;j++) { // 현재부터 반복
				// 끝 < 시작이면 볼 필요 없음
				if(em < arr[j][0] ) { 
					break;
				}
				else if(em == arr[j][0]) {
					if(ed < arr[j][1]) {
						break;
					}
				}

				// 시작 < 끝이면 갱신
				
				if(arr[j][0] < em) {
					nem = arr[j][2];
					ned = arr[j][3];
					i = j;
					change = true;
					System.out.println("ch" + Arrays.toString(arr[j]));
				}
				else if(arr[j][0] == em) {
					if(arr[j][1] <= ed) {
						i = j;
						nem = arr[j][2];
						ned = arr[j][3];
						change = true;
						System.out.println("ch" + Arrays.toString(arr[j]));
					}
				}
//				if(change== true) {
//					System.out.println(Arrays.toString(arr[j]));
//				}
				
 			}
			
			if(change == true) {
				em = nem;
				ed = ned;
				cnt++;
			}
			else {
				break;
			}
			
			// 종료가 안되고 있음
			System.out.println(cnt);
			System.out.println("result: " + sm + " " + sd + " " + em + " " +ed);
			System.out.println();
			
		
			// 되는지 체크
			boolean flag = true;
			if(sm > 3) {
				flag = false;
			}
			else if(sm == 3) {
				if(sd > 1) {
					flag = false;
				}
			}
			
			if(em < 11) {
				flag = false;
			}
			else if(em == 11) {
				if(ed <= 30) { // [)
					flag = false;
				}
			}
			
			if(flag == true) {	
//				break;
				min = Math.min(min, cnt);
			}
		}
//		System.out.println();
//		

	}

}
