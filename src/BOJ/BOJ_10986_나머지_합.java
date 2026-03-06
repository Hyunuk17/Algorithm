import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    /*
     * BOJ 10986. 나머지 합
     * -------------------
     * 
     * [문제 설명]
     * 수열 A
     * - N개
     * 
     * 구간
     * - 연속된 부분 구간의 합이 M으로 나누어 떨어짐 : sum % M == 0 
     * 
     * [입력]
     * N : 수의 개수
     * M : 나누어 떨어지는 수
 	 *  
     * [출력]
     * 연속된 부분 구간의 합이 M으로 나누어 떨어지는 구간의 개수
	 *
     * [제한사항]
     * 1 <= N <= 10^6 
     * 2 <= M <= 10^3
     * 0 <= A[i] <= 10^9
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

        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }   

        // 문제풀이
        /*
            long 타입
            - A[i] <= 10^9 
            - N <= 10^6

            구간 합
            - 1. (i, j)를 선택, 구간 합 구하기 : O(N^2) * O(N) => TLE
            - 2. 누적합, (i, j) 선택 : O(N) * O(N^2) => TLE
            - 3. 누적합, Sliding Window? : O(N) * O(N) => TLE
            - 4. 누적합, ?? : O(N) * O(?) < logn 
            
            M으로 나누어 떨어지는 구간
            - 1. prefixSum[i] % M == 0 
            - 2. (prefixSum[j] - prefixSum[i]) % M == 0
            => M으로 나눈 나머지가 같은(ex, 1, 2, ...) 누적합 간의 빼기 연산은 항상 0이다
            => M으로 나눈 나머지로 분류한 누적합 묶음들에서, 2개씩을 묶는 경우의 수 : 조합
            
            시간복잡도
            - 누적합 : O(N)
            - 조합 : M * O(k_C_2)
        */
        
        prefixSum = new long[N];
        modCnt = new int[M]; // 나머지를 가지는 누적합들의 개수를 저장하는 배열

        for(int i=0;i<N;i++) {
            if(i == 0) {
                prefixSum[0] = A[0];
            }
            else {
                prefixSum[i] = prefixSum[i-1] + A[i];
            }

            int index = (int)(prefixSum[i] % M);
            modCnt[index]++;
        }
        
        cnt = modCnt[0]; // 나머지가 이미 0인 누적합들
        for (int i=0;i<M;i++) {
            cnt += combination(modCnt[i]); // 누적합들의 조합으로 나머지가 0인 구간의 개수 구하기
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
    static int[] A;
    static long[] prefixSum;
    static int[] modCnt;
    static long cnt;

    static long combination(long num) {
        long count = 0;
        
        if(num >=2 ) {
            count = (num * (num - 1)) / 2; // num_C_2
        }

        return count;
    }
}