package projetBank.fr.banque.entities;

import projetBank.fr.banque.BanqueException;

public interface ICompte extends IEntite
{
	public static final TypeCompte TYPE = TypeCompte.COURRANT;
	public static final double SOLDE = 0.0;

	public void ajouter(Double unMontant);

	public void retirer(Double unMontant) throws BanqueException;

	@Override
	public Long getNumero();

	public String getLibelle();

	public void setLibelle(String libelle);

	public TypeCompte getType();

	public Double getSolde();

	public void setNumero(Long id);
}
