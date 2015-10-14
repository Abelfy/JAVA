package projetBank.fr.banque.entities;

public final class FactoryClient {

	private static FactoryClient myInstance;

	private long num;
	private FactoryClient(){}

	public Client getClient()
	{
		return this.createClient();
	}
	public Client getClient(String nom)
	{
		return this.createClient(nom);
	}
	public Client getClient(String nom,String prenom)
	{
		return this.createClient(nom,prenom);
	}
	public Client getClient(String nom, String prenom, int age)
	{
		return this.createClient(nom, prenom,age);
	}

	protected Client createClient()
	{
		return new Client(this.num++,"","",0);

	}
	protected Client createClient(String nom)
	{
		return new Client(this.num++,nom,"",0);

	}
	protected Client createClient(String nom, String prenom)
	{
		return new Client(this.num++,nom,prenom,0);

	}
	protected Client createClient(String nom ,String prenom,int age){
		return new Client(this.num++,nom,prenom,age);
	}

	public static synchronized FactoryClient getInstance(){
		if(FactoryClient.myInstance == null)
		{
			FactoryClient.myInstance = new FactoryClient();
		}
		return FactoryClient.myInstance;
	}


}
