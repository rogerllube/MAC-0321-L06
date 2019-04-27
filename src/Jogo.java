import java.util.Scanner;

public class Jogo {

	public static void main(String[] args) {
		System.out.println("Bem vindo, jogador 1. Digite o seu nome de treinador");
		Scanner scanner = new Scanner(System.in);
		String name = scanner.next();
		boolean con = false;
		String cfm;
		while(con == false) {
			System.out.println("Seu nome e " + name +"? Digite s para confirmar e n para alterar.");
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
			System.out.println("Seu nome e " + name2 +"? Digite s para confirmar e n para alterar.");
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
		

		scanner.close();
	}

	
}
