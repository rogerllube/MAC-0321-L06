import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Battle extends Controller {
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
			boolean dead;
			type1 = a1.getType();
			type2 = a2.getType();
			int tie;
			if (type1>=type2) {
				if (type1 == 4) {
					flee(p1);
					System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				if (type1 == 3)
					swap(p1);
				if (type1 == 2)
					item(p1);
				if(type1 == 1) 
					if (p1.getActivePoke().getSpeed() > p2.getActivePoke().getSpeed()) {
						dead = attack(a1, p2);
						if(p2.getOver()) {
							System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
							return;
						}
						if(!dead)
							attack(a2, p1);
					}
					else if (p1.getActivePoke().getSpeed() < p2.getActivePoke().getSpeed()) {
						dead = attack(a2, p1);
						if(p1.getOver()) {
							System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
							return;
						}
						if (!dead)
							attack(a1, p2);
					}
					else {
						tie = ThreadLocalRandom.current().nextInt(0, 2);
						if (tie == 0) {
							dead = attack(a1, p2);
							if(!p2.getOver() && !dead) {
								attack(a2, p1);
							}
						}
						else {
							dead = attack(a2, p1);
							if(!p1.getOver() && !dead)
								attack(a1, p2);
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
					flee(p2);
					System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				if (type2 == 3)
					swap(p2);
				if (type2 == 2)
					item(p2);	
				if (type2 == 1 && type1 != 1) {
					attack(a2, p1);
					if(p1.getOver()) {
						System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
						return;
					}
				}
			
		}
			if (type2>type1) {
				if (type2 == 4)
					flee(p2);
				if (type2 == 3)
					swap(p2);
				if (type2 == 2)
					item(p2);
				if(p1.getOver()) {
					System.out.println(p2.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				else if(p2.getOver()) {
					System.out.println(p1.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ " turnos.");
					return;
				}
				
				if (type1 == 4)
					flee(p1);
				if (type1 == 3)
					swap(p1);
				if (type1 == 2)
					item(p1);
				if(type1 == 1)
					attack(a1, p2);
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

		private void swap(Trainer p) {
			Pokemon a, b;
			int troca;
			p.getPokeList();
			System.out.println(p.getName() + " digite o número do pokémon que deseja usar");
			troca = scanner.nextInt();
			a = p.getPoke(p.getActive());
			b = p.getPoke(troca);
			p.setPoke(b, p.getActive());
			p.setPoke(a, troca);
			
		}

		private void flee(Trainer p) {
			p.setOver(true);	
		}
		
		private void item(Trainer p) {
			if(p.getItem() > 0) {
				p.heal(scanner);
				p.setItem();
			}
			else {
				System.out.println("Perdeu o turno");
			}
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
			
			damage = Attack.Dano(atk, pokeA, pokeD);
			hp = pokeD.getHp();
			if(hp - damage <= 0) {
				pokeD.setHp(0);
				System.out.println(pokeA.getName()+ " atacou " +pokeD.getName()+ " com " +atk.getName()+ "!"+ System.lineSeparator() +pokeD.getName()+ " não tem mais hp");
				td.setLeft();
				
				if(td.getLeft() != 0) {
					System.out.println("Escolha outro pokémon");
					td.getPokeList();
					td.setAtivo(scanner.nextInt());
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
			return morreu;
		}
	}
	private class ChooseLead extends Event{

		
		public void action() {
			int starter;
			Pokemon a, b;
			System.out.println(p1.getName() + " escolha o seu pokemon inicial");
			p1.getPokeList();
			starter = scanner.nextInt();
			starter = p1.setStarter(starter, scanner);
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
		Battle battle = new Battle();
		battle.addEvent(battle.new Begin());
		battle.run();
	}
}
	

