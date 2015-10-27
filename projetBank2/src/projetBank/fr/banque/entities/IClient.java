package projetBank.fr.banque.entities;

import java.sql.Date;

import projetBank.fr.banque.BanqueException;

public interface IClient extends IEntite
{
	@Override
	public Long getNumero();

	public String getPrenom();

	public String getNom();

	public Integer getAge();

	public int getSexe();

	public Date getDerniereConnexion();

	public String getAdresse();

	public Integer getCodePostal();

	public String getTelephone();

	public void setPrenom(String prenom);

	public void setNom(String nom);

	public void setAge(Integer age);

	public void setSexe(int sexe);

	public void setDerniereConnexion(Date date);

	public void setAdresse(String adresse);

	public void setCodePostal(Integer codePostal);

	public void setTelephone(String telephone);

	public void setNumero(Long id);

	public void setComptes(ICompte[] comptes) throws BanqueException;

	public void ajouterCompte(ICompte unCompte) throws BanqueException;

	public ICompte getCompte(Long numeroCompte);

	public ICompte[] getComptes();
}
