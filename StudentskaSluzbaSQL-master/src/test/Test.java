package test;

import db.Databases;
import fajlovi.ImportFajla;
import studentskaSluzba.Student;

public class Test {
	
	
	public static void main(String[] args) 
	{
		//ImportFajla importFajla = new ImportFajla("Baza.txt");
		
		
		Databases db = new Databases();
//
		Student st1 = db.readStudent(56, 2017);
		System.out.println(st1.prikaziOceneStudenta());
		
	}

}
