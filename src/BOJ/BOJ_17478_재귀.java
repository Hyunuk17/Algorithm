package BOJ;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_17478_재귀 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		sb = new StringBuilder();

		// 재귀를 반복할 횟수 받기
		N = Integer.parseInt(br.readLine());

		// 첫 문장
		sb.append("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.\n");
		// 재귀 시작
		recur(0, "");

		// 출력
		bw.write(sb.toString());
		bw.flush();
	}

	// 횟수 입력
	static int N;
	// 출력하기 위한 SB 생성
	static StringBuilder sb;

	// 재귀함수
	static void recur(int depth, String underBar) {
		// 최대 재귀를 반복하여 마지막 재귀라면
		if (depth == N) {
			sb.append(underBar + "\"재귀함수가 뭔가요?\"\n");
			sb.append(underBar + "\"재귀함수는 자기 자신을 호출하는 함수라네\"\n");
			sb.append(underBar + "라고 답변하였지.\n");
			return;
		}
		
		
		sb.append(underBar + "\"재귀함수가 뭔가요?\"\n");
		sb.append(underBar + "\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n");
		sb.append(underBar + "마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n");
		sb.append(underBar + "그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n");

		recur(depth + 1, underBar + "____");

		sb.append(underBar + "라고 답변하였지.\n");
	}
}
