
public class Move {
	private String name;
	private int type;
	private int damage;
	private int physical;
	private int priority;
	
	public Move (String nome, int tipo, int damage, int physical, int prio) {
		type = tipo;
		this.damage = damage;
		this.physical = physical;
		name = nome;
		priority = prio;
	}
	
	public String getName() {
		return name;
	}
	
	public int getType() {
		return type;
	}
	
	public int getDamage() {
		return damage;
	}
	
	public int getPhysical() {
		return physical;
	}
	public int getPriority() {
		return priority;
	}
}
