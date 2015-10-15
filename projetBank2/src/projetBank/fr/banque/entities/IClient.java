package projetBank.fr.banque.entities;

import projetBank.fr.banque.BanqueException;

public interface IClient extends IEntite
{
	public String getPrenom();
	public String getNom();
	public Integer getAge();
	@Override
	public Long getNumero();
	public void setPrenom(String prenom);
	public void setNom(String nom);
	public void setAge(Integer age);
	public void setComptes(ICompte[] comptes);
	public void ajouterCompte(ICompte unCompte) throws BanqueException;
	public ICompte getCompte(int numeroCompte );
	public ICompte[] getComptes();
}
