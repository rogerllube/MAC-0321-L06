
public class Item {
	private String name;
	private int recover;
	private int quantidade;
	
	public void setName(String nome) {
		name = nome;
	}
	
	public void setRec(int rec) {
		recover = rec;
	}
	
	public void setQuant(int quant) {
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
