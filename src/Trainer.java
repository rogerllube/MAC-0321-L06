import java.util.Scanner;

public class Trainer {
	private String name;
	private int pokmnLeft;
	private int[] item;
	private Pokemon[] pokmn =  new Pokemon[7];
	private int index;
	private int activePoke;
	
	public Trainer (String nome) {
		name = nome;
		for(int i = 0; i < 7; i++) {
			pokmn[i] = null;
		}
		pokmnLeft = 0;
		index = 0;
	}
	
	public boolean addPokemon(String poke) {
		int i = 1;
		while(pokmn[i] != null) {
			i++;
		}
		pokmn[i] = Searcher.pesquisaPoke(poke);
		if(pokmn[i] == null) {
			return false;
		}
		else
			pokmnLeft++;
			index++;
			return true;
		
	}
	
	public void getPokeList() {
		int i = 1;
		String fainted;
		while(i < 7 && pokmn[i] != null) {
			String name = pokmn[i].getName();
			if(pokmn[i].getHp()== 0)
				fainted = "fainted";
			else fainted = "";
			System.out.println(""+i+ " - " +name+" "+fainted);
			i++;
		}
	}
	
	public void removePoke(int n, Scanner scan) {
		boolean conf = true;
		String cfm;
		while((pokmn[n] == null && conf) || n>6) {
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
		if(pokmn[n] != null && conf) {
			String nome = pokmn[n].getName();
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
				nome = pokmn[n].getName();
				System.out.println("Pokémon a ser removido: " +nome);
				System.out.println("Tem certeza que quer remover " +nome+ "? Digite s para confirmar ou qualquer outra coisa para alterar.");
				cfm = scan.next();
			}
			System.out.println("Pokémon " +nome+ " removido com sucesso");
			pokmn[n] = null;
		}
	}

	public void setAtivo(int ativo, Scanner scanner) {
		while(ativo>index||ativo<1||pokmn[ativo].getHp()==0) {
			System.out.println("Numero invalido, escolha outro.");
			ativo = scanner.nextInt();
		}
		System.out.println("O pokemon que entrara na batalha e: "+ pokmn[ativo].getName());
		activePoke = ativo;
	}
}
