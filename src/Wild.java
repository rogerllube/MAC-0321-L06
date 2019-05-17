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
			random = ThreadLocalRandom.current().nextInt(1, 152);
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
			System.out.println("");
			if(a1.getType() == 1)
				a1.setMove(p.getActivePoke().chooseAtk(scanner, p));
			a2.setValues(1, wildP);
			System.out.println("");
			if(a2.getType() == 1)
				a2.setMove(wildP.getActivePoke().chooseAtk(wildP));
			p.setPatt(true);
			wildP.setPatt(true);
			addEvent(new ResolveWildTurn());
		}
	}
	
	private class ResolveWildTurn extends Event{

		public void action() {
			int type1, type2;
			type1 = a1.getType();
			type2 = a2.getType();
			int tie;
			p.setOver(false);
			
			if (type1 == 4) {
				addEvent(new Flee(p));
			}
			if (type1 == 3)
				addEvent(new Swap(p));
			if (type1 == 2) {
				addEvent(new Item(p, wildP));
				addEvent(new CheckEnd(p, wildP));
			}
			if(type1 == 1) {
				if (p.getActivePoke().getSpeed() > wildP.getActivePoke().getSpeed()) {
					addEvent(new Att(a1, wildP));
					addEvent(new CheckEnd(wildP, p));
					addEvent(new Att(a2, p));
					addEvent(new CheckEnd(p, wildP));
				}
				else if (p.getActivePoke().getSpeed() < wildP.getActivePoke().getSpeed()) {
					addEvent(new Att(a2, p));
					addEvent(new CheckEnd(p, wildP));
					addEvent(new Att(a1, wildP));
					addEvent(new CheckEnd(wildP, p));
				}
				else {
					tie = ThreadLocalRandom.current().nextInt(0, 2);
					if (tie == 0) {
						addEvent(new Att(a1, wildP));
						addEvent(new CheckEnd(wildP, p));
						addEvent(new WildAttack(a2, p));
						addEvent(new CheckEnd(p, wildP));
					}
					else {
						addEvent(new Att(a2, p));
						addEvent(new CheckEnd(p, wildP));
						addEvent(new Att(a1, wildP));
						addEvent(new CheckEnd(wildP, p));
					}
				}
			}
			if (type2 == 1 && type1 != 1) {
				addEvent(new Att(a2, p));
				addEvent(new CheckEnd(p, wildP));
			}		
			turn++;
			addEvent(new CreateWildTurn());
		}

	}
	private class Flee extends Event {
		Trainer p;
		public Flee (Trainer t) {
			p = t;
		}
		public void action() {
			p.setOver(true);	
			p.setRun(true);
			System.out.println(p.getName() + " fugiu com sucesso");
			addEvent(new Walk());
			removeNext();
			removeX(2);
			turn =1;
		}
	}
	
	private class Swap extends Event {
		
		Trainer p;
		
		public Swap(Trainer t) {
			p = t;
		}
		public void action() {
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
	}
	
	private class Item extends Event {
		
		Trainer p, wildP;
		public Item (Trainer t, Trainer w) {
			p = t;
			wildP = w;
		}
		public void action() {
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
				}
				else {
					System.out.println("Perdeu o turno");
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
						p.setCapture(true);
					}
					else {
						System.out.println(wildP.getPoke(1).getName() + " escapou da pokebola!");
						System.out.println("");
					}
				}
			}
		}
		
	}

	private class Att extends Event {
		
		Action a;
		Trainer p;
		
		public Att (Action action, Trainer t) {
			a = action;
			p = t;
		}
		
		public void action () {
			int damage;
			int hp, troca;
			Trainer ta = a.getTrainer();
			Pokemon pokeA = ta.getActivePoke();
			Move atk = a.getMove();
			Trainer td = p;
			Pokemon pokeD = td.getActivePoke();
			Pokemon morto, substituto;
			damage = Calc.Dano(atk, pokeA, pokeD);
			hp = pokeD.getHp();
			if(hp - damage <= 0) {
				pokeD.setHp(0);
				System.out.println(pokeA.getName()+ " atacou " +pokeD.getName()+ " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " não tem mais hp");
				td.setLeft();
				td.setPatt(false);
				if(td.getLeft() != 0) {
					System.out.println("Escolha outro pokémon");
					td.getPokeList();
					troca = scanner.nextInt();
					troca = td.setAtivo(troca, scanner);
					morto = td.getPoke(td.getActive());
					substituto = td.getPoke(troca);
					td.setPoke(substituto, td.getActive());
					td.setPoke(morto, troca);		
				}
				else {
					td.setOver(true);
				}
			}
			else{
				pokeD.setHp(hp - damage);
				System.out.println(pokeA.getName()+ " atacou " +pokeD.getName() + " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " tem " +pokeD.getHp()+ " de vida restando");
				System.out.println("");
			}
		}
	}
	
	private class WildAttack extends Event {
		
		Action a;
		Trainer p;
		
		public WildAttack(Action action, Trainer t) {
		a = action;
		p = t;
		}
		public void action() {
			int damage;
			int random = ThreadLocalRandom.current().nextInt(1, 5);
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
					td.setPatt(false);
					
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
		}	
	}
	
	
	
	private class CheckEnd extends Event{
		Trainer ta;
		int win = turn;
	
		public CheckEnd(Trainer a, Trainer b) {
			ta = a;
		}
		public void action() {
			if(ta.getCapture()) {
				turn = 1;
				ta.setCapture(false);
				removeNext();
				addEvent(new Walk());
				removeX(2);
			}
			else if(ta.getOver() && !ta.getRun()) {
				if(ta.getHuman()) {
					System.out.println(ta.getName() + " não possui mais Pokemon"+System.lineSeparator()+ ta.getName()+" fugiu em "+turn+ " turnos.");
					removeNext();
					removeX(8);
					}
				else {
					addEvent(new Walk());
					System.out.println(ta.getName() + " desmaiou!!!"+System.lineSeparator()+"Voce venceu em "+ win +" turnos.");
					removeNext();
					removeX(2);
					turn = 1;
				}
			}
		}
	}
}
	

