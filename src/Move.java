
public class Move {
	private int type;
	private int damage;
	private int physical;
	
	public Move (int tipo, int damage, int physical) {
		type = tipo;
		this.damage = damage;
		this.physical = physical;
	}
	
	public int getType() {
		return type;
	}
	
	public int getDamege() {
		return damage;
	}
	
	public int getPhysical() {
		return physical;
	}
}
