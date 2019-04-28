import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {
		System.out.println("Bem vindo, jogador 1. Digite o seu nome de treinador");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		boolean con = false;
		String cfm;
		while(con == false) {
			System.out.println("Seu nome e " + name +"? Digite s para confirmar ou qualquer outra coisa para alterar.");
			cfm = scanner.next();
			if (cfm.equals("s")) {
				con = true;
				System.out.println("Bom jogo, " + name);
			}
			else {
				System.out.println("Digite seu nome de treinador");
				name = scanner.next();
			}
		
		}
		System.out.println("Bem vindo, jogador 2. Digite o seu nome de treinador");
		String name2 = scanner.next();
		con = false;
		while(con == false) {
			System.out.println("Seu nome e " + name2 +"? Digite s para confirmar ou qualquer outr coisa para alterar.");
			cfm = scanner.next();
			if (cfm.equals("s")) {
				con = true;
				System.out.println("Bom jogo, " + name2);
			}
			else {
				System.out.println("Digite seu nome de treinador");
				name2 = scanner.next();
			}
		
		}
		System.out.println("Pokemon disponiveis:");
		Lista.listar();
		int i;
		boolean succ;
		String nomePoke, done = "s";
		Trainer ja = new Trainer(name);
		Trainer jb = new Trainer(name2);
		for(i = 1;i <= 6 && !done.equals("f"); i++) {
			System.out.println(name +": Digite o nome do seu pokemon numero " + i);
			nomePoke = scanner.next();
			succ = ja.addPokemon(nomePoke);
			while(succ == false) {
				System.out.println("Nome invalido, digite novamente");
				nomePoke = scanner.next();
				succ = ja.addPokemon(nomePoke);
			}
			if(i == 6) {
				System.out.println("Pokemon adicionado com sucesso.");
				break;
			}
			else
				System.out.println("Pokemon adicionado com sucesso. Digite f para finalizar ou qualquer outra coisa para adicionar outro");
			done = scanner.next();
		}
		System.out.println(name +", seus Pokemon sao:");
		ja.getPokeList();
		System.out.println(name + ", digite a para alterar algum dos Pokemon ou qualquer outra tecla para confirmar");
		String sure, newPoke;
		int remove;
		sure = scanner.next();
		

		while (sure.equals("a")) {
			System.out.println(name + ", digite o numero do Pokemon que deseja alterar");
			remove = scanner.nextInt();
			ja.removePoke(remove, scanner);
			System.out.println(name +", digite n para adicionar um novo Pokemon no lugar do removido ou qualquer outra coisa para nao adicionar");
			newPoke = scanner.next();
			if(newPoke.equals("n")) {
				System.out.println(name +", digite o nome do Pokemon desejado");
				nomePoke = scanner.next();
				succ = ja.addPokemon(nomePoke);
				while(succ == false) {
					System.out.println("Nome invalido, digite novamente");
					nomePoke = scanner.next();
					succ = jb.addPokemon(nomePoke);
				}
				System.out.println("Pokemon alterado com sucesso. Seus novos Pokemon sao:");
				ja.getPokeList();
				System.out.println("Digite a para fazer mais alteracoes ou qualquer outra tecla para finalizar");
				sure = scanner.next();
				
				
			}
		}
		done = "s";
		for(i = 1;i <= 6 && !done.equals("f"); i++) {
			System.out.println(name2 +": Digite o nome do seu pokemon numero " + i);
			nomePoke = scanner.next();
			succ = jb.addPokemon(nomePoke);
			while(succ == false) {
				System.out.println("Nome invalido, digite novamente");
				nomePoke = scanner.next();
				succ = jb.addPokemon(nomePoke);
			}
			if(i == 6)
				System.out.println("Pokemon adicionado com sucesso.");
			else
				System.out.println("Pokemon adicionado com sucesso. Digite f para finalizar ou qualquer outra coisa para adicionar outro");
			done = scanner.next();
		}
		System.out.println(name2 +", seus Pokemon sao:");
		jb.getPokeList();
		System.out.println(name2 + ", digite a para alterar algum dos Pokemon ou qualquer outra tecla para confirmar");
		sure = scanner.next();
		while (sure.equals("a")) {
			System.out.println(name + ", digite o numero do Pokemon que deseja alterar");
			remove = scanner.nextInt();
			jb.removePoke(remove, scanner);
			System.out.println(name +", digite n para adicionar um novo Pokemon no lugar do removido ou qualquer outra coisa para nao adicionar");
			newPoke = scanner.next();
			if(newPoke.equals("n")) {
				System.out.println(name +", digite o nome do Pokemon desejado");
				nomePoke = scanner.next();
				succ = jb.addPokemon(nomePoke);
				while(succ == false) {
					System.out.println("Nome invalido, digite novamente");
					nomePoke = scanner.next();
					succ = jb.addPokemon(nomePoke);
				}
				System.out.println("Pokemon alterado com sucesso. Seus novos Pokemon sao:");
				jb.getPokeList();
				System.out.println("Digite a para fazer mais alteracoes ou qualquer outra tecla para finalizar");
				sure = scanner.next();
			}
		}
		System.out.println("Seu time e:");
		ja.getPokeList();
		System.out.println(name +", digite o numero do pokemon que vai comecar em campo");
		int lead = scanner.nextInt();
		ja.setAtivo(lead, scanner);
		System.out.println("Seu time e:");
		jb.getPokeList();
		System.out.println(name2 + ", digite o numero do pokemon que vai comecar em campo");
		lead = scanner.nextInt();
		jb.setAtivo(lead, scanner);
		
		int turno;
		String acao;
		boolean acabou = false;
		for(turno = 1; acabou = false; turno++) {
			System.out.println(name+" digite a para selecionar um ataque, p para trocar de Pokemon, i para usar um item ou f para fugir");
			acao = scanner.next();
			if(acao.equals("a")) {
				ja.selAtk();
			}
			
		}
		scanner.close();

		
		
	}

	
}
