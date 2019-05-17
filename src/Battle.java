import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Battle extends Controller {
	private Trainer p1, p2;

	private int turn = 1;
	private Action a1 = new Action(), a2 = new Action();
	Scanner scanner = new Scanner(System.in);
	public Battle(Scanner scanner) {
		this.scanner = scanner;
	}
	
	public class Begin extends Event{

		public void action() {
			addEvent(new CreateTrainers());
		}
	}
	private class CreateTrainers extends Event{

		
		public void action() {
			System.out.println("Treinador 1, digite seu nome");
			p1 = new Trainer(scanner);
			System.out.println("Treinador 2, digite seu nome");
			p2 = new Trainer(scanner);
			addEvent(new ChoosePoke());
			
		}
	}
	private class ChoosePoke extends Event{

		public void action() {
			System.out.println("Pokemon disponiveis:");
			Lista.listar();
			p1.selTeam(scanner);
			p2.selTeam(scanner);
			addEvent(new ChooseLead());
			
		}
	}
	private class CreateTurn extends Event{

	
		public void action() {
			System.out.println("");
			System.out.println("TURNO " + turn);
			System.out.println("");
			System.out.println(p1.getName() +" escolha sua acao");
			System.out.println("[1]Ataque         [2]Usar Item" + System.lineSeparator() + "[3]Trocar Pokemon     [4]Fugir");
			a1.setValues(scanner.nextInt(), p1);
			System.out.println("");
			if(a1.getType() == 1)
				a1.setMove(p1.getActivePoke().chooseAtk(scanner, p1));
			System.out.println(p2.getName() +" escolha sua acao");
			System.out.println("[1]Ataque         [2]Usar Item" + System.lineSeparator() + "[3]Trocar Pokemon     [4]Fugir");
			a2.setValues(scanner.nextInt(), p2);
			System.out.println("");
			if(a2.getType() == 1)
				a2.setMove(p2.getActivePoke().chooseAtk(scanner, p2));
			p1.setPatt(true);
			p2.setPatt(true);
			addEvent(new ResolveTurn());			
		}
	}
	private class ResolveTurn extends Event{

		
		public void action() {
			int type1, type2;
			type1 = a1.getType();
			type2 = a2.getType();
			int tie;
			if (type1>=type2) {
				if (type1 == 4) {
					addEvent(new Flee(p1));
					addEvent(new CheckEnd(p2,p1,2));
				}
				if (type1 == 3)
					addEvent(new Swap(p1));
				if (type1 == 2)
					addEvent(new Item(p1));
				if(type1 == 1) {
					if(a1.getMove().getPriority()>a2.getMove().getPriority()) {
						addEvent(new Attack(a1, p2));
						addEvent(new CheckEnd(p1, p2,1));
						addEvent(new Attack(a2, p1));
						addEvent(new CheckEnd(p2,p1,2));
					}
					if(a2.getMove().getPriority()>a1.getMove().getPriority()) {
						addEvent(new Attack(a2, p1));
						addEvent(new CheckEnd(p2, p1,1));
						addEvent(new Attack(a1, p2));
						addEvent(new CheckEnd(p1,p2,2));
					}
					if(a1.getMove().getPriority() == a2.getMove().getPriority()) {
						if (p1.getActivePoke().getSpeed() > p2.getActivePoke().getSpeed()) {
							addEvent(new Attack(a1, p2));
							addEvent(new CheckEnd(p1, p2,1));
							addEvent(new Attack(a2, p1));
							addEvent(new CheckEnd(p2,p1,2));
						}
						else if (p1.getActivePoke().getSpeed() < p2.getActivePoke().getSpeed()) {
							addEvent(new Attack(a2, p1));
							addEvent(new CheckEnd(p2, p1,1));
							addEvent(new Attack(a1, p2));
							addEvent(new CheckEnd(p1,p2,2));
						}
						else {
							tie = ThreadLocalRandom.current().nextInt(0, 2);
							if (tie == 0) {
								addEvent(new Attack(a1, p2));
								addEvent(new CheckEnd(p1,p2,1));
								addEvent(new Attack(a2, p1));
								addEvent(new CheckEnd(p2,p1,2));
								}
							else {
								addEvent(new Attack(a2, p1));
								addEvent(new CheckEnd(p2,p1,1));
								addEvent(new Attack(a1, p2));
								addEvent (new CheckEnd(p1,p2,2));
							}
						}
					}
				}
				if (type2 == 4) {
					addEvent(new Flee(p2));
					addEvent(new CheckEnd(p1,p2,2));
				}
				if (type2 == 3)
					addEvent(new Swap(p2));
				if (type2 == 2)
					addEvent(new Item(p2));	
				if (type2 == 1 && type1 != 1) {
					addEvent(new Attack(a2, p1));
					addEvent(new CheckEnd(p2,p1,2));
				}
			}
			if (type2>type1) {
				if (type2 == 4) {
					addEvent(new Flee(p2));
					addEvent(new CheckEnd(p1,p2,2));
			}
				if (type2 == 3)
					addEvent(new Swap(p2));
				if (type2 == 2)
					addEvent(new Item(p2));
				if (type1 == 4) {
					addEvent(new Flee(p1));
					addEvent(new CheckEnd(p2,p1,2));
				}
				if (type1 == 3)
					addEvent(new Swap(p1));
				if (type1 == 2)
					addEvent(new Item(p1));
				if(type1 == 1) {
					addEvent(new Attack(a1, p2));
					addEvent(new CheckEnd(p1,p2,2));
				}
			}
			turn++;
			addEvent(new CreateTurn());
			}
	}
	private class Flee extends Event{
	
		Trainer trainer;
		public Flee(Trainer trainer) {
			this.trainer = trainer;
		}
		
		public void action() {
			trainer.setOver(true);
		}
		
	}
	private class Item extends Event{
		Trainer trainer;
		public Item(Trainer trainer) {
			this.trainer = trainer;
		}
		public void action() {
			if(trainer.getItem() > 0) {
				trainer.heal(scanner);
				trainer.setItem();
			}
			else {
				System.out.println("Perdeu o turno");
			}	
		}
	}
	private class Swap extends Event{
		Trainer trainer;
		public Swap(Trainer trainer) {
			this.trainer = trainer;
		}
		public void action() {
			Pokemon a, b;
			int troca;
			trainer.getPokeList();
			System.out.println(trainer.getName() + " digite o número do pokémon que deseja usar");
			troca = scanner.nextInt();
			troca = trainer.setAtivo(troca, scanner);
			a = trainer.getPoke(trainer.getActive());
			b = trainer.getPoke(troca);
			trainer.setPoke(b, trainer.getActive());
			trainer.setPoke(a, troca);
		}
	}
	private class Attack extends Event{
		Action a;
		Trainer def;
		public Attack(Action a, Trainer def) {
			this.a = a;
			this.def = def;
		}
		public void action() {
			int damage;
			int hp, troca;
			Trainer ta = a.getTrainer();
			Pokemon pokeA = ta.getActivePoke();
			Move atk = a.getMove(); 
			Trainer td = def;
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
	private class ChooseLead extends Event{
		public void action() {
			int starter;
			Pokemon a, b;
			System.out.println(p1.getName() + " escolha o seu pokemon inicial");
			p1.getPokeList();
			starter = scanner.nextInt();
			p1.setStarter(starter, scanner);
			a = p1.getPoke(p1.getActive());
			b = p1.getPoke(starter);
			p1.setPoke(b, p1.getActive());
			p1.setPoke(a, starter);
			System.out.println(p2.getName() + " escolha o seu pokemon inicial");
			p2.getPokeList();
			starter = scanner.nextInt();
			starter = p2.setStarter(starter, scanner);
			a = p2.getPoke(p2.getActive());
			b = p2.getPoke(starter);
			p2.setPoke(b, p2.getActive());
			p2.setPoke(a, starter);
			addEvent(new CreateTurn());
			
		}
		
	}
	private class CheckEnd extends Event{
		Trainer ta, tb;
		int i;
		int win = turn;
		public CheckEnd(Trainer a, Trainer b, int i) {
			ta = a;
			tb = b;
			this.i = i;
		}
		public void action() {
			if(tb.getOver() ) {
				System.out.println(ta.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+ win +" turnos.");
				removeNext();
				if(i == 1) {
					removeX(2);
				}
				return;
			}
			else if(!tb.getPatt()) {
				removeNext();
				addEvent(new CreateTurn());
			}
		}
	}
}


