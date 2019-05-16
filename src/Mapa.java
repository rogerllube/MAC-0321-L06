
public class Mapa {
	int m[][] = new int[][] {
		{1, 0, 0, 0, 1, 1, 0},
		{1, 0, 0, 0, 1, 1, 0},
		{1, 0, 0, 0, 1, 1, 0},
		{1, 0, 0, 0, 1, 1, 0},
		{1, 0, 0, 0, 1, 1, 0},
		{1, 0, 0, 0, 1, 1, 0},
		{1, 0, 0, 0, 1, 1, 0}
	};
	
	public void Print(int a, int b) {
		int i, j;
		System.out.println("");
		for(i = 0; i < 7; i++) {
			System.out.print("|");
			for(j = 0; j < 7; j++) {
				if(i == a && j == b) {
					System.out.print(" P ");
				}
				else if(m[i][j] ==  1){
					System.out.print(" * ");
				}
				else {
					System.out.print("   ");
				}
				System.out.print("|");
			}
			System.out.println("");
		}
	}
}
