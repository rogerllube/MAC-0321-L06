import java.util.Scanner;

public class Jogo {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		int escolha;
		while(true) {
			System.out.println("Digite [1] para jogar uma batalha 1v1 ou digite [2] para jogar o modo de 1 jogador");
			escolha = scanner.nextInt();
			if (escolha == 1) {
				Battle battle = new Battle(scanner);
				battle.addEvent(battle.new Begin());
				battle.run();
			}
			if(escolha == 2) {
				Wild adventure = new Wild(scanner);
				adventure.addEvent(adventure.new Begin());
				adventure.run();
			}
			else
				break;
		}
	}
}
