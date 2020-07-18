
public class Test {

	public static void main(String[] args) {
		int i=153;
		int[] chiffres = new int[]{ 0, 0, 0, 0 }; 
		int j=0;
		while (i != 0) {
			chiffres[j++] = i%10;
			i = (i-i%10)/10;	
		}
		for (int j2 = 0; j2 < chiffres.length; j2++) {
			System.out.println(chiffres[j2]);
		}
		i=0;
		for (int k = 0; k < chiffres.length; k++) {
		  i+=Math.pow(chiffres[k], j);		  
		}
		System.out.println("i="+i);
        
	}

}
