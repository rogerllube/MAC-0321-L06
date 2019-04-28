
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
		pokmnLeft = 0;
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
			pokmnLeft++;
			return true;
		
	}
	
	public void getPokeList() {
		int i = 0;
		while(i < 7 && Pokmn[i] != null) {
			String name = Pokmn[i].getName();
			System.out.println(""+name);
		}
	}

}
