import java.util.concurrent.ThreadLocalRandom;

public class Resolve {
	public void attack(Action a, Trainer p) {
		int damage;
		int hp;
		Trainer ta = a.getTrainer();
		int activeA = ta.getActive();
		Pokemon pokeA = ta.getPoke(activeA);
		Trainer td = p;
		int activeD = td.getActive();
		Pokemon pokeD = td.getPoke(activeD);
		
		damage = Attack.Dano(pokeA.getMove(a.getSubtype()), pokeA, pokeD);
		hp = pokeD.getHp();
		if(hp - damage <= 0) {
			pokeD.setHp(0);
			System.out.println(pokeA+ " atacou " +pokeD+ " com " +pokeA.getMove(a.getSubtype())+ "!"+ System.lineSeparator() +pokeD+ " não tem mais hp");
			System.out.println("Escolha outro pokémon");
			td.setLeft();
			
			if(td.getLeft() != 0) {
				td.getPokeList();
				td.setAtivo(ThreadLocalRandom.current().nextInt(1, 6+1));
			}
			else {
				td.setOver(true);
			}
		}
		pokeD.setHp(hp - damage);
		System.out.println(pokeA+ " atacou " +pokeD+ " com " +pokeA.getMove(a.getSubtype())+ "!"+ System.lineSeparator() +pokeD+ " tem " +hp+ " de vida restando");
	}
	public void useItem(Action a) {
		Trainer t = a.getTrainer();
		if(t.getItem() > 0) {
		
		}
	}
	public void switchPoke(Action a) {
		
	}
	
	public void flee(Action a) {
		Trainer t = a.getTrainer();
		t.setOver(true);
	}
}
