import java.util.Scanner;

public class Trainer {
	private String name;
	private int pokmnLeft;
	private Item[] item;
	private Pokemon[] pokmn =  new Pokemon[7];
	private int index;
	private int activePoke;
	private int changePoke;
	private boolean acabou;
	
	public Trainer (String nome) {
		name = nome;
		for(int i = 0; i < 7; i++) {
			pokmn[i] = null;
		}
		pokmnLeft = 0;
		index = 0;
		acabou = false;
		for(int i = 0; i < 6; i++) {
			item[i].setName("potion");
			item[i].setQuant(1);
			item[i].setRec(20);
		}
	}
	
	public void setOver(boolean over) {
		acabou = over;
	}
	
	public boolean getOver() {
		return acabou;
	}
	
	public String getName() {
		return name;
	}
		
	public int getChange() {
		return changePoke;
	}
	
	public int getActive() {
		return activePoke;
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

	public void setAtivo(int ativo) {
		while(ativo>index||ativo<1||pokmn[ativo].getHp()==0) {
			System.out.println("Numero invalido, escolha outro.");
			ativo = Math.ra;
		}
		System.out.println("O pokemon que entrara na batalha e: "+ pokmn[ativo].getName());
		activePoke = ativo;
	}
	
	public void selPoke(Scanner scanner) {
		boolean con = false;
		String cfm;
		this.getPokeList();
		System.out.println("Digite o número do pokémon que deseja usar:");
		changePoke = scanner.nextInt(); //changePoke armazena o pokemon que será trocado para posteriormente ser utilizado no Event
		while(con == false) {
			System.out.println("O pokémon que será utilizado será" +pokmn[changePoke]+ ". Digite s para confirmar e qualquer outra tecla para ecolher outro");
			cfm = scanner.next();
			if(cfm.equals("s")) {
				con = true;
			}
			else {
				this.getPokeList();
				System.out.println("Digite o número do pokémon que deseja usar:");
				changePoke = scanner.nextInt();
			}
		}
	}
	
	public void changePoke() {
		Pokemon newAtivo, oldAtivo;
		int change = changePoke;
		int active = activePoke;
		newAtivo = pokmn[change];
		oldAtivo = pokmn[active];
		pokmn[active] = newAtivo;
		pokmn[change] = oldAtivo;
	}
	
	public void heal(int cura) {
		int i = cura;
		item[i].
		
	}
	
}
