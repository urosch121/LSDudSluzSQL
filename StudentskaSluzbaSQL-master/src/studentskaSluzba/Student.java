package studentskaSluzba;

import java.util.ArrayList;

public class Student 
{
	private int brojIndexa;
	private int godinaUpisa;
	private String ime;
	private ArrayList<PolozeniIspiti> listaPolozenihIspita;
	
	public Student (int brojIndexa, int godinaUpisa, String ime)
	{
		this.brojIndexa = brojIndexa;
		this.godinaUpisa = godinaUpisa;
		this.ime = ime;
		this.listaPolozenihIspita = new ArrayList<>();
	}
	
	public void dodajOcenuStudentu (PolozeniIspiti polozenIspit)
	{
		boolean postojiIspit = false;
		for (PolozeniIspiti pi : listaPolozenihIspita) 
		{
			if (pi.getPredmet().getIdPredmeta()==polozenIspit.getPredmet().getIdPredmeta())
			{
				
				postojiIspit = true;
				if (polozenIspit.getOcena() > pi.getOcena())
					pi.setOcena(polozenIspit.getOcena());
			}
		}
		if (postojiIspit == false)
			this.listaPolozenihIspita.add(polozenIspit);
	}
	
	
	public String prikaziOceneStudenta()
	{
		String ocene = "\nOcene:\n";
		for (PolozeniIspiti polozeniIspiti : listaPolozenihIspita) 
			ocene += polozeniIspiti + "\n";
		
		return this + ocene;
	}
	
	@Override
	public String toString() 
	{
		return this.brojIndexa + "/" + this.godinaUpisa + " " + this.ime;
	}

	public int getBrojIndexa() {return brojIndexa;}
	public int getGodinaUpisa() {return godinaUpisa;}
	public String getIme() {return ime;}
	public ArrayList<PolozeniIspiti> getListaPolozenihIspita() {return listaPolozenihIspita;}
}
