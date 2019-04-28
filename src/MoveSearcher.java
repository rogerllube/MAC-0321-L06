import java.io.File;
import java.io.IOException;
import java.util.Scanner;

abstract class MoveSearcher {
	
	public static Move pesquisaMove(String name) {
		System.out.println("Digite o nome do move");
		File file = new File("MoveList.txt");
		Move mov;
	    Scanner scanner = null;
	    int type, dam, phys; 
	    try {
	        scanner = new Scanner(file);

	        while (scanner.hasNext()) {
	            final String lineFromFile = scanner.nextLine();
	            if (lineFromFile.contains(name)) {
	                System.out.println("I found " + name);
	                type = Integer.parseInt(scanner.nextLine());
	                dam = Integer.parseInt(scanner.nextLine());
	                phys = Integer.parseInt(scanner.nextLine());
	                mov = new Move(name, type, dam, phys);
	                scanner.close();
	                return mov;
	            }
	        }
	    } catch (IOException e) {
	        System.out.println(" cannot write to file " + file.toString());
	    	}
		return null;
	}
}
