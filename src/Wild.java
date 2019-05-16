import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Wild extends Controller{
	private Trainer p, wildP;
	private int turn = 1;
	private int i, j;
	private Action a1 = new Action(), a2 = new Action();
	private Mapa map = new Mapa();
	Scanner scanner;
	
	public Wild(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public class Begin extends Event{
		public void action() {
			addEvent(new CreateTrainer());
		}
	}
	
	private class CreateTrainer extends Event{
		public void action() {
			System.out.println("Treinador, digite seu nome");
			p = new Trainer(scanner);
			addEvent(new Initial());
		}
	}
	
	private class Initial extends Event{
		public void action() {
			System.out.println("Pokemon disponiveis:");
			Lista.listar();
			p.Inicial(scanner);
			addEvent(new Start());
		}
	}
	
	private class Start extends Event{
		public void action() {
			i = ThreadLocalRandom.current().nextInt(0, 7);
			j = ThreadLocalRandom.current().nextInt(0, 7);
			addEvent(new Walk());
		}
	}
	
	private class Walk extends Event{
		String dir;
		public void action() {
			map.Print(i, j);
			System.out.println("Digite para qual direção deseja ir: ");
			System.out.println("[c] cima");
			System.out.println("[e] esquerda");
			System.out.println("[b] baixo");
			System.out.println("[d] direita");
			dir = scanner.next();
			if(dir.equals("c")) {
				if(i == 0) {
					i = 6;
				}
				else {
					i = i - 1;
				}
			}
			if(dir.equals("e")) {
				if(j == 0) {
					j = 6;
				}
				else {
					j = j - 1;
				}
			}
			if(dir.equals("b")) {
				if(i == 6) {
					i = 0;
				}
				else {
					i = i + 1;
				}
			}
			if(dir.equals("d")) {
				if(j == 6) {
					j = 0;
				}
				else {
					j = j + 1;
				}
			}
			addEvent(new WildAppear());
		}
	}
	private class WildAppear extends Event{
		int appear;
		public void action() {
			if(map.m[i][j] == 1) {
				appear = ThreadLocalRandom.current().nextInt(0, 101);
				if(appear < 50) {
					map.Print(i, j);
					addEvent(new CreateWild());
				}
				else {
					addEvent(new Walk());
				}
			}
			else {
				addEvent(new Walk());
			}
		}
	}	
	
	private class CreateWild extends Event{
		int random;
		Pokemon prov;
		public void action() {
			random = ThreadLocalRandom.current().nextInt(1, 4);
			prov = Searcher.pesquisaPoke(random);
			wildP = new Trainer(prov.getName());
			wildP.addPokemon(random);
			System.out.println("Um " +wildP.getName()+ " selvagem apareceu!!");
			System.out.println("");
			addEvent(new CreateWildTurn());
		}
	}
	
	private class CreateWildTurn extends Event{
		public void action() {
			System.out.println("");
			System.out.println("TURNO " + turn);
			System.out.println("");
			System.out.println(p.getName() +" escolha sua acao");
			System.out.println("[1]Ataque         [2]Usar Item" + System.lineSeparator() + "[3]Trocar Pokemon     [4]Fugir");
			a1.setValues(scanner.nextInt(), p);
			a2.setValues(1, wildP);
			addEvent(new ResolveWildTurn());
		}
	}
	
	private class ResolveWildTurn extends Event{

		public void action() {
			int type1, type2;
			boolean dead;
			type1 = a1.getType();
			type2 = a2.getType();
			int tie;
			int end = 0;
			boolean capture = false;
			p.setOver(false);
			
			if (type1 == 4) {
				flee(p);
				System.out.println(p.getName() + " fugiu com sucesso");
				turn = 1;
				end = 1;
			}
			if (type1 == 3)
				swap(p);
			if (type1 == 2) {
				capture = item(p, wildP);
				if(capture == true) {
					end = 1;
					turn = 1;
				}
			}
			if(type1 == 1) {
				if (p.getActivePoke().getSpeed() > wildP.getActivePoke().getSpeed()) {
					dead = attack(a1, wildP);
					if(wildP.getOver()) {
						System.out.println(wildP.getName()+" desmaiou"+System.lineSeparator()+ p.getName()+" venceu em "+turn+ " turnos.");
						turn = 1;
						end = 1;
					}
					if(!dead)
						wildAttack(a2, p);
					if(p.getOver()) {
						System.out.println(p.getName() + " não possui mais Pokemon"+System.lineSeparator()+ p.getName()+" fugiu em "+turn+ " turnos.");
						return;
						}
				}
				else if (p.getActivePoke().getSpeed() < wildP.getActivePoke().getSpeed()) {
					firstWildAttack(a2, p);
					if(p.getOver()) {
						System.out.println(p.getName() + " não possui mais Pokemon"+System.lineSeparator()+ p.getName()+" fugiu em "+turn+ " turnos.");
						return;
					}
					if(wildP.getOver()) {
						System.out.println(wildP.getName()+" desmaiou"+System.lineSeparator()+ p.getName()+" venceu em "+turn+ " turnos.");
						turn = 1;
						}
				}
				else {
					tie = ThreadLocalRandom.current().nextInt(0, 2);
					if (tie == 0) {
						dead = attack(a1, wildP);
						if(!wildP.getOver() && !dead) {
							wildAttack(a2, p);
						}
					}
					else {
						firstWildAttack(a2, p);
					}
					if(p.getOver()) {
						System.out.println(p.getName() + " não possui mais Pokemon"+System.lineSeparator()+ p.getName()+" fugiu em "+turn+ " turnos.");
						return;
						}
					if(wildP.getOver()) {
						System.out.println(wildP.getName()+" desmaiou"+System.lineSeparator()+ p.getName()+" venceu em "+turn+ " turnos.");
						turn = 1;
						end = 1;
						}
				}
			}
			if (type2 == 1 && type1 != 1 && end == 0) {
				wildAttack(a2, p);
				if(p.getOver()) {
					System.out.println(p.getName() + " não possui mais Pokemon"+System.lineSeparator()+ p.getName()+" fugiu em "+turn+ " turnos.");
					return;
				}
			}		
			if(end == 0) {
				turn++;
				addEvent(new CreateWildTurn());
			}
			else {
				addEvent(new Walk());
			}
		}

		private void flee(Trainer p) {
			p.setOver(true);	
		}
		
		private void swap (Trainer p) {
			Pokemon a, b;
			int troca;
			p.getPokeList();
			System.out.println(p.getName() + " digite o número do pokémon que deseja usar");
			troca = scanner.nextInt();
			troca = p.setAtivo(troca, scanner);
			a = p.getPoke(p.getActive());
			b = p.getPoke(troca);
			p.setPoke(b, p.getActive());
			p.setPoke(a, troca);	
		}
		
		private boolean item(Trainer p, Trainer wildP) {
			int choose, succ;
			boolean cap;
			p.getItemList();
			System.out.println("Escolha o item");
			System.out.println("[1]Potion         [2]Pokeball");
			choose = scanner.nextInt();
			while(choose < 1 || choose > 2) {
				System.out.println("Número inválido, digite novamente");
				choose = scanner.nextInt();
			}
			if(choose == 1) {
				if(p.getItem() > 0) {
					p.heal(scanner);
					p.setItem();
					return false;
				}
				else {
					System.out.println("Perdeu o turno");
					return false;
				}
			}
			else {
				if(p.getPokeball() > 0) {
					p.setPokeball();
					succ = wildP.caught();
					if(succ == 1 ) {
						System.out.println(p.getName() + " capturou " + wildP.getName() + "!!");
						System.out.println("");
						cap = p.addPokeWild(wildP.getPoke(1));
						if(cap == false) {
							p.changeParty(scanner, wildP);
						}
						System.out.println("Seu novo time e:" + System.lineSeparator());
						p.getPokeList();
						return true;
					}
					else {
						System.out.println(wildP.getPoke(1).getName() + " escapou da pokebola!");
						System.out.println("");
					}
				}
			}
			return false;
		}
		
		
		private boolean attack(Action a, Trainer p) {
			int damage;
			boolean morreu = false;
			int hp;
			Trainer ta = a.getTrainer();
			Pokemon pokeA = ta.getActivePoke();
			Move atk = pokeA.chooseAtk(scanner, a.getTrainer()); 
			Trainer td = p;
			Pokemon pokeD = td.getActivePoke();
			
			damage = Calc.Dano(atk, pokeA, pokeD);
			hp = pokeD.getHp();
			if(hp - damage <= 0) {
				pokeD.setHp(0);
				System.out.println(pokeA.getName()+ " atacou " +pokeD.getName()+ " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " não tem mais hp");
				td.setLeft();
				morreu = true;
				td.setOver(true);
			}
			else{
				pokeD.setHp(hp - damage);
				System.out.println(pokeA.getName()+ " atacou " +pokeD.getName() + " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " tem " +pokeD.getHp()+ " de vida restando");
				System.out.println("");
			}
			return morreu;
		}
		
		private boolean wildAttack(Action a, Trainer p) {
			int damage;
			int random = ThreadLocalRandom.current().nextInt(1, 5);
			boolean morreu = false;
			int hp, troca;
			Trainer ta = a.getTrainer();
			Pokemon pokeA = ta.getActivePoke();
			Move atk = pokeA.getMove(random); 
			Trainer td = p;
			Pokemon pokeD = td.getActivePoke();
			
			Pokemon morto, substituto;
			
			damage = Calc.Dano(atk, pokeA, pokeD);
			hp = pokeD.getHp();
			if(hp - damage <= 0) {
				pokeD.setHp(0);
				System.out.println(pokeA.getName()+ " atacou " +pokeD.getName()+ " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " não tem mais hp");
				td.setLeft();
				
				if(td.getLeft() != 0) {
					System.out.println("Escolha outro pokémon");
					td.getPokeList();
					troca = scanner.nextInt();
					troca = td.setAtivo(troca, scanner);
					morto = p.getPoke(p.getActive());
					substituto = p.getPoke(troca);
					p.setPoke(substituto, p.getActive());
					p.setPoke(morto, troca);
					morreu = true;
					
				}
				else if (td.getLeft() == 0) {
					td.setOver(true);
				}
			}
			else{
				pokeD.setHp(hp - damage);
				System.out.println(pokeA.getName()+ " atacou " +pokeD.getName() + " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " tem " +pokeD.getHp()+ " de vida restando");
				System.out.println("");
			}
			return morreu;
		}	
	}
	private boolean firstWildAttack(Action a, Trainer p) {
		int damage;
		boolean morreu = false;
		int hp, troca;
		int random = ThreadLocalRandom.current().nextInt(1, 5);
		Trainer ta = a.getTrainer();
		Pokemon pokeA = ta.getActivePoke();
		Move atk = pokeA.getMove(random); 
		Trainer td = p;
		Pokemon pokeD = td.getActivePoke();
	
		Pokemon morto, substituto;
	
		Move esc = pokeD.chooseAtk(scanner, td);
		
		damage = Calc.Dano(atk, pokeA, pokeD);
		hp = pokeD.getHp();
		if(hp - damage <= 0) {
			pokeD.setHp(0);
			System.out.println(pokeA.getName()+ " atacou " +pokeD.getName()+ " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " não tem mais hp");
			td.setLeft();
			
			if(td.getLeft() != 0) {
				System.out.println("Escolha outro pokémon");
				td.getPokeList();
				troca = scanner.nextInt();
				troca = td.setAtivo(troca, scanner);
				morto = p.getPoke(p.getActive());
				substituto = p.getPoke(troca);
				p.setPoke(substituto, p.getActive());
				p.setPoke(morto, troca);
				morreu = true;		
			}
			else {
				morreu = true;
				td.setOver(true);
			}
		}
		else{
			pokeD.setHp(hp - damage);
			System.out.println(pokeA.getName()+ " atacou " +pokeD.getName() + " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " tem " +pokeD.getHp()+ " de vida restando");
			System.out.println("");
		}
		if(morreu != true) {
			damage = Calc.Dano(esc, pokeD, pokeA);
			hp = pokeA.getHp();
			if(hp - damage <= 0) {
				pokeD.setHp(0);
				System.out.println(pokeD.getName()+ " atacou " +pokeA.getName()+ " com " +esc.getName()+ "!"+ System.lineSeparator() +pokeA.getName()+ " não tem mais hp");
				System.out.println("");
				td.setLeft();
				ta.setOver(true);
				morreu = true;
			}
			else{
				pokeA.setHp(hp - damage);
				System.out.println(pokeD.getName()+ " atacou " +pokeA.getName() + " com " +esc.getName()+ "!"+ System.lineSeparator() +pokeA.getName()+ " tem " +pokeA.getHp()+ " de vida restando");
				System.out.println("");
			}
		}
		return morreu;
	}	
}
	

