package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_1244_스위치켜고끄기 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		/** 백준 1244. 스위치 켜고 끄기
		 * 
		 * 1부터 연속적으로 번호가 붙은 스위치들
		 * 켜져(1) 있거나 꺼져(0) 있음
		 * 
		 * 학생에게 수 배부
		 * 남학생(1) : 스위치 번호가 자기가 받은 수의 배수이면 상태 변경
		 * 여학생(2) : 받은 수와 같은 번호 중심, 좌우로 대칭이면서 가장 많은 스위치 포함, 전부 상태 변경
		 * 
		 * 스위치들의 마지막 상태 출력
		 * 
		 * 스위치 개수 : 1 <= N <= 100
		 * 스위치 상태 : arr[N]
		 * 학생 수 : 1 <= M <= 100
		 * 학생이 받은 수 : student[M][2] {성별, 받은 수}
		 */
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// 입력
		N = Integer.parseInt(br.readLine());
		arr = new int[N+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		M = Integer.parseInt(br.readLine());
		student = new int[M][2];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			student[i][0] = Integer.parseInt(st.nextToken());
			student[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		// 문제풀이
		
		for(int i=0;i<M;i++) {
			// 남학생인 경우
			if(student[i][0] == 1) {
				// 받은 수의 배수를 찾아
				for(int j=student[i][1];j<=N;j+=student[i][1]) {
					// 상태를 변경
					swap(j);
				}
			}
			// 여학생인 경우
			else if(student[i][0] == 2) {
				// 같은 번호 중심으로 좌우 대칭
				swap(student[i][1]);
				select(student[i][1]);
			}
		}
		
		// 출력 
		for(int i=1;i<=N;i++) {
			bw.write(arr[i] +" ");
			// 20개씩 끊기
			if(i % 20 == 0) {
				bw.write("\n");
			}
		}
		bw.flush();
	}

	static int N;
	static int M;
	static int[] arr;
	static int[][] student;
	
	// 스위치의 상태 변경
	static void swap(int index) {
		if(arr[index] == 0) {
			arr[index] = 1;
		}
		else if(arr[index] == 1) {
			arr[index] = 0;
		}
	}
	
	// 좌우를 선택하여 비교하교 변경
	static void select(int index) {
		// +-할 범위
		int num = 1;
		while(true) {
			// 범위를 넘어서는 경우
			if(index - num <= 0 || index + num > N)  {
				break;
			}
			// 좌우가 일치하지 않는 경우
			if(arr[index - num] != arr[index + num]) {
				break;
			}
			// 좌우가 일치하는 경우
			else {
				swap(index - num);
				swap(index + num);
			}
			num++;
		}
	}
}
