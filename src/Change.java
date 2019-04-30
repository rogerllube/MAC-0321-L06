import java.util.Scanner;

public class Change extends Event{

	private Trainer t;
	int active;
	
	public Change(long eventTime, Trainer treinador, int ativo) {
		super(eventTime);
		t = treinador;
		active = ativo;
	}


	public void action() {
		t.setAtivo(active);
	}


	public String description() {
		String message;
		message = t+ "trocou de pokémon";
		return message;
	}
}
