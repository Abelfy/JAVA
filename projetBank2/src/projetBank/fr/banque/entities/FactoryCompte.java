package projetBank.fr.banque.entities;

public final class FactoryCompte
{

	private static FactoryCompte myInstance;

	private long num;

	private FactoryCompte()
	{

	}

	protected Compte createCompte()
	{
		return new Compte(Long.valueOf(this.num++));

	}

	protected Compte createCompte(String label)
	{
		return new Compte(Long.valueOf(this.num++), label);
	}

	protected Compte createCompte(String label, Double solde)
	{
		return new Compte(Long.valueOf(this.num++), label, solde);
	}

	public Compte getCompte()
	{
		return this.createCompte();
	}

	public Compte getCompte(String label)
	{
		return this.createCompte(label);
	}

	public Compte getCompte(String label, Double solde)
	{
		return this.createCompte(label, solde);
	}

	public static synchronized FactoryCompte getInstance()
	{
		if (FactoryCompte.myInstance == null)
		{
			FactoryCompte.myInstance = new FactoryCompte();
		}
		return FactoryCompte.myInstance;
	}

}