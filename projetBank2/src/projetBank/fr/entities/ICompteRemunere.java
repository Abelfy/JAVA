package projetBank.fr.entities;

public interface ICompteRemunere {

	public double calculerInterets();
	public void verserInterets();
	public float getTaux();
	public void setTaux(float taux);
}
