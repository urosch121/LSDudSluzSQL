package fajlovi;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import db.Databases;
import studentskaSluzba.PolozeniIspiti;
import studentskaSluzba.Predmet;
import studentskaSluzba.Profesor;
import studentskaSluzba.Student;

public class ImportFajla 
{
	private String putanjaFajla;
	private Databases db = new Databases();
	
	public ImportFajla (String putanjaFajla) 
	{
		this.putanjaFajla = putanjaFajla;
		ucitajPredmet();
		ucitajProfesora();
		ucitajStudenta();
	}
	
	private void ucitajProfesora ()
	{
		Scanner in;
		String line;
		
		try
		{
			in = new Scanner(new FileReader(this.putanjaFajla));
			
			while(in.hasNextLine()) 
			{
				line = in.nextLine();
				String [] nizLine1 = line.split(":");
				
				if (nizLine1[0].equalsIgnoreCase("P"))
				{
					String [] nizLine2 = nizLine1[1].split(",");
					int idP = Integer.parseInt(nizLine2[0].trim());
					String ime = nizLine2[1].trim();
					int idPredmeta = Integer.parseInt(nizLine2[2].trim());
					Predmet predmet = pretragaPredmetaPoId(idPredmeta);
					Profesor profesor = new Profesor(idP, ime, predmet);
					//ubacujemo profesora u bazu
					db.addProfesor(profesor);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Navedeni fajl ne postoji! ");
		}
	}
	
	private void ucitajPredmet ()
	{
		Scanner in;
		String line;
		
		try
		{
			in = new Scanner(new FileReader(this.putanjaFajla));
			
			while(in.hasNextLine()) 
			{
				line = in.nextLine();
				String [] nizLine1 = line.split(":");
				
				if (nizLine1[0].equalsIgnoreCase("PR"))
				{
					String [] nizLine2 = nizLine1[1].split(",");
					int idPredmeta = Integer.parseInt(nizLine2[0].trim());
					String nazivPredmeta = nizLine2[1].trim();
					Predmet predmet = new Predmet(idPredmeta, nazivPredmeta);
					//ubacujemo predmet direktno u vazu
					db.addPredmet(predmet);
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Navedeni fajl ne postoji! ");
		}
	}
	
	private void ucitajStudenta ()
	{
		Scanner in;
		String line;
		
		try
		{
			in = new Scanner(new FileReader(this.putanjaFajla));
			
			while(in.hasNextLine()) 
			{
				line = in.nextLine();
				String [] nizLine1 = line.split(":");
				
				if (nizLine1[0].equalsIgnoreCase("S"))
				{
					String [] nizLine2 = nizLine1[1].split(",");
					int brojIndexa = Integer.parseInt(nizLine2[0].trim());
					int godinaUpisa = Integer.parseInt(nizLine2[1].trim());
					String ime = nizLine2[2].trim();
					Student student = new Student(brojIndexa, godinaUpisa, ime);
					//ubacujemo studenta u bazu
					db.addStudent(student);
					for (int i = 3; i < nizLine2.length; i+=2)
					{
						int idPredmeta = Integer.parseInt(nizLine2[i].trim());
						Predmet predmet = pretragaPredmetaPoId(idPredmeta);
						int ocena = Integer.parseInt(nizLine2[i+1].trim());
						PolozeniIspiti pi = new PolozeniIspiti(predmet, ocena);
						student.dodajOcenuStudentu(pi);
						//ubacujemo ocene u novu tabelu
						db.dodajOceneStudentu(student, predmet, ocena);
					}
				}
			}
		}
		catch (FileNotFoundException e)
		{
			System.err.println("Navedeni fajl ne postoji! ");
		}
	}
	
	private Predmet pretragaPredmetaPoId (int id)
	{
		Predmet predmet = db.readPredmet(id);
		return predmet;
	}

}
