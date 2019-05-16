

public class Action {
	private Trainer trainer;
	private int type;
	private Move move;
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
	public Move getMove() {
		return move;
	}
	public void setMove(Move move) {
		this.move = move;
	}
}
