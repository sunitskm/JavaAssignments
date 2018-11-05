package streamsexample;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OperationStreams {
	public static void main(String[] args) {
		List<Person> listPersons = populateListData();
		System.out.println("-------------------Obtain US and non US based persons using partitionBy & groupingBy----------");
		System.out.println(listPersons.stream()
				.collect(Collectors
				.partitioningBy((Person p) -> p.getCountry().equals("USA"))));
		System.out.println(listPersons.stream()
				.collect(Collectors
				.groupingBy((Person p) -> p.getCountry().equals("USA"))));
		
		System.out.println("-------------------count US and non US based persons using partitionBy and groupBy-----------------");
		System.out.println(listPersons.
				stream().
				collect(Collectors.
						partitioningBy((Person p) -> p.getCountry().equals("USA"),Collectors.counting())));
		
		System.out.println(listPersons.
				stream().
				collect(Collectors.
						groupingBy((Person p) -> p.getCountry().equals("USA"),Collectors.counting())));
		System.out.println("-------------------Obtain US and non US based and map those names to uppercase-----------");
		System.out.println("------------------Obtain the persons in each country and count them using groupingBy-----");
		System.out.println("-----------------Obtain the names in each country using groupingBy and map names to uppercase using mapping--------------");
	}
	
	public static List<Person> populateListData(){
		Person person1 = new Person("Ajay", "India");
		Person person2 = new Person("John", "USA");
		Person person3 = new Person("Sarah","USA");
		Person person4 = new Person("Adam","USA");
		Person person5 = new Person("Rahul","India");
		Person person6 = new Person("Antoine","France");
		Person person7 = new Person("Kylian","France");
		Person person8 = new Person("Bastian","Germany");
		Person person9 = new Person("Danielle","USA");
		List<Person> listPerson = new ArrayList<>();
		listPerson.add(person1);
		listPerson.add(person2);
		listPerson.add(person3);
		listPerson.add(person4);
		listPerson.add(person5);
		listPerson.add(person6);
		listPerson.add(person7);
		listPerson.add(person8);
		listPerson.add(person9);
		return listPerson;
	}

}
