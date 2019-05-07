import java.io.File;
import java.io.IOException;
import java.util.Scanner;

abstract class Searcher{
 
	public static Pokemon pesquisaPoke(int number) {
			File file = new File("dex.txt");
			Pokemon poke;
			String name;
		    Scanner scanner = null;
		    Move a, b, c, d;
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
		                hp = (Integer.parseInt(scanner.nextLine())*2)+110;
		                atk = (Integer.parseInt(scanner.nextLine())*2)+5;
		                def = (Integer.parseInt(scanner.nextLine())*2)+5;
		                spatk = (Integer.parseInt(scanner.nextLine())*2)+5;
		                spdef = (Integer.parseInt(scanner.nextLine())*2)+5;
		                speed = (Integer.parseInt(scanner.nextLine())*2)+5;
		                poke = new Pokemon(name, dexN, type1, type2, hp, atk, def, spatk, spdef, speed);
		                a = new Move(scanner.nextLine(), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));
		                b = new Move(scanner.nextLine(), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));
		                c = new Move(scanner.nextLine(), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));
		                d = new Move(scanner.nextLine(), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));
		                poke.setMove(a, b, c, d);
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



