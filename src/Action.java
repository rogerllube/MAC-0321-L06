import java.util.concurrent.ThreadLocalRandom;

public class Action {
	private Trainer trainer;
	private int type, subtype;
	public Action(int type, Trainer trainer) {
		this.type = type;
		this.trainer = trainer;
		if(this.type == 1)
			subtype = this.chooseAtk();
		if(this.type == 3)
			subtype = this.choosePoke();
	}
	private int choosePoke() {
		return ThreadLocalRandom.current().nextInt(1, 6+1);
	}
	private int chooseAtk() {
		return ThreadLocalRandom.current().nextInt(1, 4+1);
	}
	private int getType() {
		return type;
	}
	private int getSubtype() {
		return subtype;
	}
	private Trainer getTrainer() {
		return trainer;
	}
}
