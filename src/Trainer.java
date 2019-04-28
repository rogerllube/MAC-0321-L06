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
	
	public void removePoke(int n, Scanner scan) {
		boolean conf = true;
		String cfm;
		while((Pokmn[n] == null && conf) || n>6) {
			System.out.println("Número inválido, deseja remover pokémon? Digite s para confirmar ou qualquer outra coisa para alterar.");
			cfm = scan.next();
			if(cfm.equals("s")) {
				System.out.println("Digite o número do pokémon a ser removido");
				n = scan.nextInt();
			}
			else {
				conf = false;
			}
		}
		if(Pokmn[n] != null && conf) {
			String nome = Pokmn[n].getName();
			System.out.println("Pokémon a ser removido: " +nome);
			System.out.println("Tem certeza que quer remover " +nome+ "? Digite s para confirmar ou qualquer outra coisa para alterar.");
			cfm = scan.next();
			while(!cfm.equals("s")) {
				System.out.println("Se não quiser mais alterar pokémon, digite s");
				cfm = scan.next();
				if(cfm.equals("s")) {
					scan.close();
					return;
				}
				System.out.println("Digite o número do pokémon a ser removido");
				n = scan.nextInt();
				nome = Pokmn[n].getName();
				System.out.println("Pokémon a ser removido: " +nome);
				System.out.println("Tem certeza que quer remover " +nome+ "? Digite s para confirmar ou qualquer outra coisa para alterar.");
				cfm = scan.next();
			}
			System.out.println("Pokémon " +nome+ " removido com sucesso");
			Pokmn[n] = null;
		}
	}

	public void setLead(int lead) {
		
	}
}
