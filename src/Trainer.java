import java.util.Scanner;

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
		int i = 1;
		while(Pokmn[i] != null) {
			i++;
		}
		Pokmn[i] = Searcher.pesquisaPoke(poke);
		if(Pokmn[i] == null) {
			return false;
		}
		else
			pokmnLeft++;
			return true;
		
	}
	
	public void getPokeList() {
		int i = 1;
		while(i < 7 && Pokmn[i] != null) {
			String name = Pokmn[i].getName();
			System.out.println(""+i+ " - " +name);
			i++;
		}
	}
	
	public void removePoke(int n) {
		boolean conf = true;
		String cfm;
		Scanner scanner = new Scanner(System.in);
		while(Pokmn[n] != null && conf) {
			System.out.println("Número inválido, deseja remover pokémon? Digite s para confirmar ou qualquer outra coisa para alterar.");
			cfm = scanner.next();
			if(cfm.equals("s")) {
				System.out.println("Digite o número do pokémon a sr removido");
				n = scanner.nextInt();
			}
			else {
				conf = false;
			}
		}
		if(Pokmn[n] != null && conf) {
			String nome = Pokmn[n].getName();
			System.out.println("Pokémon a ser removido: " +nome);
			System.out.println("Tem certeza que quer remover " +nome+ "? Digite s para confirmar ou qualquer outra coisa para alterar.");
			cfm = scanner.next();
			while(!cfm.equals("s")) {
				System.out.println("Se não quiser mais alterar pokémon, digite s");
				cfm = scanner.next();
				if(cfm.equals("s")) {
					scanner.close();
					return;
				}
				System.out.println("Digite o número do pokémon a ser removido");
				n = scanner.nextInt();
				nome = Pokmn[n].getName();
				System.out.println("Pokémon a ser removido: " +nome);
				System.out.println("Tem certeza que quer remover " +nome+ "? Digite s para confirmar ou qualquer outra coisa para alterar.");
				cfm = scanner.next();
			}
			System.out.println("Pokémon " +nome+ " removido com sucesso");
			Pokmn[n] = null;
		}
		scanner.close();
	}
}
