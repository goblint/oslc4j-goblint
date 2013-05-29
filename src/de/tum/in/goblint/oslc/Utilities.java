package de.tum.in.goblint.oslc;

public class Utilities {
	private static int id_counter = 100; 
	public static synchronized int getNewId(){
		int i = id_counter;
		id_counter = id_counter + 1;
		return i;
	}
}
