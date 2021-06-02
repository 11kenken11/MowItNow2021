package domain;

public class Pelouse {

	private Coordonees rightUp;
	private Coordonees leftDown;
	
	
	public Pelouse() {
		this.leftDown = new Coordonees(0, 0);
	};
	
	public Pelouse(Coordonees rightUp) {
		this.rightUp = rightUp;
		this.leftDown = new Coordonees(0, 0);
	}

	public Coordonees getRightUp() {
		return rightUp;
	}

	public void setRightUp(Coordonees rightUp) {
		this.rightUp = rightUp;
	}

	public Coordonees getLeftDown() {
		return leftDown;
	}

	public void setLeftDown(Coordonees leftDown) {
		this.leftDown = leftDown;
	}

	@Override
	public String toString() {
		return "Pelouse [rightUp=" + rightUp + ", leftDown=" + leftDown + "]";
	}
	
}
