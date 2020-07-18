package io.laidani.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MainClass {

	public static void main(String[] args) {
		/*
		 * TECH - HashSet doesn't allow double & doesn't remember insertion order
		 *        LinkedHashSet do remember insertion order
		 *        TreeSet store data in ASC order by default
		 */
		
		List<String> values = new ArrayList<>();
		values.add("Mohamed");
		values.add("Eva");
		values.add("Vlad");
		values.add("Rohit");
		values.add("Marie-Antoinette");
		values.add("Isaac");
		values.add("Mike");
		values.add("Yamakato");
		values.add("Irfan");
		values.add("Chao");
		values.add("Abraham");
		values.add("Mao");
			
		
		System.out.println("--------------------------- Display data from HashSet --------------------------");
		Set<String> names  = new HashSet<>();
		names.addAll(values);
		names.stream().forEach(System.out::println); 
		
		System.out.println("--------------------------- Display data from LinkedHashSet --------------------------");
		
		Set<String> namesLink  = new LinkedHashSet<>();
		namesLink.addAll(values);
		namesLink.stream().forEach(System.out::println);
		
		System.out.println("--------------------------- Display data from TreeSet --------------------------");
		
		Set<String> namesTree  = new TreeSet<>();
		namesTree.addAll(values);
		namesTree.stream().forEach(System.out::println);
		
		System.out.println("---------------- Display data from TreeSet with Custom Comparator ----------------");
		
		Comparator<String> comparator = new Comparator<String>() {

			@Override
			public int compare(String str1, String str2) {
				return str1.length()-str2.length();
			}
		};
		Set<String> namesTreeS  = new TreeSet<>(comparator);
		namesTreeS.addAll(values);
		namesTreeS.stream().forEach(System.out::println);	
		
		System.out.println("---------------- Opertaions on Sets  ----------------");
		Set<String> set0 = new HashSet<>(Arrays.asList("Mohamed","Athyl","Tamim","Oliver","Charlize","Eva","Vlad"));
		Set<String> set1 = new HashSet<>(set0);
		Set<String> set2 = new HashSet<>(Arrays.asList("Mohamed","Athyl","Tamim","Chao","Boris","Rohit","Iklil"));
		System.out.println("----------Set1----------------");
		set1.stream().forEach(System.out::println);
		System.out.println("----------Set2----------------");
		set2.stream().forEach(System.out::println);
		
		System.out.println("Intersection of <Set1> & <Set2> will outcome : ");
		set1.retainAll(set2);
		set1.stream().forEach(System.out::println);
		System.out.println("Union of <Set1> & <Set2> will outcome : ");
		set1.removeIf(f -> true);
		set1.addAll(set0);
		set1.addAll(set2);		
		set1.stream().forEach(System.out::println);
		System.out.println("Difference between <Set1> & <Set2> will outcome : ");
		set1.clear();
		set1.addAll(set0);
		set1.removeAll(set2);		
		set1.stream().forEach(System.out::println);
		
		/*
		 * TECH : Map -> HashMap, LinkedHashMap, TreeMap
		 */
		
		System.out.println("---------------- Opertaions on Maps  ----------------");
		
		Map<String, Integer> map = new TreeMap<String, Integer>(comparator);
		
		map.put("One", 1);
		map.put("Eleven", 11);
		map.put("One hundred fifteen", 115);
		map.put("Fifty nine", 59);
		map.put("Thirty eight", 38);
		map.put("thousand and Seventy three", 1073);
		
		
		System.out.println("---------------- TreeMap with Comparator  ----------------");
		System.out.println("Eleven element is : " + map.get("Eleven"));
		System.out.println("Elements of the Map : " + map.entrySet());
		
		System.out.println(map.containsKey("Seven")? "The map contains 'Seven' key.":"The map doesn't contains 'Seven' key.");
		System.out.println("The map contains keys : " + map.keySet() + " and values : " + map.values());
		
		
		System.out.println("---------------- TreeMap  ----------------");
		Map<String, Integer> mapH = new TreeMap<String, Integer>();
		mapH.putAll(map);
		System.out.println("Eleven element is : " + mapH.get("Eleven"));
		mapH.remove("Eleven");
		System.out.println("Elements of the Map : " + mapH.entrySet());
		mapH.put("Seven", 7);
		
		System.out.println(mapH.containsKey("Seven")? "The map contains 'Seven' key.":"The map doesn't contains 'Seven' key.");
		System.out.println("The map contains keys : " + mapH.keySet() + " and values : " + mapH.values());
		
		System.out.println("---------------- LinkedHashMap  ----------------");
		Map<String, Integer> mapL = new LinkedHashMap<String, Integer>();
		mapL.putAll(map);
		System.out.println("Eleven element is : " + mapL.get("Eleven"));
		mapL.remove("Eleven");
		
		System.out.println("Elements of the Map : " + mapL.entrySet());
		mapL.put("Seven", 7);
		mapL.put("Twenty Thousand and two hundred sixty four", 20264);
		
		System.out.println(mapL.containsKey("Seven")? "The map contains 'Seven' key.":"The map doesn't contains 'Seven' key.");
		System.out.println("The map contains keys : " + mapL.keySet() + " and values : " + mapL.values());	
	}
}
