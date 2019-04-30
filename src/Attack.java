public class Attack extends Event{
	private Pokemon pa, pd;
	private Move move;
	private Trainer t;
	
	public Attack(long eventTime, Pokemon pa, Pokemon pd, Move m, Trainer treinador) {
		super(eventTime);
		this.pa = pa;
		this.pd = pd;
		move = m;
		t = treinador;
	}

	public int Dano (Move m, Pokemon a, Pokemon b) {
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


	public void action() {
		int damage;
		int hp;
		damage = this.Dano(move, pa, pd);
		hp = pd.getHp();
		if(hp - damage <= 0) {
			pd.setHp(0);
			System.out.println(pa+ " atacou " +pd+ " com " +move+ "!"+ System.lineSeparator() +pd+ " não tem mais hp");
			System.out.println("Escolha outro pokémon");
			t.getPokeList();
			t.setAtivo(1);
		}
		pd.setHp(hp - damage);
	}


	public String description() {
		String message;
		int hp = pd.getHp();
		if(pd.getHp() == 0) {
			return null;
		}
		else{
			message = pa+ " atacou " +pd+ " com " +move+ "!"+ System.lineSeparator() +pd+ " tem " +hp+ " de vida restando"; 
		}
		return message;
	}

}
