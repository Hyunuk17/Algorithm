import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    /*
     * BOJ 34049. 배열 나누기
     * ---------------------
     * 
     * [문제 설명]
     * 정수 배열
     * - 길이 N
     * - 여러 연속한 부분으로 분할
     * 
     * 분할 조건
     * - 부분 배열의 길이 L
     * - 1~i번 원소의 합은 0 이상
     * 
     * [입력]
     * N : 원소의 개수
     * A[] : 원소
 	 *  
     * [출력]
     * 나뉜 부분 배열의 최대 개수
     * 없다면 -1
	 *
     * [제한사항]
     * 1 <= N <= 200,000
     * -200,000 <= A[i] <= 200,000
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(br.readLine());
        A = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }


        // 문제풀이
        /*
            부분 배열의 원소 누적합이 항상 0 이상이 되도록 자르기
            - 앞에서부터 보면서 누적합 계산
            - 누적합이 음수가 되기 전까지가 하나의 부분 배열?
                - 원소를 더했을 때, 누적합이 음수가 된다면
                - 

            최대 개수 구하기
            - 가능한 한 많이 잘라야 함

            시간복잡도
            - N <= 200,000
            - O(nlogn) : 2 * 10^5 * (2*5) = 2 * 10^6
        */

        int cnt = 0;
        int pre = A[0];
        int[] prefixSum = new int[N+1];

        int tmpSum = 0;
        for(int i=1;i<=N;i++) { // 원소 순회
            tmpSum += A[i];
            if(tmpSum < 0) { // 현재 원소를 누적했는데 음수
                cnt = -1; // 불가능
                break;
            }
            else { // 현재 원소를 누적한 값이, 양수 or 0
                if () { // 이전 누적값보다 증가했다면,
                    
                }
                cnt++; // 현재 원소를 포함해서 배열 자르기
                tmpSum = 0;
            }
        }



        sb.append(cnt);
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }


    static int N;
    static int[] A;
    static int max = Integer.MIN_VALUE;
}