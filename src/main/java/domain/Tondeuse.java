package domain;

public class Tondeuse {
	private int posX;
	private int posY;
	private char orientation;
	
	private final char DROITE = 'D';
	private final char GAUCHE = 'G';
	private final char AVANCER = 'A';

	public Tondeuse() {
	};

	public Tondeuse(int posX, int posY, char orientation) {
		this.posX = posX;
		this.posY = posY;
		this.orientation = orientation;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public char getOrientation() {
		return orientation;
	}

	public void setOrientation(char orientation) {
		this.orientation = orientation;
	}

	public void start(String instructions, Pelouse pelouse) {
		char[] inst = instructions.toCharArray();
		for (char c : inst) {
			switch (c) {
			case DROITE:
				right();
				break;
			case GAUCHE:
				left();
				break;
			case AVANCER:
				goForward(pelouse.getLeftDown(), pelouse.getRightUp());
				break;
			default:
				System.out.println("invalid instruction");
				break;
			}
		}
		System.out.println(this);
	}


	private void goForward(Coordonees leftDown, Coordonees rightUp) {
		switch (orientation) {
		case Directions.NORTH:
			if(posY < rightUp.getY()) {
				posY++;				
			}
			break;
		case Directions.EAST:
			if(posX < rightUp.getY()) {				
				posX++;
			}
			break;
		case Directions.WEST:
			if(posX > leftDown.getX()) {				
				posX--;
			}
			break;
		case Directions.SOUTH:
			if(posY > leftDown.getY()) {				
				posY--;
			}
			break;

		default:
			break;
		}
	}

	private void right() {
		switch (orientation) {
		case Directions.NORTH:
			orientation = Directions.EAST;
			break;
		case Directions.EAST:
			orientation = Directions.SOUTH;
			break;
		case Directions.WEST:
			orientation = Directions.NORTH;
			break;
		case Directions.SOUTH:
			orientation = Directions.WEST;
			break;

		default:
			break;
		}
	}

	private void left() {
		switch (orientation) {
		case Directions.NORTH:
			orientation = Directions.WEST;
			break;
		case Directions.EAST:
			orientation = Directions.NORTH;
			break;
		case Directions.WEST:
			orientation = Directions.SOUTH;
			break;
		case Directions.SOUTH:	
			orientation = Directions.EAST;
			break;

		default:
			break;
		}
	}

	@Override
	public String toString() {
		return posX + " " + posY + " " + orientation;
	}

}
