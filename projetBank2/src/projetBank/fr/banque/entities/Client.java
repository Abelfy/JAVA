package projetBank.fr.banque.entities;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import projetBank.fr.banque.BanqueException;

	class Client implements IClient {

	private String prenom;
	private String nom;
	private Integer age;
	private Long numero;
	//private ICompte[] comptes;
	Map<Long, ICompte> listeCompte;
	private List<ICompte> comptes;

	public static final int NB_MAX_COMPTE = 5;


	/**
	 *
	 */
	protected Client(Long num){
		this(num,"","",new Integer(0));
	}
	protected Client(Long num, String nom){
		this(num,nom,"",new Integer(0));
	}
	protected Client(Long num, String nom, String prenom) {
		this(num,nom,prenom,new Integer(0));
	}
	protected Client(Long num,String nom, String prenom, Integer age) {
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAge(age);
		this.numero = num;
	}


	@Override
	public String getPrenom() {
		return this.prenom;
	}
	@Override
	public String getNom() {
		return this.nom;
	}
	@Override
	public Integer getAge() {
		return this.age;
	}
	@Override
	public Long getNumero() {
		return this.numero;
	}
	@Override
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}
	@Override
	public void setAge(Integer age) {
		this.age = age;
	}

	@Override
	public void setComptes(ICompte[] comptes) {
		for (ICompte iCompte : comptes) {
			this.comptes.add(iCompte);
		}
	}

	@Override
	public void ajouterCompte(ICompte unCompte) throws BanqueException
	{
		if(this.listeCompte == null)
		{
				this.listeCompte = new Hashtable<Long, ICompte>();
				this.listeCompte.put(unCompte.getNumero(), unCompte);
		}
		else if(this.listeCompte.size() < Client.NB_MAX_COMPTE){

			//this.comptes = Arrays.copyOf(this.comptes, this.comptes.length+1)
			this.listeCompte.put(unCompte.getNumero(), unCompte);
			//			if(!this.comptes.add(unCompte))
			//				{
			//					throw new BanqueException("Impossible d'ajouter le compte");
			//				}
		}
		else
		{
			throw new BanqueException("Le client possède déja "+Client.NB_MAX_COMPTE+ " comptes");
		}
	}

	@Override
	public ICompte getCompte(Long numeroCompte ){
		return this.listeCompte.get(numeroCompte);
	}

	@Override
	public ICompte[] getComptes(){

		ICompte[] comptesTmp = new ICompte[this.listeCompte.size()];
		for (int i = 0 ; i < this.listeCompte.size(); i++) {
			comptesTmp[i]=this.listeCompte.get(Long.valueOf(i));
		}
		return comptesTmp;
	}

	@Override
	public String toString(){
		StringBuffer buff = new StringBuffer();
		buff.append(this.getClass().getName());
		buff.append(" [Numero client  = ");
		buff.append(this.getNumero());
		buff.append(", prenom : ");
		buff.append(this.getPrenom());
		buff.append(", nom : ");
		buff.append(this.getNom());
		buff.append(", age : ");
		buff.append(this.getAge());
		buff.append(']');
		buff.append('\n');
		if (this.listeCompte != null)
		{
			for (ICompte compte : this.listeCompte.values())
			{
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
			return cTmp.getNumero() == this.getNumero();
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
