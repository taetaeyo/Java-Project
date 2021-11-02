package tennisPackage;

// main 메서드가 존재하는 Tennis 클래스
public class Tennis {

	public static void main(String[] args) {

		GameSetting gs = new GameSetting(); // 게임 초기 입력 객체 생성
		gs.createGame(); // 게임 초기 정보를 입력 받는다
		String[] names = gs.getName(); // 계수기에 이름 반영
		int gender = gs.getGender(); // 입력받은 성별 저장

		InitScore is = new InitScore(); // 테니스 게임 세팅을 위한 객체 생성
		is.nameSet(names); // 계수기에 이름 반영
		is.setScoreCheck(gender); // 테니스 게임 진행

		System.out.println("\t\t\t    ■ 총 게임 수 : " + is.getCountgame().get(is.getCountgame().size() - 1));
		System.out.println("\t\t\t    ※ 게임 종료 ※");
	}
}
