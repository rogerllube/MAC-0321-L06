import java.util.concurrent.ThreadLocalRandom;

public class Battle extends Event {
	private Trainer p1, p2;
	private int turn;
	public Battle(Trainer p1, Trainer p2) {

	}

	public void action() {
		Action a1, a2;
		while(p1.getOver() && p2.getOver()) {
			a1 = new Action(ThreadLocalRandom.current().nextInt(1, 4+1), p1);
			a2 = new Action(ThreadLocalRandom.current().nextInt(1, 4+1), p2);
			if (a1>=a2)
				resolve(a1);
		
		
		
		
		}
	}
		
	private void resolve(Action a1) {
		if(a1.getType() == 1) {
			
			
	}

	}
	public String description() {
	
		return null;
	}
	
	

}
