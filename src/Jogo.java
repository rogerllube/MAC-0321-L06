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
			Trainer j1 = new Trainer(name);
			Trainer j2 = new Trainer(name2);
		
		}
		System.out.println("Pokemon disponiveis:");
		Lista.listar();
		int i;
		boolean succ;
		String nomePoke, done = "s";
		for(i = 1;i < 6 && done.equals("s"); i++) {
			System.out.println("Treinador 1: Digite o nome do seu pokemon numero " + i);
			nomePoke = scanner.next();
			succ = j1.addPokemon(nomePoke);
			while(succ = false) {
				System.out.println("Nome invalido, digite novamente");
				nomePoke = scanner.next();
				succ = j1.addPokemon(nomePoke);100600
			}
			System.out.println("Pokemon adicionado com sucesso. Digite s para adicionar outro ou qualquer outra coisa para concluir");
			done = scanner.next();
		}
		for(i = 1;i < 6 && done.equals("s"); i++) {
			System.out.println("Treinador 2: Digite o nome do seu pokemon numero " + i);
			nomePoke = scanner.next();
			succ = j2.addPokemon(nomePoke);
			while(succ = false) {
				System.out.println("Nome invalido, digite novamente");
				nomePoke = scanner.next();
				succ = j2.addPokemon(nomePoke);
			}
			System.out.println("Pokemon adicionado com sucesso. Digite s para adicionar outro ou qualquer outra coisa para concluir");
			done = scanner.next();
		}
		

		scanner.close();
	}

	
}
