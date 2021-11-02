package tennisPackage;

// 게임에 필요한 요소 추상 클래스
// 이름, 성별, 단식 복식, 세트 선언
public abstract class Element {

	private String[] name;
	private int gender;
	private int mode;
	private int setN;

	public Element() {
	}

	public Element(String[] name, int gender, int mode) {
		this.name = name;
		this.gender = gender;
		this.mode = mode;
	}

	public String[] getName() {
		return name;
	}

	public void setName(String[] name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getSetN() {
		return setN;
	}

	public void setSetN(int setN) {
		this.setN = setN;
	}

}
