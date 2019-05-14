public abstract class Attack {

	public static int Dano (Move m, Pokemon a, Pokemon b) {
		Tabela typeEf = new Tabela();
		double danoD, atk, def;
		int danoI;
		int phys = m.getPhysical();
		int dam = m.getDamege();
		int typ = m.getType();
		
		if(b.getType2() == -1) {
			if(phys == 1) {
				atk = a.getAttack();
				def = b.getDefense();
				danoD = (((42 * dam * (atk/def)) / 50 + 2) * typeEf.tab[typ][b.getType1()]);
				danoI = (int) Math.round(danoD);
				return danoI;
			}
			else {
				atk = a.getSpAttack();
				def = b.getSpDefense();
				danoD = (((42 * dam * (atk/def)) / 50 + 2) * typeEf.tab[typ][b.getType1()]);
				danoI = (int) Math.round(danoD);
				return danoI;
			}
		}
		else {
			if(phys == 1) {
				atk = a.getAttack();
				def = b.getDefense();
				danoD = (((42 * dam * (atk/def)) / 50 + 2) * typeEf.tab[typ][b.getType1()] * typeEf.tab[typ][b.getType2()]);
				danoI = (int) Math.round(danoD);
				return danoI;
			}
			else {
				atk = a.getSpAttack();
				def = b.getSpDefense();
				danoD = (((42 * dam * (atk/def)) / 50 + 2) * typeEf.tab[typ][b.getType1()] * typeEf.tab[typ][b.getType2()]);
				danoI = (int) Math.round(danoD);
				return danoI;
			}
		}
	}
}
