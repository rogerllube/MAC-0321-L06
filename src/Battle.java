import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Battle extends Controller {
	private Trainer p1, p2;

	private int turn;
	private Action a1, a2;
	Scanner scanner = new Scanner(System.in);
	
	private class Begin extends Event{

		public void action() {
			addEvent(new CreateTrainers());
		}
		
		public String description() {
		
			return "Batalha iniciada";
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
			
		}
		
	}
	private class CreateTurn extends Event{

	
		public void action() {
			a1 = new Action();
			a2 = new Action();
			a1.setValues(ThreadLocalRandom.current().nextInt(1, 4+1), p1);
			a2.setValues(ThreadLocalRandom.current().nextInt(1, 4+1), p2);
			addEvent(new ResolveTurn());			
		}
	}
	private class ResolveTurn extends Event{

		
		public void action() {
			int type1, type2;
			type1 = a1.getType();
			type2 = a2.getType();
			if (type1>=type2) {
				if (type1 == 4)
					flee(p1);
				if (type1 == 3)
					swap(p1);
				if (type1 == 2)
					item(p1);
				if(type1 == 1) 
					attack(a1, p2);
				if(p2.getOver()) {
					System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
				}
				else if(p1.getOver()) {
					System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
					}
				
				if (type2 == 4)
					flee(p2);
				if (type2 == 3)
					swap(p2);
				if (type1 == 2)
					item(p2);
				if(type2 == 1) 
					attack(a2, p1);
				if(p1.getOver()) {
					System.out.println(p2.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
				}
				else if(p2.getOver()) {
					System.out.println(p1.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
				}		
			
		}
			if (type2>=type1) {
				if (type2 == 4)
					flee(p2);
				if (type2 == 3)
					swap(p2);
				if (type2 == 2)
					item(p2);
				if(type2 == 1) 
					attack(a2, p1);
				if(p1.getOver()) {
					System.out.println(p2.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
				}
				else if(p2.getOver()) {
					System.out.println(p1.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
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
					System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
				}
				else if(p1.getOver()) {
					System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
				}		
			}	
		}

		private void swap(Trainer p) {
			Pokemon a, b;
			int troca;
			p.getPokeList();
			System.out.println("Digite o número do pokémon que deseja usar");
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
				p.heal();
				p.setItem();
			}
			else {
				System.out.println("Perdeu o turno");
			}
		}
		
		private void attack(Action a, Trainer p) {
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
	}

	public void action() {
		Action a1, a2;
		a1 = new Action();
		a2 = new Action();
		for(turn =0;!p1.getOver() && !p2.getOver(); turn++) {
			a1.setValues(ThreadLocalRandom.current().nextInt(1, 4+1), p1);
			a2.setValues(ThreadLocalRandom.current().nextInt(1, 4+1), p2);
			if (a1.getType()>=a2.getType())
				ResolveTurn(a1, p2);
			if(p2.getOver()) {
				System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
				break;
			}
			else if(p1.getOver()) {
				System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
				break;
			}
			else if(a2.getType()>a1.getType())
				resolve(a2, p1);
			if(p1.getOver()) {
				System.out.println(p2.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
				break;
			}
			else if(p2.getOver()) {
				System.out.println(p1.getName() + " e o vencedor. Parabens!!!."+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
				break;
			}

		}
	}

	public String description() {
		
		return "Fim de jogo!";
	}
}
	

