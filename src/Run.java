
public class Run extends Event {

	private Trainer p;
	public Run(long eventTime, Trainer perdedor) {
		super(eventTime);
		p = perdedor;
	}

	@Override
	public void action() {
		p.setOver(true);
	}

	@Override
	public String description() {
		String message;
		message = "Treinador " +p.getName()+ " fugiu da batalha";
		return message;
	}

}
