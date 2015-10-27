package projetBank.fr.banque.entities;

public interface ICompteRemunere extends ICompte
{

	public Double calculerInterets();

	public void verserInterets();

	public Float getTaux();

	public void setTaux(Float taux);
}
