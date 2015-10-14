package projetBank.fr.banque;

public final class Factory<T> {

	private long num;
	private Class<T> type;

	public Factory(Class<T> unType){
		this.type = unType;
	}

	@SuppressWarnings("unchecked")
	public T create(Object... objects)
	{
		if(objects.length == 0){
			if(this.type == Client.class)
			{
				return (T)new Client(this.num++);
			}
			else if(this.type == Compte.class)
			{
				return (T)new Compte(this.num++);
			}
		}
		else if(objects.length == 1)
		{
			if(objects[0].getClass() == String.class)
			{
				return (T)new Client(this.num++,(String)objects[0]);
			}
			else if(objects[0].getClass() == TypeCompte.class)
			{
				if(this.type == Compte.class)
				{
					return (T)new Compte(this.num++,(TypeCompte)objects[0]);
				}

				else if(this.type == CompteRemunere.class)
				{
					return (T)new CompteRemunere(this.num++,(TypeCompte)objects[0]);
				}
				else if(this.type == CompteASeuil.class)
				{
					return (T)new CompteASeuil(this.num++,(TypeCompte)objects[0]);
				}
			}
		}
		else if(objects.length == 2)
		{
			if(objects[0].getClass() == String.class){
				return (T)new Client(this.num++,(String)objects[0],(String)objects[1]);
			}
			else if(objects[0].getClass() == TypeCompte.class){
				if(this.type == Compte.class)
				{
					return (T)new Compte(this.num++,(TypeCompte)objects[0],(double)objects[1]);
				}

				else if(this.type == CompteRemunere.class)
				{
					return (T)new CompteRemunere(this.num++,(TypeCompte)objects[0],(double)objects[1]);
				}
				else if(this.type == CompteASeuil.class)
				{
					return (T)new CompteASeuil(this.num++,(TypeCompte)objects[0],(double)objects[1]);
				}

			}
		}
		else if (objects.length == 3)
		{
			if(objects[0].getClass() == String.class){
				return (T)new Client(this.num++,(String)objects[0],(String)objects[1],(int)objects[2]);
			}
			else if(objects[0].getClass() == TypeCompte.class)
			{
				if(this.type == CompteRemunere.class)
				{
					return (T)new CompteRemunere(this.num++,(TypeCompte)objects[0],(double)objects[1],(double)objects[2]);
				}
				else if(this.type == CompteASeuil.class)
				{
					return (T)new CompteASeuil(this.num++,(TypeCompte)objects[0],(double)objects[1],(double)objects[2]);
				}
			}
		}
		System.err.println("Oublié un cas");
		return null;
	}
}
