package tennisPackage;

import java.util.ArrayList;
import java.util.Random;

public class InitScore extends Element {

	private int count = 1;// 현재 게임 몇번째인지
	private ArrayList<String> countgame = new ArrayList<String>(); // 몇번째 게임인지

	// 계수기 출력, Log파일 생성, gameSetting 클래스로부터 받은 이름
	// 넘겨주기 위한 객체 생성
	DisplayScore sb = new DisplayScore();

	// 포인트, 게임, 세트를 저장하고 ScoreBd 클래스로 넘겨주기 위한 배열
	// 점수만 받는 작은 배열, 우선은 3세트
	int[][] board = new int[2][3]; // [0][0] :포인트, [0][1] : 세트 점수만 있는 테이블

	Random rnd = new Random();
	int idx = 0;

	// 세트 스코어를 확인하는 메소드
	public void setScoreCheck(int a) {
		while (board[0][2] < a && board[1][2] < a) {
			gameScoreCheck();
		}
	}

	// 계수기에 선수 이름을 출력하기 위한 메소드
	public void nameSet(String[] names) {
		sb.setScoreBoardName(names);
	}

	// 게임 스코어를 확인하는 메소드 // 맨처음 세팅
	public void gameScoreCheck() {
		while (!(board[idx][1] - board[1 - idx][1] > 1 && board[idx][1] > 5)) {
			gameProgress();
		}
		// 게임 포인트 초기화
		board[0][1] = board[1][1] = 0;
		board[idx][2]++;
		sb.printSet(idx, Integer.toString(board[idx][2]));
	}

	// 포인트 생성 메소드
	public void gameProgress() {
		String[] score = { "0", "15", "30", "40", "win" };

		while (!(board[idx][0] - board[1 - idx][0] > 1 && board[idx][0] > 3)) {
			idx = rnd.nextInt(2); // 난수 0~1
			board[idx][0]++; // idx, 0이면 위팀 이겼으면 1증가

			// board가 2이면 score의 "30" 해당
			sb.printScore(score[board[0][0]], score[board[1][0]]);

			// 최종 게임 횟수를 카운트 하기위한 조건문
			if (score[board[0][0]] == "win" || score[board[1][0]] == "win") {
				countgame.add(count++ + "게임");
			}

			// 듀스 상황시 실행
			if (board[0][0] == 3 && board[1][0] == 3)
				idx = pointDeuce();
		} // while

		board[0][0] = board[1][0] = 0; // 포인트를 0으로 다시 초기화
		board[idx][1]++; // 옆에 1세트 열로 넘어간다.
		// 게임스코어를 계수기에 반영 및 출력
		sb.printPointScore(idx, Integer.toString(board[idx][1]));

	}

	// 듀스 상황에서 포인트를 생성하는 메소드
	public int pointDeuce() {
		String[] score = { "0", "15", "30", "40", "40A", "win" };

		while (!(board[idx][0] == 5 && board[1 - idx][0] == 3)) {
			idx = rnd.nextInt(2);
			board[idx][0]++;

			//// 40A -> 40로 변경
			if (board[1 - idx][0] == 4)
				board[1 - idx][0]--;
			sb.printScore(score[board[0][0]], score[board[1][0]]);

		}
		return idx;
	}

	public ArrayList<String> getCountgame() {
		return countgame;
	}

}
