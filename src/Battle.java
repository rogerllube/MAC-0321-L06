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
			if (a1.getType()>=a2.getType()) {
				resolve(a1, p2);
				if(p2.getOver()) {
					System.out.println(p1.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
				}
				else if(p1.getOver()) {
					System.out.println(p2.getName() + " e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
					return;
				}
				
			}
			else if(a2.getType()>a1.getType())
				resolve(a2, p1);
			if(p1.getOver()) {
				System.out.println(p2.getName()+" e o vencedor. Parabens!!!"+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
				return;
			}
			else if(p2.getOver()) {
				System.out.println(p1.getName() + " e o vencedor. Parabens!!!."+System.lineSeparator()+"Voce venceu em "+turn+ "turnos.");
				return;
			}
			
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
			
			
		}

		private void flee(Trainer p) {
			p.setOver(true);
			
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
				resolve(a1, p2);
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
		
	private void resolve(Action a1, Trainer p2) {
		if(a1.getType() == 1) {
			Resolve.attack(a1, p2);
	}
		if(a1.getType()==2) {
			Resolve.useItem(a1);
		}
		if (a1.getType == 3) {
			Resolve.switchPoke(a1);
		}
		if(a1.getType() == 4) {
			Resolve.flee(a1);
		}
	}
	public String description() {
		
		return "Fim de jogo!";
	}
	
	private class Attack extends Event{

		
		public void action() {
			
			
		}

		
		public String description() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}

}
