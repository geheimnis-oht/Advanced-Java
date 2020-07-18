package io.laidani.streams.collectors;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import Utility.Student;

public class MainClass {

	public static void main(String[] args) {
	 List<Student> students = populateWithData();
	 
	 
	 System.out.println("---------------- obtain 'US' and non 'US' based students using partioningBy & groupingBy -------------------------");
	 System.out.println(students.stream().collect(Collectors.partitioningBy((Student s) -> s.getCountry().equals("US"))));
	 System.out.println(students.stream().collect(Collectors.groupingBy(s -> s.getCountry().equals("US"))));
	 
	 System.out.println("---------------- count 'DE' and non 'DE' based students using partioningBy & groupingBy -------------------------");
	 System.out.println(students.stream().collect(Collectors.partitioningBy((Student s) -> s.getCountry().equals("DE"), Collectors.counting())));
	 System.out.println(students.stream().collect(Collectors.groupingBy(s -> s.getCountry().equals("DE"), Collectors.counting())));
	 
	 System.out.println("---------------- counting students of each country using groupingBy -------------------------");
	 System.out.println(students.stream().collect(Collectors.groupingBy((Student s) -> s.getCountry())));
	 System.out.println(students.stream().collect(Collectors.groupingBy((Student s) -> s.getCountry(), Collectors.counting())));
	 
	 System.out.println("---------------- obtain 'US' and non 'US' based students using partioningBy & map their name toUpperCase ------------------");
	 System.out.println(students.stream().collect(Collectors.partitioningBy((Student s) -> s.getCountry().equals("DE"), 
			                                                                  Collectors.mapping((Student s) -> s.getName().toUpperCase(), 
			                                                                                        Collectors.toList()))));

	}
	
	public static List<Student> populateWithData() {
		List<Student> students = new ArrayList<>();
		students.add(new Student("Mohamed", "DZ"));
		students.add(new Student("Isaac", "DE"));
		students.add(new Student("Mike", "US"));
		students.add(new Student("Sara", "DE"));
		students.add(new Student("Valentin", "FR"));
		students.add(new Student("Gallager", "UK"));
		students.add(new Student("Billel", "DE"));
		students.add(new Student("Mohamed", "TN"));
		students.add(new Student("Dalila", "IT"));
		students.add(new Student("Farid", "DZ"));
		students.add(new Student("Rohit", "IN"));
		students.add(new Student("Sachin", "IN"));
		students.add(new Student("Alberto", "IT"));
		students.add(new Student("Ilyes", "DZ"));
		students.add(new Student("Athyl", "DE"));
		students.add(new Student("Tamim", "UK"));
		students.add(new Student("Vladimir", "RUS"));
		students.add(new Student("Paola", "BR"));
		return students;
	}
}
