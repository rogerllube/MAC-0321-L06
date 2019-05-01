import java.util.concurrent.ThreadLocalRandom;

public class Action {
	private Trainer trainer;
	private int type, subtype;
	public void setValues(int type, Trainer trainer) {
		this.type = type;
		this.trainer = trainer;
		if(this.type == 1)
			subtype = this.chooseAtk();
		if(this.type == 3)
			subtype = this.choosePoke();
	}
	public int choosePoke() {
		return ThreadLocalRandom.current().nextInt(1, 6+1);
	}
	public int chooseAtk() {
		return ThreadLocalRandom.current().nextInt(1, 4+1);
	}
	public int getType() {
		return type;
	}
	public int getSubtype() {
		return subtype;
	}
	public Trainer getTrainer() {
		return trainer;
	}
}
