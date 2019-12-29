package happyeliminating;

public class a {
	public static void main(String[] args) {
//		System.out.print(Math.round(Math.random()*3));
		int count[] = new int[] {0,0,0,0};
		for(int i = 0; i < 10000000 ; ++ i) {
			count[(int) Math.floor(Math.random()*4)] ++;
		}
		System.out.println(count[0]);
		System.out.println(count[1]);
		System.out.println(count[2]);
		System.out.println(count[3]);
		
	}
}
