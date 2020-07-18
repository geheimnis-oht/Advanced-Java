
import java.util.function.Predicate;

import Utility.Gender;
import Utility.Person;

public class PersonPredicates {
	
	public static final int FORTUNE = 500000;
	public static final int AGE = 50;
		
	public static Predicate<Person> femalePerson = p -> p.getGender().equals(Gender.FEMALE);
	public static Predicate<Person> oldPerson = p -> p.getAge() > AGE;
	public static Predicate<Person> fortunatePerson = p -> p.getFortune() > FORTUNE;
}
