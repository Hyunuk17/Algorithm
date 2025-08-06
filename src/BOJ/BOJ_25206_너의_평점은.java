import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    /*
     * BOJ 25206. 너의 평점은
     * ---------------------
     * 
     * [문제 설명]
     * 전공평점 계산
     * - (전공과목별 (학점 x 과목평균)의 합) / (학점의 총합)
     * - P인 과목은 제외 (F는 반영)
     * 
     * [입력]
     * "과목명 학점 등급"
 	 *  
     * [출력]
     * 전공평점 출력
     * - 정답과의 절대오차 또는 상대오차가 10^-4 이하이면 정답으로 인정
	 *
     * [제한사항]
     * 1 <= 과목명.length() <= 50
     * 학점 : 1.0, 2.0, 3.0, 4.0
     * 등급 : 4.5, 4.0, 3.5, 3.0, 2.5, 2.0, 1.5, 1.0, 0
	 *
	 */
    public static void main(String[] args) throws IOException {
        // 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        int N = 20;
        String[][] subject = new String[N][3];
        for(int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            String title = st.nextToken();
            String credit = st.nextToken();
            String grade = st.nextToken();

            subject[i][0] = title;
            subject[i][1] = credit;
            subject[i][2] = grade;
        }

        // 문제풀이
        /**/

        Double creditSum = 0.0;
        Double gradeSum = 0.0;
        for(int i=0;i<N;i++) {
            
            double credit = Double.parseDouble(subject[i][1]);
            String grade = subject[i][2];
            if(grade.equals("P")) {
                continue;
            }
            else if(grade.equals("A+")) {
                gradeSum += (4.5 * credit);
            }
            else if(grade.equals("A0")) {
                gradeSum += (4.0 * credit);
            }
            else if(grade.equals("B+")) {
                gradeSum += (3.5 * credit);
            }
            else if(grade.equals("B0")) {
                gradeSum += (3.0 * credit);
            }
            else if(grade.equals("C+")) {
                gradeSum += (2.5 * credit);
            }
            else if(grade.equals("C0")) {
                gradeSum += (2.0 * credit);
            }
            else if(grade.equals("D+")) {
                gradeSum += (1.5 * credit);
            }
            else if(grade.equals("D0")) {
                gradeSum += (1.0 * credit);
            }
            else if(grade.equals("F")) {
                gradeSum += (0.0 * credit);
            }

            creditSum += credit;
        }

        sb.append(gradeSum / creditSum);

        
        
        // 출력
        bw.write(sb.toString());
        bw.flush();

        bw.close();
        br.close();
    }
}