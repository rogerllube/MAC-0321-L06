import java.util.concurrent.ThreadLocalRandom;

public class Battle extends Event {
	private Trainer p1, p2;
	private int turn;  //sgdefghergdfgragegasdfg
	public Battle(Trainer p1, Trainer p2) {

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
	
	

}
