import java.io.File;
import java.io.IOException;
import java.util.Scanner;

abstract class MoveSearcher {
	
	public Move pesquisaMove() {
		System.out.println("Digite o nome do move");
		File file = new File("MoveList.txt");
		Move mov;

	    Scanner kb = new Scanner(System.in);

	    String name = kb.next();
	    Scanner scanner;
	    int type, dam, phys; 
	    try {
	        scanner = new Scanner(file).useDelimiter( ",");

	        while (scanner.hasNext()) {
	            final String lineFromFile = scanner.nextLine();
	            if (lineFromFile.contains(name)) {
	                System.out.println("I found " + name);
	                type = Integer.parseInt(scanner.nextLine());
	                dam = Integer.parseInt(scanner.nextLine());
	                phys = Integer.parseInt(scanner.nextLine());
	                mov = new Move(name, type, dam, phys);
	                return mov;
	            }
	        }
	    } catch (IOException e) {
	        System.out.println(" cannot write to file " + file.toString());
	    	}
		return null;
	}
}
