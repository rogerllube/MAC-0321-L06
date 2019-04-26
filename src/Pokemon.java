public class Pokemon {
	
	private String name;
	private int type1, type2;
	private String[] move;
	private int dexNumber;
	private int hp,hpMAX;
	private int attack;
	private int defense;
	private int spAttack;
	private int spDefense;
	private int speed;
	
	public Pokemon(String name, int dexN, int type1, int type2, int hp, int atk, int def, int spatk, int spdef, int speed) {
		dexNumber = dexN;
		this.type1 = type1;
		this.type2 = type2;
		this.hp = hpMAX = hp;
		attack = atk;
		defense = def;
		spAttack = spatk;
		spDefense = spdef;
		this.speed = speed;
		this.name = name;
	}
}
