package studentskaSluzba;

public class Predmet 
{
	private int idPredmeta;
	private String nazivPredmeta;
	
	public Predmet (int idPredmeta, String nazivPredmeta)
	{
		this.idPredmeta = idPredmeta;
		this.nazivPredmeta = nazivPredmeta;
	}
	
	@Override
	public String toString() 
	{
		return this.idPredmeta + " " + this.nazivPredmeta;
	}
	
	public int getIdPredmeta() {return idPredmeta;}
	public String getNazivPredmeta() {return nazivPredmeta;}
	public void setNazivPredmeta(String nazivPredmeta) {this.nazivPredmeta = nazivPredmeta;}
	
}
