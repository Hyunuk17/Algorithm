package BOJ;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_21758_꿀_따기 {

	public static void main(String[] args) throws IOException {
		/**
		 * BOJ 21758. 꿀 따기
		 * ----------------
		 * 
		 * N개 장소(배열)
		 * 두마리 벌(회색)
		 * 벌통(진한 회색)
		 * 
		 * 지나가는 모든 칸에서 꿀을 딴다
		 * 두 마리가 모두 지나간 장소에서는 두 마리 모두 표시된 만끔 딴다
		 * 벌이 시작한 장소에서는 어떤 벌도 딸 수 없다
		 * 
		 * 최대의  꿀의 양 구하기
		 * 
		 * 3 <= N <= 100,000
		 * 1 <= arr[i] <= 10,000
		 * 
		 */

		// 입력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int total = 0;
		int m = 0;
		for(int i=0;i<N;i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
			m = Math.max(m, arr[i]);
		}
		
		// 문제풀이
		// N <100,000 -> 2중 for문 불가능
		// 1차원 배열 -> 한번의 탐색으로 답을 구할 수 있을까?
		// 전체 누적 합 : 10^5 * 10*4 < Integer
		
		// case 1 : 벌통이 오른쪽 끝에 있는 경우
		// 벌통과 벌 한 마리는 양쪽 끝
		// 꿀이 양이 최대가 되는 나머지 벌 한마리 위치 구하기
		
		// 누적합 구하기
		dp = new int[N];
		dp[0] = arr[0];
		for(int i=1;i<N;i++) {
			dp[i] = dp[i-1] + arr[i];
		}
		
		// 1~N-2까지 벌 위치 구하기
		for(int i=1;i<=N-2;i++) {
			int sum = 0;
			sum = dp[N-1] - arr[N-1]; // 누적합 - 벌 위치는 더하지 않기
			sum += dp[i];
			sum -= 2*arr[i];  // 다른 벌 위치 2번 빼기
			
			max = Math.max(max, sum); // max 계산
		}
		
		// case 2 : 벌통이 오른쪽
		// 누적합 구하기
		dp = new int[N];
		dp[N-1] = arr[N-1];
		for(int i=N-2;i>=0;i--) {
			dp[i] = dp[i+1] + arr[i];
		}

		// N-2~1까지 벌 위치 구하기
		for(int i=N-2; i>=1;i--) {
			int sum = 0;
			sum = dp[0] - arr[0]; // 누적합 - 벌 위치 더하지 않기
			sum += dp[i];
			sum -= 2*arr[i]; // 겹치는 벌 위치 2번 빼기
			
			max = Math.max(max, sum); // max 계산
		}

		// case 3 : 벌통이 가운데 있는 경우
		// 벌 두 마리는 양쪽 끝
		// 꿀 양의 최대가 되는 벌통 위치 구하기 : 가장 큰 arr[i] 값
		int sum = 0;
		
		// 0~N-1 + 가장 큰 값
		sum = total - arr[0] - arr[N-1] + m;
		max = Math.max(max, sum); 
		
		// 출력
		sb.append(max);
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
	
	static int N;
	static int[] arr;
	static int[] dp;
	static int max;
	
}
