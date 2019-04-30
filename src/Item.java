
public class Item {
	private String name;
	private int recover;
	private int quantidade;
	
	public Item(String nome, int rec, int quant) {
		name = nome;
		recover = rec;
		quantidade = quant;
	}
	
	public String getName() {
		return name;
	}
	
	public int getRec() {
		return recover;
	}
	
	public int getQuant() {
		return quantidade;
	}
}
