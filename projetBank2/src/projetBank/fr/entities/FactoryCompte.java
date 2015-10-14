package projetBank.fr.entities;

public final class FactoryCompte {

	private static FactoryCompte myInstance;

	private long num;
	private FactoryCompte(){

	}

	protected Compte createCompte()
	{
		return new Compte(this.num++);

	}
	protected Compte createCompte(TypeCompte type){
		return new Compte(this.num++,type);
	}
	protected Compte createCompte(TypeCompte type,double solde){
		return new Compte(this.num++,type,solde);
		}

	public Compte getCompte()
	{
		return this.createCompte();
	}
	public Compte getCompte(TypeCompte type)
	{
		return this.createCompte(type);
	}
	public Compte getCompte(TypeCompte type,double solde)
	{
		return this.createCompte(type,solde);
	}


	public static synchronized FactoryCompte getInstance(){
		if(FactoryCompte.myInstance == null)
		{
			FactoryCompte.myInstance = new FactoryCompte();
		}
		return FactoryCompte.myInstance;
	}


}