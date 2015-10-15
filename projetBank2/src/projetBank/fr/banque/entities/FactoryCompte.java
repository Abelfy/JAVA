package projetBank.fr.banque.entities;

public final class FactoryCompte {

	private static FactoryCompte myInstance;

	private long num;
	private FactoryCompte(){

	}

	protected Compte createCompte()
	{
		return new Compte(Long.valueOf(this.num++));

	}
	protected Compte createCompte(TypeCompte type){
		return new Compte(Long.valueOf(this.num++),type);
	}
	protected Compte createCompte(TypeCompte type,Double solde){
		return new Compte(Long.valueOf(this.num++),type,solde);
		}

	public Compte getCompte()
	{
		return this.createCompte();
	}
	public Compte getCompte(TypeCompte type)
	{
		return this.createCompte(type);
	}
	public Compte getCompte(TypeCompte type,Double solde)
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