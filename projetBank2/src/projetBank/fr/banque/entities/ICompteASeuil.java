package projetBank.fr.banque.entities;

public interface ICompteASeuil extends ICompte{

	public double getSeuil();
	public void setSeuil(double seuil);
	@Override
	public void retirer(double uneValeur);
}
