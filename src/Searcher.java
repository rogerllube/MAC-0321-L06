import java.io.File;
import java.io.IOException;
import java.util.Scanner;

abstract class Searcher{
 
	public static Pokemon pesquisaPoke() {
		    System.out.println("Digite o nome do pokemon");
			File file = new File("data.txt");
			Pokemon poke;

		    Scanner kb = new Scanner(System.in);

		    String name = kb.next();
		    Scanner scanner;
		    int dexN, type1, type2, hp, atk, def, spatk, spdef, speed; 
		    try {
		        scanner = new Scanner(file).useDelimiter( ",");

		        while (scanner.hasNext()) {
		            final String lineFromFile = scanner.nextLine();
		            if (lineFromFile.contains(name)) {
		                System.out.println("I found " + name);
		                dexN = Integer.parseInt(scanner.nextLine());
		                type1 = Integer.parseInt(scanner.nextLine());
		                type2 = Integer.parseInt(scanner.nextLine());
		                hp = Integer.parseInt(scanner.nextLine());
		                atk = Integer.parseInt(scanner.nextLine());
		                def = Integer.parseInt(scanner.nextLine());
		                spatk = Integer.parseInt(scanner.nextLine());
		                spdef = Integer.parseInt(scanner.nextLine());
		                speed = Integer.parseInt(scanner.nextLine());
		                poke = new Pokemon(name, dexN, type1, type2, hp, atk, def, spatk, spdef, speed);
		                return poke;
		            }
		        }
		    } catch (IOException e) {
		        System.out.println(" cannot write to file " + file.toString());
		    	}
			return null;
	}
}



