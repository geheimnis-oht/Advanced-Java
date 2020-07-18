package io.laidani.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainClass {

	private static boolean isNarcissistic(Integer n) {
		int i=n;
		int[] chiffres = new int[]{ 0, 0, 0, 0, 0, 0, 0, 0 };
		int j=0;
		while (i != 0) {
			chiffres[++j] = i%10;
			i = (i-i%10)/10;	
		}
		i=0;
		for (int k = 0; k < chiffres.length; k++) {
			i+=Math.pow(chiffres[k], j);		  
		}
		
		return i == n;
	}
	
	private static boolean isEven(Integer i) {
		return i%2 == 0;
	}
	
	private static Integer totalValues(List<Integer> numbers, Predicate<Integer> selector) {		
		return numbers.stream()
		              .filter(selector)
		              .reduce(0, Math::addExact);
	}
	
	public static void main(String[] args) {
		List<Integer> values = Arrays.asList(2,4,3,8,3,4,2,0,10,8,4,7,12,9,3,2,1,30,20,44,5);
		System.out.println("Display the square of first even number bigger than 4 from the list");
		System.out.println(values
		.stream()
		.filter(MainClass::isEven)     //.filter(e -> e%2 == 0)
		.filter(e -> e > 4)
		.map(e -> Math.pow(e, 2))
		.findFirst()
		.get());
		
		System.out.println("---------------------- Streams with Predicates --------------------------");
		
		System.out.println("Sum of all numbers is :" + totalValues(values, e -> true));
		System.out.println("Sum of even numbers is :" + totalValues(values, e -> e%2 == 0));
		
		System.out.println("-----------------------------------------------------------");
		IntStream
		.range(10, 23)
		.skip(5)
		.forEach(System.out::println);
		
		System.out.println( "sum of number [0-10] is : " +
		IntStream
		.range(0, 10)
		.sum()
		);
		
		System.out.println("Display Narcissistic Numbers between 0 to 10000 -----------------");
		IntPredicate predicate = i -> isNarcissistic(i);
		IntPredicate predicate2 = i -> i > 9;
		
		IntStream
		.range(0, 10000)
		.filter(predicate.and(predicate2))
		.forEach(System.out::println);
		
		Stream.of("Mohamed","Athyl","Tamim","Evan")
		.sorted()
		.findFirst()
		.ifPresent(System.out::println);
		
		System.out.println("List of sorted names that started with 'M' --------------------------------------------- ");
		String[] names = {"Mohamed","Athyl","Tamim","Evan","Oliver", "Mark", "Evana", "Sara", "Anita", "Mourad"};
		Arrays.stream(names)
		.filter(n -> n.startsWith("M"))
		.sorted()
		.forEach(System.out::println);
		
		System.out.println("Average of square if distinct numbers      --------------------------------------------- ");
		Arrays.stream(new int[] {3, 4, 6, 6, 6, 6, 6, 7, 9, 2, 5, 5, 7})
		.distinct()
		.map(i -> i * i)
		.average()
		.ifPresent(System.out::println);
		
		System.out.println("Display summary statistics ------------------------------------------");
		IntSummaryStatistics summary = IntStream.range(0, 100).summaryStatistics();
		System.out.println(summary);
		
		System.out.println("List of sorted names that contain 'A' and started with 'M' ------------------------------------- ");
		List<String> people = Arrays.asList("Mohamed","Athyl","Tamim","Evan","Oliver", "Mark", "Evana", "Sara", "Anita", "Mourad");
		people
		.stream()
		.map(String::toUpperCase)
		.filter(p -> p.contains("A"))
		.filter(p -> p.startsWith("M"))
		.sorted()
		.forEach(System.out::println);
		
		/*
		 *  Valid Data example = A,45,4.33
		 */
		
		try {
			/*
			 * Count valid data lines
			 */
			Stream<String> data = Files.lines(Paths.get("c:\\temp\\data.txt"));		
			int count = (int) data
			.map(d -> d.split(","))
			.filter(t -> t.length == 3)
			.count();
			System.out.println("Numbers of valid rows is : " + count);
			data.close();
			
			/*
			 * Display valid data with second elements > 15
			 */
			System.out.println("List of valid data with second element > 15   ---------------------------- ");
			Stream<String> data2 = Files.lines(Paths.get("c:\\temp\\data.txt"));	
			data2
			.map(d -> d.split(","))
			.filter(t -> t.length == 3)
			.filter(i -> Integer.parseInt(i[1]) > 15)
			.forEach(x -> System.out.println(x[0] + " " + x[1] + " " + x[2]));
			data2.close();
			
			System.out.println("List of valid names mapped  --------------------------------------------- ");
			Stream<String> data3 = Files.lines(Paths.get("c:\\temp\\data.txt"));
			Map<String, Integer> map = new HashMap<>();
			map = data3
			.map(d -> d.split(","))
			.filter(t -> t.length == 3)
			.filter(i -> Integer.parseInt(i[1]) > 15)
			.collect(Collectors.toMap(x -> x[0], 
					                  x->Integer.parseInt(x[1]
					                 )
					 ));
			data3.close();
			for (String key : map.keySet()) {
				System.out.println(key + " " + map.get(key));
			}
					
		} catch (IOException e) {		
			System.err.println(e.getMessage());
		} 				
		
	}
	
	

}
