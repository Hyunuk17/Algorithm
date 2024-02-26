import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17952_과제는_끝나지_않아 {

	public static void main(String[] args) throws IOException {
		/**
		 * 업무 평가 := BOJ 과제는 끝나지 않아!
		 * ------
		 * 
		 * 업무 수행중
		 * 1분기 끝날 때 까지
		 * 
		 * 가장 최근에 주어진 순서대로, 업무를 받으면 바로 시작
		 * 업무 도중 새 업무 추가 시, 새 업무 시작
		 * 새 업무가 끝나면 이전 업무를 이어서
		 * 
		 * 업무 평가 점수 구하기
		 * 
		 * 1 <= N <= 1,000,000
		 * 1 A T : 업무의 만점 A, T분 걸림
		 * 0 : 업무가 주어지지 않음
		 */

	
		System.setIn(new FileInputStream("2번_Input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		// Test case
		T = 2;
		for(int t=1;t<=T;t++) {
			// 입력
			StringBuilder sb = new StringBuilder();
			br.readLine(); // 주석 건너뛰기
			N = Integer.parseInt(br.readLine());
			total = 0;
			

			// 문제풀이
			queue = new ArrayDeque<>();
			for(int i=0;i<N;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int n = Integer.parseInt(st.nextToken());
				if(n == 1) {
					int A = Integer.parseInt(st.nextToken());
					int T = Integer.parseInt(st.nextToken());
					queue.addFirst(new Node(A, T));
				}
				
				doTask(); // 업무 수행
			}			
			
			// 출력
			sb.append("#").append(t).append(" ");
			sb.append(total);
			sb.append("\n");
			bw.write(sb.toString());
			bw.flush();
		}
		bw.close();
		br.close();
	}
	


	static int T;
	static int N;
	static int[] arr;
	static int total;
	static Deque<Node> queue;
	
	private static void doTask() {
		if(queue.isEmpty()) {
			return;
		}
		Node node = queue.poll();
		if((node.rTime - 1) == 0) {
			total += node.score;
		}
		else {
			queue.addFirst(new Node(node.score, node.rTime - 1));
		}
		
	}
	
	static class Node{
		int score;
		int rTime;
		
		public Node(int score, int rTime) {
			this.score = score;
			this.rTime = rTime;
		}		
	}
	
}
