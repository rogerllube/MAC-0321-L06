
public class criarJogador extends Event{
	public criarJogador() {
		super();
	}
	private Trainer j1, j2;

	public void action() {
		j1 = new Trainer("Player1");
		j2 = new Trainer("Player2");
	}

	public String description() {
		return "Jogador 1 e Jogador 2 criados com sucesso";
	}
	public Trainer getP1() {
		return j1;
	}
	public Trainer getP2() {
		return j2;
	}
}
