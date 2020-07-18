import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import Utility.Gender;
import Utility.Person;

public class MainClass {
	
	public static void main(String[] args) {
		
		/*
		 * TECH : test Integer Predicates
		 */
		
		List<Integer> numbers = Arrays.asList(-2,4,7,0,6,4,-3,-2,-10,6,9,4);
		System.out.println("'Positive numbers :'");
		List<Integer> positiveNumbers = getFilteredNumbers(numbers, IntegerPredicates.positiveNumberPredicate);
		positiveNumbers.forEach(System.out::println);
		
		System.out.println("'Odd numbers :'");
		List<Integer> oddNumbers = getFilteredNumbers(numbers, IntegerPredicates.oddNumberPredicate);
		oddNumbers.forEach(System.out::println);
		
		System.out.println("Even and positive numbers:");
		List<Integer> evenNumbers = getFilteredNumbers(numbers, IntegerPredicates.evenNumberPredicate
				                                                .and(IntegerPredicates.positiveNumberPredicate));
		evenNumbers.forEach(System.out::println);
		
		Integer i = 5;
		System.out.println("numbers bigger than :" + i);
		List<Integer> numbersBiggerThan = getFilteredNumbers(numbers, IntegerPredicates.numberBiggerThan(i));
		numbersBiggerThan.forEach(System.out::println);
		
		/*
		 * TECH : Test Person Object Predicates
		 */
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("Mohamed","LAIDANI",Gender.MALE,37, 999000));
		persons.add(new Person("Malik","OKHRAVI",Gender.MALE,44, 456000));
		persons.add(new Person("Oliver","EVANI",Gender.MALE,42, 56600));
		persons.add(new Person("Sara","TITANOVA",Gender.FEMALE,23,234000));
		persons.add(new Person("Hilen","YAKIN",Gender.FEMALE,19, 567000));
		persons.add(new Person("Margot","BELLE",Gender.FEMALE,24, 432000));
		persons.add(new Person("Mark","ZOKERBERG",Gender.FEMALE,34, 143200));
		persons.add(new Person("Evan","TERRIBLE",Gender.FEMALE,54, 665000));
		persons.add(new Person("Rachelle","WEIZ",Gender.FEMALE,48, 865000));
		persons.add(new Person("Eva","GREEN",Gender.FEMALE,32, 789000));		
				
		System.out.println("Persons whom are not Female and fortunate are :");
		List<Person> femalePersons = getFilteredPersons(persons, PersonPredicates.femalePerson
				                                                 .and(PersonPredicates.fortunatePerson)
				                                                 .negate());
		femalePersons.forEach(System.out::println);
			
	}
	
	public static List<Integer> getFilteredNumbers(List<Integer> numbers, Predicate<Integer> predicate){
		List<Integer> filteredNumbers = new ArrayList<>();
		
		for (Integer i : numbers) {
			if(predicate.test(i)) {
				filteredNumbers.add(i);
			}
		}		
		return filteredNumbers;
	}
	
	public static List<Person> getFilteredPersons(List<Person> persons, Predicate<Person> predicate){
		List<Person> filteredPersons = new ArrayList<>();
		
		for (Person p : persons) {
			if(predicate.test(p)) {
				filteredPersons.add(p);
			}
		}		
		return filteredPersons;
	}
		
}


