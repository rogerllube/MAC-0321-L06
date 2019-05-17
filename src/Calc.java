public abstract class Calc {

	public static int Dano (Move m, Pokemon a, Pokemon b) {
		Tabela typeEf = new Tabela();
		double danoD, atk, def;
		int danoI;
		int phys = m.getPhysical();
		int dam = m.getDamage();
		int typ = m.getType();
		double modifier;
		
		if(b.getType2() == -1) {
			if(phys == 1) {
				atk = a.getAttack();
				def = b.getDefense();
				modifier = typeEf.tab[typ][b.getType1()];
				danoD = (((42 * dam * (atk/def)) / 50 + 2) * modifier);
				danoI = (int) Math.round(danoD);
				if(modifier == 0.0)
					System.out.println("It doesn't affect " + b.getName());
				else if(modifier >0.0 && modifier < 1.0) 
					System.out.println("It's not very effective!");
				else if (modifier > 1.0)
					System.out.println("It's super effective!");
				return danoI;
			}
			else {
				atk = a.getSpAttack();
				def = b.getSpDefense();
				modifier =  typeEf.tab[typ][b.getType1()];
				danoD = (((42 * dam * (atk/def)) / 50 + 2) * modifier);
				danoI = (int) Math.round(danoD);
				if(modifier == 0.0)
					System.out.println("It doesn't affect " + b.getName());
				else if(modifier >0.0 && modifier < 1.0) 
					System.out.println("It's not very effective!");
				else if (modifier > 1.0)
					System.out.println("It's super effective!");
				return danoI;
			}
		}
		else {
			if(phys == 1) {
				atk = a.getAttack();
				def = b.getDefense();
				modifier = typeEf.tab[typ][b.getType1()] * typeEf.tab[typ][b.getType2()];
				danoD = (((42 * dam * (atk/def)) / 50 + 2) * modifier);
				danoI = (int) Math.round(danoD);
				if(modifier == 0.0)
					System.out.println("It doesn't affect " + b.getName());
				else if(modifier >0.0 && modifier < 1.0) 
					System.out.println("It's not very effective!");
				else if (modifier > 1.0)
					System.out.println("It's super effective!");
				return danoI;
			}
			else {
				atk = a.getSpAttack();
				def = b.getSpDefense();
				modifier = typeEf.tab[typ][b.getType1()] * typeEf.tab[typ][b.getType2()];
				danoD = (((42 * dam * (atk/def)) / 50 + 2) * modifier);
				danoI = (int) Math.round(danoD);
				if(modifier == 0.0)
					System.out.println("It doesn't affect " + b.getName());
				else if(modifier >0.0 && modifier < 1.0) 
					System.out.println("It's not very effective!");
				else if (modifier > 1.0)
					System.out.println("It's super effective!");
				return danoI;
			}
		}
	}
}
