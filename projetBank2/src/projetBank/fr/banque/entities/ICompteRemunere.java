package projetBank.fr.banque.entities;

public interface ICompteRemunere extends ICompte {

	public double calculerInterets();
	public void verserInterets();
	public float getTaux();
	public void setTaux(float taux);
}
