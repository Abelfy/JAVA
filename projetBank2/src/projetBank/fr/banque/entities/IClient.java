package projetBank.fr.banque.entities;

public interface IClient extends IEntite
{
	public String getPrenom();
	public String getNom();
	public int getAge();
	@Override
	public long getNumero();
	public void setPrenom(String prenom);
	public void setNom(String nom);
	public void setAge(int age);
	public void setComptes(ICompte[] comptes);
	public void ajouterCompte(ICompte unCompte);
	public ICompte getCompte(int numeroCompte );
	public ICompte[] getComptes();
}
