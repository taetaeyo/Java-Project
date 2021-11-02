package tennisPackage;

import java.io.FileWriter;
import java.io.PrintWriter;

// display 관련 클래스 
public class DisplayScore {

	private String[][] scoreBd = newScoreBoard(); // 계수기, 클래스 전반적으로 쓰이기 때문에 전역변수로 선언
	private int temp; // 세트가 종료될 때 다음 세트 기록을 위해 계수기의 주소를 증가시키기 위한 변수

	// 계수기 생성 메소드
	private String[][] newScoreBoard() {

		scoreBd = new String[3][8];
		String[] menu = { "이름", "포인트", "1세트", "2세트", "3세트", "4세트", "5세트", "매치" };

		// 배열의 0번째 행 - 카테고리로 초기화
		for (int i = 0; i < menu.length; i++) {
			scoreBd[0][i] = menu[i];
		}

		// menu 공간을 뺀 나머지 부분을 0으로 초기화
		for (int i = 1; i < scoreBd.length; i++) {
			for (int j = 1; j < scoreBd[i].length; j++) {
				scoreBd[i][j] = "0";
			}
		}
		// 계수기 생성
		return scoreBd;
	}

	// 계수기에 선수 이름 반영하는 메소드
	public void setScoreBoardName(String[] names) {

		switch (names.length) {
		case 2: // 단식 경기
			scoreBd[1][0] = names[0];
			scoreBd[2][0] = names[1];
			break;

		case 4: // 복식 경기
			scoreBd[1][0] = names[0].charAt(0) + "&" + names[1].charAt(0);
			scoreBd[2][0] = names[2].charAt(0) + "&" + names[3].charAt(0);
			break;
		}
	}

	// 포인트를 계수기에 반영 및 출력하는 메소드
	public void printScore(String score1, String score2) {

		scoreBd[1][1] = score1;
		scoreBd[2][1] = score2;
		dispScoreBoard();
	}

	// 게임스코어를 계수기에 반영 및 출력하는 메소드
	public void printPointScore(int a, String pointScore) {

		int i = temp;
		// 큰 보드에서 세트 칸에 점수 부여, 점수 주면서 다음 칸으로 넘어가는거
		if (a == 0)
			scoreBd[1][2 + i] = pointScore;
		else
			scoreBd[2][2 + i] = pointScore;

		// 두 선수의 점수 차
		int scoreGap = Math.abs(Integer.parseInt(scoreBd[1][2 + i]) - Integer.parseInt(scoreBd[2][2 + i]));

		// 다음 세트를 기록하는 위치로 이동하기 위해 temp 증가
		for (int j = 1; j < 3; j++) {
			// 2게임차 이상이면서 먼저 6게임을 딴 경우
			// 1게임 끝났을때 세트 수 변수 증가
			if (scoreBd[j][2 + i].contains("6") && scoreGap >= 2)
				temp++;
		}
		dispScoreBoard();
	}

	// 매치 메뉴 밑, 세트 스코어를 계수기에 반영 및 출력하는 메소드
	public void printSet(int a, String setScore) {

		if (a == 0)
			scoreBd[1][7] = setScore;
		else
			scoreBd[2][7] = setScore;

		dispScoreBoard();
		printWinnerName();
	}

	// Log 파일 생성 메소드
	public void newLogFile(String[][] scoreBd) {

		String fileName = System.getProperty("user.dir");
		fileName += "\\src\\tennisPackage\\tennisLogFile.txt";
		try (FileWriter fw = new FileWriter(fileName, true); PrintWriter pw = new PrintWriter(fw);) {

			pw.println("-".repeat(90));
			for (int i = 0; i < scoreBd.length; i++) {
				for (String line : scoreBd[i]) {
					pw.print(line + "\t");
				}
				pw.println();
				pw.println("-".repeat(90));
			}
			pw.println();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 계수기 출력 메소드
	public void dispScoreBoard() {
		System.out.println("■".repeat(31) + "테니스 계수기" + "■".repeat(31));
		System.out.println("=".repeat(74));

		for (int i = 0; i < scoreBd.length; i++) {
			for (int j = 0; j < scoreBd[i].length; j++) {
				System.out.printf("\t%s", scoreBd[i][j]);
			}
			System.out.println();
			if (i == 0)
				System.out.println("=".repeat(74));
		}
		System.out.println();

		// Log 파일에 기록
		newLogFile(scoreBd);
	} // dispScoreBoard()

	// 최종 우승자 출력
	public void printWinnerName() {
		if (Integer.parseInt(scoreBd[1][7]) > Integer.parseInt(scoreBd[2][7])) {
			System.out.printf("\t\t\t    ■ 최종 승자 : %s\n", scoreBd[1][0]);
		} else if (Integer.parseInt(scoreBd[1][7]) < Integer.parseInt(scoreBd[2][7])) {
			System.out.printf("\t\t\t    ■ 최종 승자 : %s\n", scoreBd[2][0]);
		}
	} // printWinnerName()

}
