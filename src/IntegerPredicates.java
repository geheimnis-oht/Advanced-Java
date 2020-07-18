import java.util.function.Predicate;

public class IntegerPredicates {
	
	/*public static Predicate<Integer> positiveNumberPredicate = new Predicate<Integer>() {
		public boolean test(Integer i) {
			return i > 0;
		}
	};*/
	
	public static Predicate<Integer> positiveNumberPredicate = n -> n > 0;
	
	
	/*public static Predicate<Integer> oddNumberPredicate = new Predicate<Integer>() {
		public boolean test(Integer i) {
			return i % 2 == 0;
		}
	};*/
	
	public static Predicate<Integer> oddNumberPredicate = n -> n%2 == 0;
	
	public static Predicate<Integer> evenNumberPredicate = n -> n%2 == 1;
	
	public static Predicate<Integer> numberBiggerThan (Integer k) {
		return n -> n > k;
	}
	
	public static Predicate<Integer> narcissisticNumberPredicate = new Predicate<Integer>() {
		public boolean test(Integer n) {
			
			int i=n;
			int[] chiffres = {}; 
			int j=0;
			while (i != 0) {
				chiffres[++j] = i%10;
				i = (i-i%10)/10;	
			}
			i=0;
			for (int k = 0; k < chiffres.length; k++) {
			  i+=chiffres[k]^j;		  
			}
			
			return i == n;
		} 
	};
}
