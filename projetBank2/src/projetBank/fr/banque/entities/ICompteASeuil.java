package projetBank.fr.banque.entities;

import projetBank.fr.banque.BanqueException;

public interface ICompteASeuil extends ICompte{

	public Double getSeuil();
	public void setSeuil(Double seuil);
	@Override
	public void retirer(Double uneValeur) throws BanqueException;
}
