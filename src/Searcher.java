import java.io.File;
import java.io.IOException;
import java.util.Scanner;

abstract class Searcher{
 
	public static Pokemon pesquisaPoke(int number) {
			File file = new File("dex.txt");
			Pokemon poke;
			String name;
		    Scanner scanner = null;
		    int dexN, type1, type2, hp, atk, def, spatk, spdef, speed; 
		    try {
		        scanner = new Scanner(file);

		        while (scanner.hasNext()) {
		            final String lineFromFile = scanner.nextLine();
		            if (lineFromFile.equals("#"+number)) {
		                name = scanner.nextLine();
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
		                scanner.close();
		                return poke;
		            }
		        }
		    } catch (IOException e) {
		        System.out.println(" cannot write to file " + file.toString());
		    	}
		    
		    return null;
			
	}
}



