import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BattleExp extends Controller{
	private Trainer p1, p2;

	private int turn = 1;
	private Action a1 = new Action(), a2 = new Action();
	Scanner scanner = new Scanner(System.in);
	
	private class Begin extends Event{

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
			System.out.println(p2.getName() +" escolha sua acao");
			System.out.println("[1]Ataque         [2]Usar Item" + System.lineSeparator() + "[3]Trocar Pokemon     [4]Fugir");
			a2.setValues(scanner.nextInt(), p2);
			System.out.println("");
			addEvent(new ResolveTurn());			
		}
	}
	private class ResolveTurn extends Event{

		
		public void action() {
			int type1, type2;
			boolean dead = false;
			type1 = a1.getType();
			type2 = a2.getType();
			int tie;
			if (type1>=type2) {
				if (type1 == 4) {
					addEvent(new Flee(p1));
					System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				if (type1 == 3)
					addEvent(new Swap(p1));
				if (type1 == 2)
					addEvent(new Item(p1));
				if(type1 == 1) 
					if (p1.getActivePoke().getSpeed() > p2.getActivePoke().getSpeed()) {
						addEvent(new Atta(a1, p2, dead));
						if(p2.getOver()) {
							System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
							return;
						}
						if(!dead)
							addEvent(new Atta(a2, p1, dead));
					}
					else if (p1.getActivePoke().getSpeed() < p2.getActivePoke().getSpeed()) {
						addEvent(new Atta(a2, p1, dead));
						if(p1.getOver()) {
							System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
							return;
						}
						if (!dead)
							addEvent(new Atta(a1, p2, dead));
					}
					else {
						tie = ThreadLocalRandom.current().nextInt(0, 2);
						if (tie == 0) {
							addEvent(new Atta(a1, p2, dead));
							if(!p2.getOver() && !dead) {
								addEvent(new Atta(a2, p1, dead));
							}
						}
						else {
							addEvent(new Atta(a2, p1, dead));
							if(!p1.getOver() && !dead)
								addEvent(new Atta(a1, p2, dead));
						}
						if(p1.getOver()) {
							System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
							return;
							}
						if(p2.getOver()) {
							System.out.println(p1.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
							return;
							}
					}
				if (type2 == 4) {
					addEvent(new Flee(p2));
					System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				if (type2 == 3)
					addEvent(new Swap(p2));
				if (type2 == 2)
					addEvent(new Item(p2));	
				if (type2 == 1 && type1 != 1) {
					addEvent(new Atta(a2, p1, dead));
					if(p1.getOver()) {
						System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
						return;
					}
				}
			
		}
			if (type2>type1) {
				if (type2 == 4)
					addEvent(new Flee(p2));
				if (type2 == 3)
					
					addEvent(new Swap(p2));
					System.out.println("Primeiro");
				if (type2 == 2)
					addEvent(new Item(p2));
				if(p1.getOver()) {
					System.out.println(p2.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				else if(p2.getOver()) {
					System.out.println(p1.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				
				if (type1 == 4)
					addEvent(new Flee(p1));
				if (type1 == 3)
					addEvent(new Swap(p1));
				if (type1 == 2)
					addEvent(new Item(p1));
				if(type1 == 1)
					addEvent(new Atta(a1, p2, dead));
					System.out.println("SEGUNDO");
				if(p2.getOver()) {
					System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				else if(p1.getOver()) {
					System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}		
			}	
			turn++;
			addEvent(new CreateTurn());
		}
	}
	
	private class Swap extends Event {
		Pokemon a, b;
		int troca;
		Trainer p;
		
		public Swap (Trainer t) {
			p = t;
		}	
		public void action() {
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
	
	private class Flee extends Event {
		Trainer p;
		
		public Flee (Trainer t) {
			p = t;
		}
		
		public void action() {
			p.setOver(true);
		}
	
	}
	
	private class Item extends Event {
		Trainer p;
		
		public Item (Trainer t) {
			p = t;
		}
		public void action() {
			if(p.getItem() > 0) {
				p.heal(scanner);
				p.setItem();
			}
			else {
				System.out.println("Perdeu o turno");
			}
		}
	}
	
	private class Atta extends Event {
		private int damage;
		boolean morreu;
		int hp, troca;
		Trainer ta;
		Pokemon pokeA;
		Move atk;
		Trainer td;
		Pokemon pokeD;
		
		Pokemon morto, substituto;
		
		public Atta(Action a, Trainer p, boolean dead) {
			morreu = dead;
			ta = a.getTrainer();
			pokeA = ta.getActivePoke();
			atk = pokeA.chooseAtk(scanner, a.getTrainer());
			td = p;
			pokeD = td.getActivePoke();
			damage = Attack.Dano(atk, pokeA, pokeD);
			hp = pokeD.getHp();
		}
		
		public void action() {
			if(hp - damage <= 0) {
				pokeD.setHp(0);
				System.out.println(pokeA.getName()+ " atacou " +pokeD.getName()+ " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " não tem mais hp");
				td.setLeft();
			
				if(td.getLeft() != 0) {
					System.out.println("Escolha outro pokémon");
					td.getPokeList();
					troca = scanner.nextInt();
					troca = td.setAtivo(troca, scanner);
					morto = td.getPoke(td.getActive());
					substituto = td.getPoke(troca);
					td.setPoke(substituto, td.getActive());
					td.setPoke(morto, troca);
					morreu = true;
				
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
	public static void main(String[] args) {
		BattleExp battle = new BattleExp();
		battle.addEvent(battle.new Begin());
		battle.run();
	}
}
