package studentskaSluzba;

public class Profesor 
{
	private int idProfesora;
	private String imeProfesora;
	private Predmet predmet;
	
	public Profesor (int idProfesora, String imeProfesora, Predmet predmet)
	{
		this.idProfesora = idProfesora;
		this.imeProfesora = imeProfesora;
		this.predmet = predmet;
	}
	
	@Override
	public String toString() 
	{
		return this.idProfesora + " " + this.imeProfesora + " " + this.predmet;
	}

	public int getIdProfesora() {return idProfesora;}
	public String getImeProfesora() {return imeProfesora;}
	public Predmet getPredmet() {return predmet;}
}
