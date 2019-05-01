import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Trainer {
	private String name;
	private int pokmnLeft;
	private int item;
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
		item = 6;
	}
	
	public void setOver(boolean over) {
		acabou = over;
	}
	
	public Pokemon getPoke(int active) {
		return pokmn[active];
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
	
	public void setLeft() {
		pokmnLeft--;
	}
	public int getLeft() {
		return pokmnLeft;
	}
	public int getItem() {
		return item;
	}
	public void setItem() {
		item--;
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
		if(pokmnLeft == 1) {
			System.out.println("Vocẽ só possui um pokémon disponível para batalha.");
			return;
		}
		while(ativo>index||ativo<1||pokmn[ativo].getHp()==0 || activePoke == ativo) {
			System.out.println("Numero invalido, escolha outro.");
			ativo = ThreadLocalRandom.current().nextInt(1, 6+1);
		}
		System.out.println("O pokemon que entrara na batalha e: "+ pokmn[ativo].getName());
		activePoke = ativo;
	}
	
	public void selPoke(){
		this.getPokeList();
		do {
			changePoke = ThreadLocalRandom.current().nextInt(1, 6+1);
			
		} while(pokmn[changePoke].getHp()==0);
		System.out.println("O pokémon que será utilizado será" +pokmn[changePoke]);
		
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
	
	public void heal() {
		pokmn[activePoke].setHp(pokmn[activePoke].getHp() + 20);
		System.out.println(pokmn[activePoke]+ " recuperou 20 de hp");
	}
	
}
