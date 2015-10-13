package projetBank.fr.banque;

import java.util.Arrays;
import java.util.UUID;

public class Client {

	private String prenom;
	private String nom;
	private int age;
	private long numero;
	private Compte[] comptes;
	public static final int NB_MAX_COMPTE = 5;


	/**
	 *
	 */
	public Client() {
		this("");
	}
	/**
	 * @param prenom
	 */
	public Client(String prenom) {
		this(prenom,"");
	}
	/**
	 * @param prenom
	 * @param nom
	 */
	public Client(String prenom, String nom) {
		this(prenom,nom,0);
	}
	/**
	 * @param prenom
	 * @param nom
	 * @param age
	 */
	public Client(String prenom, String nom, int age) {
		this.setPrenom(prenom);
		this.setNom(nom);
		this.setAge(age);
		this.numero = UUID.randomUUID().getMostSignificantBits();
	}


	public String getPrenom() {
		return this.prenom;
	}
	public String getNom() {
		return this.nom;
	}
	public int getAge() {
		return this.age;
	}
	public long getNumero() {
		return this.numero;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public void setAge(int age) {
		this.age = age;
	}

	public void setComptes(Compte[] comptes) {
		this.comptes = comptes;
	}

	public void ajouterCompte(Compte unCompte){
		if(this.comptes == null)
		{
				this.comptes = new Compte[1];
				this.comptes[0]=unCompte;
		}
		else if(this.comptes.length < Client.NB_MAX_COMPTE){

			this.comptes = Arrays.copyOf(this.comptes, this.comptes.length+1);
			this.comptes[this.comptes.length-1] =unCompte;
		}
		else
		{
			System.err.println("Impossible d'ajouter le compte.");
		}
	}

	public Compte getCompte(int numeroCompte ){
		if (this.comptes[numeroCompte] != null){
			return this.comptes[numeroCompte];
		}
		return null;
	}

	public Compte[] getComptes(){

		return this.comptes;
	}

	@Override
	public String toString(){
		StringBuffer buff = new StringBuffer();
		buff.append(this.getClass().getName());
		buff.append(" [Numero = ");
		buff.append(this.getNumero());
		buff.append(", prenom : ");
		buff.append(this.getPrenom());
		buff.append(", nom : ");
		buff.append(this.getNom());
		buff.append(", age : ");
		buff.append(this.getAge());
		buff.append(']');
		if (this.comptes != null)
		{
			for (Compte compte : this.comptes) {
				buff.append(compte.toString()+'\n');
			}
		}

		return buff.toString();
	}


	@Override
	public boolean equals(Object obj) {
		if(obj == null)
		{
			return false;
		}
		if(obj== this){
			return true;
		}
		if(obj.getClass() == Client.class){
			Client cTmp = (Client)obj;
			String moi = this.toString();
			String lui = cTmp.toString();
			return moi.equals(lui);

			//return (this.getNom() == cTmp.getNom()) || (this.getNom().equals(cTmp.getNom()) &&
			//		(this.getPrenom() == cTmp.getPrenom())) || ((this.getPrenom().equals(cTmp.getPrenom()) &&
			//		(this.getAge() == cTmp.getAge()) &&
			//		(this. getNumeroDeType() == cTmp. getNumeroDeType())) &&
			//		this.comptes.equals(cTmp.comptes));
		}

		return false;
	}
	@Override
	public int hashCode() {
		String b = this.getClass().getName()+"_"+this.toString();
		return b.hashCode();
	}



}
