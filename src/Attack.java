public class Attack {
	
	public int Dano (Move m, Pokemon a, Pokemon b) {
		Tabela typeEf = new Tabela();
		double danoD, atk, def;
		int danoI;
		
		if(b.getType2() == -1) {
			if(m.physical == 1) {
				atk = a.getAttack();
				def = b.getDefense();
				danoD = (((42*m.damage*(atk/def))/50 + 2) * typeEf.tab[m.type][b.getType1()]);
				danoI = (int) Math.round(danoD);
				return danoI;
			}
			else {
				atk = a.getSpAttack();
				def = b.getSpDefense();
				danoD = (((42*m.damage*(atk/def))/50 + 2) * typeEf.tab[m.type][b.getType1()]);
				danoI = (int) Math.round(danoD);
				return danoI;
			}
		}
		else {
			if(m.physical == 1) {
				atk = a.getAttack();
				def = b.getDefense();
				danoD = (((42*m.damage*(atk/def))/50 + 2) * typeEf.tab[m.type][b.getType1()] * typeEf.tab[m.type][b.getType2()]);
				danoI = (int) Math.round(danoD);
				return danoI;
			}
			else {
				atk = a.getSpAttack();
				def = b.getSpDefense();
				danoD = (((42*m.damage*(atk/def))/50 + 2) * typeEf.tab[m.type][b.getType1()] * typeEf.tab[m.type][b.getType2()]);
				danoI = (int) Math.round(danoD);
				return danoI;
			}
		}
	}

}
