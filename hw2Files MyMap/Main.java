//////////////////////////////////////////////////////////////////////////// 
// 
// Brandon Sweeney 
//  Assignment 2 hw2files 
// 9 - 26 - 2019
// 
////////////////////////////////////////////////////////////////////////////
package map;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;


public class Main
{
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
// 
// DESCRIPTION: This program makes some MyMap objects
//      and store two of them on a binary file.
//  
// INPUTS:  Values put into the MyMap objects.
// 
// OUTPUTS:  The program displays values in the
//      MyMap objects.
// 
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    
	public static void main(String[] args)
	{
		// Test program maps from an Integer ID to a String name
		MyMap<Integer,String> map = new MyMap<Integer,String>();

		map.addOrUpdateMap(30510203, "Bill");
		map.addOrUpdateMap(31253507, "Aaron");
		map.addOrUpdateMap(31515824, "Charles");

		System.out.println("Contains 31515824? " +
				(map.getData(31515824) == null ? "No" : "Yes"));
		System.out.println("Contains 10? " +
				(map.getData(10) == null ? "No" : "Yes"));

		System.out.println("\nAll keys and values:");
		for (Integer i : map.getKeys())
			System.out.println(i + " " + map.getData(i));

		// Test of cloning
		// YOU MUST FIX THE CLONE METHOD IN MYMAP TO MAKE THIS WORK
		System.out.println();
		MyMap<Integer,String> map2 = map.clone();
		// change contents of map
		map.addOrUpdateMap(31510203, "WILLIAM");
		System.out.println("\nAll map2 keys and values:");
		for (Integer i : map2.getKeys())
			System.out.println(i + " " + map2.getData(i));
		// Map from a Person object to an Order object
		System.out.println();
		MyMap<Person,Order> map3 = new MyMap<Person,Order>();
		map3.addOrUpdateMap(new Person("Bob"),new Order(100, 50.25));
		map3.addOrUpdateMap(new Person("Aaron"),new Order(101, 50.25));
		map3.addOrUpdateMap(new Person("Charlie"),new Order(102, 15.75));
		System.out.println("\nAll map3 keys and values:");
		for (Person p : map3.getKeys())
			System.out.println(p + " " + map3.getData(p));
		// THIS PART DOES NOT WORK - YOU MUST FIGURE OUT WHAT TO FIX!
		// Should output ID 100, $50.25
		System.out.print("ID for Bob: " );
		System.out.println(map3.getData(new Person("Bob")));
		// The below should update Bob's order to 103 and $99.99
		System.out.print("Update Bob to $99.99: ");
		map3.addOrUpdateMap(new Person("Bob"), new Order(103, 99.99));
		System.out.println(map3.getData(new Person("Bob")));
            //This part was added to write the objects to a binary file.    
            try {
                ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("MyMap.bin"));
                o.writeObject(map);
                o.writeObject(map2);
                o.close();
            } catch (IOException e) {
                System.out.println(e);
            }
                
	}
}