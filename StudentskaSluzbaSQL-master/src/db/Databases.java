package db;

import java.sql.*;
import java.util.ArrayList;

import studentskaSluzba.PolozeniIspiti;
import studentskaSluzba.Predmet;
import studentskaSluzba.Profesor;
import studentskaSluzba.Student;

public class Databases 
{
	Connection conn;
	public Databases () 
	{
		String connString = "jdbc:mysql://localhost:3306/studentskasluzba?user=root&password=";
		try 
		{
			conn = DriverManager.getConnection(connString);
		} 
		catch (SQLException e) 
		{
			System.out.println("Neka greska sa bazom!");
		}
	}
	
	public void addPredmet(Predmet predmet)
	{
		try 
		{
			String query = "INSERT INTO Predmet VALUES (?, ?)";
			PreparedStatement prepS = conn.prepareStatement(query);
			prepS.setInt(1, predmet.getIdPredmeta());
			prepS.setString(2, predmet.getNazivPredmeta());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void updatePredmet(Predmet predmet)
	{
		try 
		{
			String query = "UPDATE predmet SET nazivPredmeta = ? WHERE id = ?";
			
			PreparedStatement prepS = conn.prepareStatement(query);
			prepS.setString(1, predmet.getNazivPredmeta());
			prepS.setInt(2, predmet.getIdPredmeta());
			prepS.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void deletePredmet (Predmet predmet)
	{
		try
		{
			String query = "DELETE FROM predmet WHERE id = ?";
			
			PreparedStatement prepS = conn.prepareStatement(query);
			prepS.setInt(1, predmet.getIdPredmeta());
			prepS.executeUpdate();
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Predmet readPredmet(int idPredmeta)
	{
		try
		{
			String query = "SELECT * FROM predmet WHERE id = ?";
			
			PreparedStatement prepS = conn.prepareStatement(query);
			prepS.setInt(1, idPredmeta);
			
			ResultSet res = prepS.executeQuery();
			
			if (res.next()) 
			{
				int idP = res.getInt("id");
				String naziv = res.getString("nazivPredmeta").trim();
				Predmet predmet = new Predmet(idP, naziv);
				return predmet;
			}
			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ArrayList<Predmet> readPredmeti()
	{
		ArrayList<Predmet> listaPredmeta = new ArrayList<>();
		try 
		{
			Statement statment = conn.createStatement();
			String query = "SELECT * FROM predmet";
			ResultSet res = statment.executeQuery(query);
			
//			String query1 = "SELECT * FROM predmet ";
//			PreparedStatement prepS1 = conn.prepareStatement(query1);
//			ResultSet res1 = prepS1.executeQuery();
			
			while (res.next())
			{
				int idP = res.getInt("id");
				String naziv = res.getString("nazivPredmeta").trim();
			
				Predmet predmet = new Predmet(idP, naziv);
				listaPredmeta.add(predmet);
			}
			return listaPredmeta;
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
			return listaPredmeta;
		}
		
	}
	
	public void addStudent(Student student)
	{
		try 
		{
			String query = "INSERT INTO Student VALUES (?, ?, ?)";
			PreparedStatement prepS = conn.prepareStatement(query);
			prepS.setInt(1, student.getBrojIndexa());
			prepS.setInt(2, student.getGodinaUpisa());
			prepS.setString(3, student.getIme());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public void dodajOceneStudentu(Student student, Predmet predmet, int ocena)
	{
		try 
		{
			String query = "INSERT INTO oceneStudenta VALUES (?, ?, ?, ?)";//indexStudenta, godinaUpisa, idPredmeta, ocena
			PreparedStatement prepS = conn.prepareStatement(query);
			prepS.setInt(1, student.getBrojIndexa());
			prepS.setInt(2, student.getGodinaUpisa());
			prepS.setInt(3, predmet.getIdPredmeta());
			prepS.setInt(4, ocena);
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	public Student readStudent (int brojIndexa, int godinaUpisa) 
	{
		try
		{
			String query = "SELECT * FROM student WHERE brojIndexa = ? AND godinaUpisa = ?";
			
			PreparedStatement prepS = conn.prepareStatement(query);
			prepS.setInt(1, brojIndexa);
			prepS.setInt(2, godinaUpisa);
			
			ResultSet res = prepS.executeQuery();
			
			if (res.next()) 
			{
				int index = res.getInt("brojIndexa");
				int godina = res.getInt("godinaUpisa");
				String ime = res.getString("ime").trim();
			
				Student student = new Student(index, godina, ime);
				
				String queryOcene = "SELECT * FROM ocenestudenta WHERE brojIndexa = ? AND godinaUpisa = ?";
				PreparedStatement prepOcene = conn.prepareStatement(queryOcene);
				prepOcene.setInt(1, brojIndexa);
				prepOcene.setInt(2, godinaUpisa);
				ResultSet res2 = prepOcene.executeQuery();
				
				while (res2.next())
				{
					int idPredmeta = res2.getInt("idPredmeta");
					int ocena = res2.getInt("ocena");
					student.dodajOcenuStudentu(new PolozeniIspiti(readPredmet(idPredmeta), ocena));
				}
				return student;
			}
			return null;
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void addProfesor (Profesor profesor)
	{
		try 
		{
			String query = "INSERT INTO profesor VALUES (?, ?, ?)";
			PreparedStatement prepS = conn.prepareStatement(query);
			prepS.setInt(1, profesor.getIdProfesora());
			prepS.setString(2, profesor.getImeProfesora());
			prepS.setInt(3, profesor.getPredmet().getIdPredmeta());
			prepS.executeUpdate();
			prepS.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
