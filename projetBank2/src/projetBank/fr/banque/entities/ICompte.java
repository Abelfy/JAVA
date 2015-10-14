package projetBank.fr.banque.entities;

public interface ICompte extends IEntite
{
	public static final TypeCompte TYPE = TypeCompte.COURRANT;
	public static final double SOLDE = 0.0;

	public void ajouter(double unMontant);
	public void retirer(double unMontant);
	public long getNumero();
	public TypeCompte getType();
	public double getSolde();
}
