package io.laidani.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
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

	private static boolean isNarcissistic(long n) {
		long i=n;
		long[] chiffres = new long[]{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
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
		System.out.println("The list : "+ values);
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
		
		boolean b = values
					.stream()
					.anyMatch(e -> e%2+e%3+2%5 == 6);
		System.out.println("is there any number which satisfy the condition e%2+e%3+2%5 == 6 ? " + b);
		System.out.println("List of values ordered by [ e1%2*e2%3 - e2%2*e1%3 ] : " +
				values.stream()
				      .sorted((e1,e2) -> e1%2*e2%3 - e2%2*e1%3)
				      .map(n -> n.toString())
				      .collect(Collectors.joining(",","{", "}"))
				);
				
		System.out.println("-----------------------------------------------------------");
		System.out.println("List of numbers <10,1000> skip(5) && limit(95) that are n=0[3] && (n=1[5] || n=2[5]) : " +
				IntStream
						.range(10, 1000)
						.skip(5)
						.limit(95)
						.filter(n -> n%3==0 && ( n%5==1 || n%5==2))
						.collect(ArrayList::new, ArrayList::add, ArrayList::addAll)
			);		
				
		
		System.out.println( "sum of number [0-10] is : " +
		IntStream
		.range(0, 10)
		.sum()
		);
		
		System.out.println("Display Narcissistic Numbers between 0 to 100.000 -----------------");
		IntPredicate predicate = i -> isNarcissistic(i);
		IntPredicate predicate2 = i -> i > 9;
		
		long start = System.currentTimeMillis();
		List<Integer> narNumbers = new ArrayList<>();  
				IntStream	
				            .rangeClosed(0, 100000)
		        			.parallel()
							.filter(predicate.and(predicate2))
                            .forEach(n -> narNumbers.add(n));
		
		long duration1 = System.currentTimeMillis() - start;
		start = System.currentTimeMillis();
		narNumbers.clear();	
		              IntStream
				      .range(0, 100000)
		              .filter(predicate.and(predicate2))
		              .forEach(n -> narNumbers.add(n));
		long duration2 = System.currentTimeMillis() - start;
		
		System.out.println("The is " + narNumbers.size() + " narcissistic numbers between 10 and 100.000");
		System.out.println("Parallel processing took " + duration1 + " ms");
		System.out.println("Sequential processing took " + duration2 + " ms");
		System.out.println("List of narcissistic numbers : " + 
																narNumbers.stream()
																           .sorted()
																           .map(n -> Integer.toString(n))
																           .collect(Collectors.joining(",", "{","}"))           
				);
		
		System.out.println("---------------------------------------------------------------------");
			
		Stream.of("Mohamed","Athyl","Tamim","Evan","Stacy","Alex","Monica","Miles")
		.sorted(Comparator.comparing(String::length))
		.findFirst()
		.ifPresent(System.out::println);
		
		String[] names = {"Mohamed","Athyl","Tamim","Evan","Oliver", "Mark", "Evana", "Sara", "Anita", "Mourad"};
		System.out.println("List of sorted names that started with 'M' --------------------------------------------- ");
		System.out.println("The list : "+ Arrays.stream(names).collect(Collectors.joining(",", "{", "}")));
        System.out.println(
         Arrays.stream(names)
					.filter(n -> n.startsWith("M"))
					.sorted()
					.collect(Collectors.joining(" "))
		);
		
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
		System.out.println(		people
								.stream()
								.map(String::toUpperCase)
								.filter(p -> p.contains("A"))
								.filter(p -> p.startsWith("M"))
								.sorted()
								.collect(Collectors.joining(",", "[", "]"))
				          );	
						
		
		
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
