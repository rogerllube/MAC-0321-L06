
public class GameCreator extends Event {

	public GameCreator(long eventPrio) {
		super(eventPrio);
		
	}

	@Override
	public boolean ready() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void action() {
		Preparer.startGame();
		
	}

	@Override
	public String description() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
