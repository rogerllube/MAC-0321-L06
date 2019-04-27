
public class Trainer {
	private String name;
	private int pokmnLeft;
	private int[] item;
	private Pokemon[] Pokmn =  new Pokemon[7];
	
	public Trainer (String nome) {
		name = nome;
		for(int i = 0; i < 7; i++) {
			Pokmn[i] = null;
		}
	}
	
	public boolean addPokemon(String poke) {
		int i = 0;
		while(Pokmn[i] != null) {
			i++;
		}
		Pokmn[i] = Searcher.pesquisaPoke();
		if(Pokmn[i] == null) {
			return false;
		}
		else
			return true;
		
	}
}
