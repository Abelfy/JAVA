package projetBank.fr.banque.entities;

import java.util.Comparator;

public class CompteComparator implements Comparator<ICompte>
{

	@Override
	public int compare(ICompte o1, ICompte o2)
	{
		if (o1.getNumero().longValue() < o2.getNumero().longValue())
		{
			return -1;
		}
		else if (o1.getNumero().longValue() > o2.getNumero().longValue())
		{
			return 1;
		}
		return 0;
	}
}
