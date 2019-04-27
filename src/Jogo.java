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
		for(i = 1;i < 6 && done.equals("s"); i++) {
			System.out.println("Treinador 1: Digite o nome do seu pokemon numero " + i);
			nomePoke = scanner.next();
			succ = ja.addPokemon(nomePoke);
			while(succ == false) {
				System.out.println("Nome invalido, digite novamente");
				nomePoke = scanner.next();
				succ = ja.addPokemon(nomePoke);
			}
			System.out.println("Pokemon adicionado com sucesso. Digite s para adicionar outro ou qualquer outra coisa para concluir");
			done = scanner.next();
		}
		System.out.println("Treinador 1, seus Pokemon sao:");
		//ja.getPokeList();
		for(i = 1;i < 6 && done.equals("s"); i++) {
			System.out.println("Treinador 2: Digite o nome do seu pokemon numero " + i);
			nomePoke = scanner.next();
			succ = jb.addPokemon(nomePoke);
			while(succ == false) {
				System.out.println("Nome invalido, digite novamente");
				nomePoke = scanner.next();
				succ = jb.addPokemon(nomePoke);
			}
			System.out.println("Pokemon adicionado com sucesso. Digite s para adicionar outro ou qualquer outra coisa para concluir");
			done = scanner.next();
		}
		System.out.println("Treinador 2, seus Pokemon sao:");
		//jb.getPokeList();
		
		

		scanner.close();
	}

	
}
