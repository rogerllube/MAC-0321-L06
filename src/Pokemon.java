import java.util.Scanner;

public class Pokemon {
	
	private String name;
	private int type1, type2;
	private Move[] move = new Move[5];
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
	
	public void setMove(Move a, Move b, Move c, Move d) {
		move[1] = a;
		move[2] = b;
		move[3] = c;
		move[4] = d;
	}
	
	public String getName() {
		return name;
	}
	
	public int getType1() {
		return type1;
	}
	
	public int getType2() {
		return type2;
	}
	
	public int getDex() {
		return dexNumber;
	}
	
	public int getHp() {
		return hp;
	}
	
	public int getHpMAX() {
		return hpMAX;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getDefense() {
		return defense;
	}
	
	public int getSpAttack() {
		return spAttack;
	}
	
	public int getSpDefense() {
		return spDefense;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void setHp(int life) {
		this.hp = life;
	}
	
	public Move getMove(int num) {
		return move[num];
	}
	public Move chooseAtk(Scanner scanner, Trainer t) {
		int esc;
		getMoveList();
		System.out.println(t.getName() +" escolha seu ataque");
		esc = scanner.nextInt();
		while(esc < 1 || esc > 4) {
			System.out.println("Numero invalido, selecione outro");
		}
		return move[esc];
	}

	private void getMoveList() {
		System.out.println("Digite [1] para utilizar: " + move[1].getName() + System.lineSeparator() + "Digite [2] para utilizar: " + move[2].getName() + System.lineSeparator() + "Digite [3] para utilizar: " + move[3].getName() + System.lineSeparator() + "Digite [4] para utilizar: " + move[4].getName() + System.lineSeparator());
	}
}
