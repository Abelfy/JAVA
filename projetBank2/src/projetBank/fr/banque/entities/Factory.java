package projetBank.fr.banque.entities;

public final class Factory<T extends IEntite> {

	private long num;
	private Class<T> type;

	public Factory(Class<T> unType){
		this.type = unType;
	}



	@SuppressWarnings("unchecked")
	public T create(Object... objects)
	{

		Long numTemp = Long.valueOf(this.num++);
		if(objects.length == 0){
			if(this.type == IClient.class)
			{
				return (T)new Client(numTemp);
			}
			else if(this.type == Compte.class)
			{
				return (T)new Compte(numTemp);
			}
		}
		else if(objects.length == 1)
		{
			if(objects[0].getClass() == String.class)
			{
				return (T)new Client(numTemp,(String)objects[0]);
			}
			else if(objects[0].getClass() == TypeCompte.class)
			{
				if(this.type == Compte.class)
				{
					return (T)new Compte(numTemp,(TypeCompte)objects[0]);
				}
				else if(this.type == CompteRemunere.class)
				{
					return (T)new CompteRemunere(numTemp,(TypeCompte)objects[0]);
				}
				else if(this.type == CompteASeuil.class)
				{
					return (T)new CompteASeuil(numTemp,(TypeCompte)objects[0]);
				}
				else if(this.type == CompteASeuilRemunere.class)
				{
					return (T)new CompteASeuilRemunere(numTemp,(TypeCompte)objects[0]);
				}
			}
		}
		else if(objects.length == 2)
		{
			if(objects[0].getClass() == String.class){
				return (T)new Client(numTemp,(String)objects[0],(String)objects[1]);
			}
			else if(objects[0].getClass() == TypeCompte.class){
				if(this.type == Compte.class)
				{
					return (T)new Compte(numTemp,(TypeCompte)objects[0],(Double)objects[1]);
				}

				else if(this.type == ICompteRemunere.class)
				{
					return (T)new CompteRemunere(numTemp,(TypeCompte)objects[0],(Float)objects[1]);
				}
				else if(this.type == ICompteASeuil.class)
				{
					return (T)new CompteASeuil(numTemp,(TypeCompte)objects[0],(Double)objects[1]);
				}
			}
		}
		else if (objects.length == 3)
		{
			if(objects[0].getClass() == String.class){
				return (T)new Client(numTemp,(String)objects[0],(String)objects[1],(Integer)objects[2]);
			}
			else if(objects[0].getClass() == TypeCompte.class)
			{
				if(this.type == ICompteRemunere.class)
				{
					return (T)new CompteRemunere(numTemp,(TypeCompte)objects[0],(Double)objects[1],(Float)objects[2]);
				}
				else if(this.type == ICompteASeuil.class)
				{
					return (T)new CompteASeuil(numTemp,(TypeCompte)objects[0],(Double)objects[1],(Double)objects[2]);
				}
			}
		}
		else if (objects.length == 4 ) {
			if(this.type == ICompteASeuilRemunere.class)
			{
				return (T)new CompteASeuilRemunere(numTemp,(TypeCompte)objects[0],(Double)objects[1],(Float)objects[2],(Double)objects[3]);
			}
		}
		System.err.println("Oublié un cas");
		return null;
	}
}
