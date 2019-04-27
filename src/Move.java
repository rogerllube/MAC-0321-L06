
public class Move {
	private String name;
	private int type;
	private int damage;
	private int physical;
	
	public Move (String nome, int tipo, int damage, int physical) {
		type = tipo;
		this.damage = damage;
		this.physical = physical;
		name = nome;
	}
	
	public String getName() {
		return name;
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
