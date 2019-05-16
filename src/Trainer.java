import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Trainer {
	private String name;
	private int pokmnLeft;
	private int item, maxItem;
	private Pokemon[] pokmn =  new Pokemon[7];
	private int index;
	private int activePoke;
	private int changePoke;
	private int pokeball, maxBall;
	private boolean acabou;
	private boolean patt;
	
	public Trainer (Scanner scanner) {
		boolean con = false;
		String cfm;
		name = scanner.next();
		while(con == false) {
			System.out.println("Seu nome e " + name +"? Digite [s] para confirmar ou qualquer outra coisa para alterar.");
			cfm = scanner.next();
			if (cfm.equals("s")) {
				con = true;
				System.out.println("Bom jogo, " + name);
				System.out.println("");
			}
			else {
				System.out.println("Digite seu nome de treinador");
				name = scanner.next();
			}
		
		}
		for(int i = 0; i < 7; i++) {
			pokmn[i] = null;
		}
		pokmnLeft = 0;
		index = 0;
		acabou = false; 
		item = maxItem = 6;
		pokeball = maxBall = 100;
		activePoke = 1;
	}
	
	public Trainer (String nome) {
		name = nome;
		for(int i = 0; i < 7; i++) {
			pokmn[i] = null;
		}
		pokmnLeft = 0;
		index = 0;
		acabou = false; 
		item = 6;
		pokeball = 0;
		activePoke = 1;
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
	public int getPokeball() {
		return pokeball;
	}
	public void setPokeball() {
		pokeball--;
	}
	public void setItem() {
		item--;
		System.out.println("");
		System.out.println(name +" possui "+item+" itens sobrando");
		System.out.println("");
	}
	public void setPoke(Pokemon p, int n) {
		pokmn[n] = p;
	}
	public boolean addPokemon(int poke) {
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
	
	public boolean addPokeWild(Pokemon poke) {
		int i = 0;
		do {
			i++;
		} while(pokmn[i] != null && i < 6);
		if(pokmn[i] == null) {
			pokmn[i] = poke;
			if(pokmn[i] == null) {
				return false;
			}
			else
				pokmnLeft++;
				index++;
				return true;
		}
		return false;
	}
	
	public void getPokeList() {
		int i = 1;
		
		while(i < 7 && pokmn[i] != null) {
			String name = pokmn[i].getName();
			System.out.println("["+i+ "] - " +name+" "+pokmn[i].getHp() + "/" +pokmn[i].getHpMAX());
			i++;
		}
	}
	
	public void getItemList() {
		System.out.println("[1] - Potion " +item+ "/" +maxItem);
		System.out.println("[2] - Pokeball " +pokeball+ "/" +maxBall);
	}
	
	public void removePoke(int n, Scanner scan) {
		boolean conf = true;
		String cfm;
		while((pokmn[n] == null && conf) || n>6) {
			System.out.println("Número inválido, deseja remover pokémon? Digite [s] para confirmar ou qualquer outra coisa para alterar.");
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
			System.out.println("Tem certeza que quer remover " +nome+ "? Digite [s ]para confirmar ou qualquer outra coisa para alterar.");
			cfm = scan.next();
			while(!cfm.equals("s")) {
				System.out.println("Se não quiser mais alterar pokémon, digite [s]");
				cfm = scan.next();
				if(cfm.equals("s")) {
					return;
				}
				System.out.println("Digite o número do pokémon a ser removido");
				n = scan.nextInt();
				nome = pokmn[n].getName();
				System.out.println("Pokémon a ser removido: " +nome);
				System.out.println("Tem certeza que quer remover " +nome+ "? Digite [s] para confirmar ou qualquer outra coisa para alterar.");
				cfm = scan.next();
			}
			System.out.println("Pokémon " +nome+ " removido com sucesso");
			while(n<6 && pokmn[n + 1] != null) {
				pokmn[n] = pokmn[n + 1];
				n++;
			}
			pokmn[n] = null;
			pokmnLeft--;
			index--;
		}
	}

	public int setAtivo(int ativo, Scanner scanner) {
		if(pokmnLeft == 1) {
			while(ativo>index||ativo<1||pokmn[ativo].getHp()==0) {
				System.out.println("Numero invalido, escolha outro.");
				System.out.println("Vocẽ só possui um pokémon disponível para batalha.");
				ativo = scanner.nextInt();
			}
			return ativo;
		}
		while(ativo>index||ativo<1||pokmn[ativo].getHp()==0 || activePoke == ativo) {
			System.out.println("Numero invalido, escolha outro.");
			ativo = scanner.nextInt();
		}
		System.out.println("O pokemon que entrara na batalha e: "+ pokmn[ativo].getName());
		System.out.println("");
		return ativo;
	}
	
	public void selPoke(){
		this.getPokeList();
		do {
			changePoke = ThreadLocalRandom.current().nextInt(1, 6+1);
			
		} while(pokmn[changePoke].getHp()==0);
		System.out.println("O pokémon que será utilizado será" +pokmn[changePoke]);
		
		}
	
	public void heal(Scanner scanner) {
		getPokeList();
		System.out.println(name + " escolha o Pokemon que deseja curar");
		int i = scanner.nextInt();
		while(true) {
		while(i<1 || i> index) {
			System.out.println("Numero invalido, escolha outro");
			i=scanner.nextInt();
		}
			if(pokmn[i].getHp() == 0) {
				System.out.println("Este Pokemon esta desmaiado, escolha outro para curar");
				i=scanner.nextInt();
			}
			else
				break;
		}
		if(pokmn[i].getHp()+20 <= pokmn[i].getHpMAX())
			pokmn[i].setHp(pokmn[i].getHp() + 20);
		else
			pokmn[i].setHp(pokmn[i].getHpMAX());
		System.out.println(pokmn[i].getName()+ " recuperou 20 de hp" + System.lineSeparator() + "Agora " + pokmn[i].getName() + " tem " + pokmn[i].getHp() + "/" + pokmn[i].getHpMAX() + " de hp");
	}

	public void selTeam(Scanner scanner) {
		int i, numPoke;
		String done = "";
		boolean succ;
		for(i = 1;i <= 6 && !done.equals("f"); i++) {
			System.out.println(name +": Digite o numero do seu pokemon " + i);
			numPoke = scanner.nextInt();
			succ = addPokemon(numPoke);
			while(succ == false) {
				System.out.println("Numero invalido, digite novamente");
				numPoke = scanner.nextInt();
				succ = addPokemon(numPoke);
			}
			if(i == 6) {
				System.out.println("Pokemon adicionado com sucesso.");
				break;
			}
			else
				System.out.println("Pokemon adicionado com sucesso. Digite [f] para finalizar ou qualquer outra coisa para adicionar outro");
			done = scanner.next();
		}
		System.out.println(name +", seus Pokemon sao:");
		getPokeList();
		System.out.println("");
		System.out.println(name + ", digite [a] para alterar algum dos Pokemon ou qualquer outra tecla para confirmar");
		String sure, newPoke;
		int remove;
		sure = scanner.next();
		

		while (sure.equals("a")) {	
			if(pokmnLeft != 1) {
				System.out.println(name + ", digite o numero do Pokemon que deseja alterar");
				remove = scanner.nextInt();
				removePoke(remove, scanner);
				getPokeList();
				System.out.println("");
				System.out.println(name +", digite [n] para adicionar um novo Pokemon no lugar do removido ou qualquer outra coisa para nao adicionar");
				newPoke = scanner.next();
				if(newPoke.equals("n")) {
					System.out.println(name +", digite o numero do Pokemon desejado");
					numPoke = scanner.nextInt();
					succ = addPokemon(numPoke);
					while(succ == false) {
						System.out.println("Numero invalido, digite novamente");
						numPoke = scanner.nextInt();
						succ = addPokemon(numPoke);
						}
					System.out.println("Pokemon alterado com sucesso. Seus novos Pokemon sao:");
					getPokeList();
					System.out.println("");
					System.out.println("Digite [a] para fazer mais alteracoes ou qualquer outra tecla para finalizar");
					sure = scanner.next();	
				}
				else {
					System.out.println("Digite [a] para fazer mais alteracoes ou qualquer outra tecla para finalizar");
					sure = scanner.next();	
				}
			}
			else {
				removePoke(1, scanner);
				System.out.println(name +", digite o numero do Pokemon desejado");
				numPoke = scanner.nextInt();
				succ = addPokemon(numPoke);
				while(succ == false) {
					System.out.println("Numero invalido, digite novamente");
					numPoke = scanner.nextInt();
					succ = addPokemon(numPoke);
					}
				System.out.println("Pokemon alterado com sucesso. Seus novos Pokemon sao:");
				getPokeList();
				System.out.println("Digite [a] para fazer mais alteracoes ou qualquer outra tecla para finalizar");
				sure = scanner.next();	
			}
		}
		
	}
	
	public int setStarter(int starter, Scanner scanner) {
		while(starter>index || starter<1) {
			System.out.println("Numero invalido, digite outro");
			starter = scanner.nextInt();
		}
		System .out.println("Seu Pokemon inicial e " + pokmn[starter].getName());
		System.out.println("");
		return starter;
	}
	
	public Pokemon getActivePoke() {
		return pokmn[activePoke];
	}	
	
	public void Inicial(Scanner scanner) {
		int numPoke;
		boolean succ;
		System.out.println(name +": Digite o numero do seu pokemon inicial");
		numPoke = scanner.nextInt();
		succ = addPokemon(numPoke);
		while(succ == false) {
			System.out.println("Numero invalido, digite novamente");
			numPoke = scanner.nextInt();
			succ = addPokemon(numPoke);
		}
		System.out.println("Pokemon adicionado com sucesso.");
		System.out.println(name +", seu Pokemon inicial é:");
		getPokeList();
		System.out.println("");
		System.out.println(name + ", digite [a] para alterar o seu Pokemon ou qualquer outra tecla para confirmar");
		String sure;
		int remove = 1;
		sure = scanner.next();
	
		while (sure.equals("a")) {
			removePoke(remove, scanner);
			System.out.println(name +", digite o numero do Pokemon desejado");
			numPoke = scanner.nextInt();
			succ = addPokemon(numPoke);
			while(succ == false) {
				System.out.println("Numero invalido, digite novamente");
				numPoke = scanner.nextInt();
				succ = addPokemon(numPoke);
			}
			System.out.println("Pokemon alterado com sucesso. Seus novos Pokemon sao:");
			getPokeList();
			System.out.println("Digite [a] para alterar Pokemon ou qualquer outra tecla para finalizar");
			sure = scanner.next();	
		}
	}
	
	public int caught() {
		int f, random;
		f = (pokmn[1].getHpMAX() * 255 * 4)/(pokmn[1].getHp() * 12);
		random = ThreadLocalRandom.current().nextInt(0, 255);
		if(f > random) {
			return 1;
		}
		else {
			return 0;
		}
	}
	
	public void changeParty(Scanner scanner, Trainer wild) {
		String troca;
		int remove;
		System.out.println("Sua party já está cheia");
		System.out.println("Deseja remover um de seus Pokemon e adicionar o capturado?");
		System.out.println("Digite [s] se quiser fazer isso");
		troca = scanner.next();
		if(troca.equals("s")) {
			getPokeList();
			System.out.println("");
			System.out.println("Digite o numero do Pokemon que deseja alterar");
			remove = scanner.nextInt();
			removePoke(remove, scanner);
			addPokeWild(wild.getPoke(1));
			System.out.println(wild.getName() + " foi adicionado a sua party");
		}
	}
	public void setPatt(boolean value) {
		patt = value;
	}
	public boolean getPatt() {
		return patt;
	}
}