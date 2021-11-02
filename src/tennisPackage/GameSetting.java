package tennisPackage;

import java.util.Scanner;

// GAME 세팅에 필요한 입력 관련 처리 클래스
public class GameSetting extends Element {

	public void createGame() {
		Scanner scanner = new Scanner(System.in);

		// 세트 입력
		System.out.print("> 세트 수를 입력하세요. (1. 3세트, 2. 5세트) ");
		int setN = scanner.nextInt();

		while (!(setN == 1 || setN == 2)) {
			System.out.println("입력이 틀렸습니다. 다시 입력하세요.");
			System.out.print(" > 다시 set 수를 입력하세요. ( 1번:(3세트), 2번:(5세트) ) ");
			setN = scanner.nextInt();
		}

		// 게임 mode 입력
		System.out.print("게임 방식을 입력하시오 (1.단식 2.복식) :  ");
		int mode = scanner.nextInt();
		int gender;
		while (!(mode == 1 || mode == 2)) {
			System.out.println("입력이 틀렸습니다.");
			System.out.print("다시 게임 방식을 입력하시오 (1.단식 2.복식) :  ");
			mode = scanner.nextInt();
		}

		// 이름을 저장할 배열의 크기
		String[] name = new String[mode * 2];

		// 단식을 선택한 경우 - 성별 선택
		if (mode == 1) {
			System.out.print("성별을 입력하세요. (1.남자 2.여자) :  ");
			gender = scanner.nextInt();
			while (!(gender == 1 || gender == 2)) {
				System.out.println("입력이 잘못 됐습니다.");
				System.out.print("다시 성별을 입력하세요. (1.남자 2.여자) :  ");
				gender = scanner.nextInt();
			}
		}
		// 복식을 선택한 경우 - 성별 선택
		else {

			System.out.print("구성원 성별을 입력하시오 ( 1.남자-2명   2.여자-2명   3.혼성-남1여1 ) :  ");
			gender = scanner.nextInt();

			while (!(gender == 1 || gender == 2 || gender == 3)) {
				System.out.println("입력이 잘못 됐습니다.");
				System.out.print("다시 성별을 입력하시오 (1.남자 2.여자 3.혼성) :  ");
				gender = scanner.nextInt();
			}

		}

		if (setN == 2) {
			gender = 3;
		} else {
			gender = 2;
		}

		// 선수 이름 입력
		for (int i = 0; i < name.length; i++) {
			System.out.printf("[%d번] 선수 이름을 입력하세요 :  ", i + 1);
			name[i] = scanner.next();
		}

		this.setGender(gender);
		this.setMode(mode);
		this.setName(name);
		this.setSetN(setN);
		scanner.close();
	}

}