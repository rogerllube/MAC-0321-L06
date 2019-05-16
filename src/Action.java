

public class Action {
	private Trainer trainer;
	private int type, subtype;
	public void setValues(int type, Trainer trainer) {
		this.type = type;
		this.trainer = trainer;
			
	}
	
	public int getType() {
		return type;
	}
	public Trainer getTrainer() {
		return trainer;
	}
	public void setSubtype(int st) {
		subtype = st;
	}
	public int getSubtype() {
		return subtype;
	}
}
