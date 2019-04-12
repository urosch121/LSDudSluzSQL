package studentskaSluzba;

public class PolozeniIspiti 
{
	private Predmet predmet;
	private int ocena;
	
	public PolozeniIspiti(Predmet predmet, int ocena)
	{
		this.predmet = predmet;
		this.ocena = ocena;
	}
	
	@Override
	public String toString() 
	{
		return this.predmet + " " + this.ocena;
	}

	public Predmet getPredmet() {return predmet;}
	public int getOcena() {return ocena;}

	public void setOcena(int ocena) {
		this.ocena = ocena;
	}
}
