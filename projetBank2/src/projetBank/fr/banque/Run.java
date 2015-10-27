package projetBank.fr.banque;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import projetBank.fr.banque.entities.Factory;
import projetBank.fr.banque.entities.IClient;
import projetBank.fr.banque.entities.ICompte;
import projetBank.fr.banque.entities.ICompteASeuilRemunere;
import projetBank.fr.banque.entities.ICompteRemunere;
import projetBank.fr.banque.entities.TypeCompte;

public class Run
{

	@SuppressWarnings("deprecation")
	public static void main(String[] args)
	{

		long start = System.currentTimeMillis();

		double d = 5.54654213;
		DecimalFormat df = new DecimalFormat("0.00");
		Locale l = Locale.getDefault();
		System.out.println(l);
		System.out.println(df.format(d));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy - MM - dd HH:mm:ss");
		Date d1 = new Date();
		d1.setHours(12);
		System.out.println(d1);
		System.out.println(sdf.format(d1));

		Calendar c = new GregorianCalendar();
		c.setTime(new Date());
		c.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR) + 3);

		System.out.println(c.get(Calendar.DAY_OF_WEEK));

		Factory<IClient> fcc = new Factory<>(IClient.class);
		Factory<ICompteRemunere> fCmptRmnr = new Factory<>(ICompteRemunere.class);
		Factory<ICompteASeuilRemunere> fCompteASRmnr = new Factory<>(ICompteASeuilRemunere.class);

		// Création d'un client.
		Object[] objects = new Object[3];
		IClient c0 = fcc.create("BELFY", "Adrien", new Integer(25));

		System.out.println(c0.toString());

		// Ajoute 5 compte rémunérer au client.
		objects = new Object[3];
		for (int i = 0; i < 52; i++)
		{
			try
			{
				if ((i % 2) == 0)
				{
					objects[0] = TypeCompte.COURRANT;
				}
				else
				{
					objects[0] = TypeCompte.EPARGNE;
				}

				objects[1] = new Double(2500 + i);
				objects[2] = new Float(0.03f + (i / 1000f));
				/*
				 * On ajoute le d pour préciser que 1000 est un nombre a virgule
				 * flottante, de fait la division n'est pas une division
				 * d'entier ==> résultat flottant.
				 */
				ICompteRemunere cmptRmnr = fCmptRmnr.create(objects);
				c0.ajouterCompte(cmptRmnr);
			}
			catch (BanqueException e)
			{
				System.err.println(e.getMessage());
				break;
			}
		}

		System.out.println(c0.toString());

		for (ICompte cmptRmnr : c0.getComptes())
		{
			if (cmptRmnr instanceof ICompteRemunere)
			{
				ICompteRemunere cTmp = (ICompteRemunere) cmptRmnr;
				cTmp.verserInterets();
			}
		}

		System.out.println(c0.toString());

		try
		{
			ICompte cmpt = c0.getCompte(new Long(7));
			System.out.println("Le compte demandé : \n" + cmpt.toString() + '\n');
		}
		catch (Exception e)
		{
			System.err.println("Impossible de trouver le compte : ");
			System.err.println(e.getMessage() + '\n');
		}

		ICompteRemunere iCompt = fCmptRmnr.create(objects);
		objects = new Object[4];
		objects[0] = TypeCompte.COURRANT;
		objects[1] = new Double(250);
		objects[2] = new Float(0.03);
		objects[3] = new Double(100);
		ICompteASeuilRemunere iComptASRmnr = fCompteASRmnr.create(objects);

		try
		{
			iComptASRmnr.retirer(new Double(260));
		}
		catch (BanqueException e)
		{
			System.err.println(e.getMessage());
		}
		System.out.println(iComptASRmnr.toString());
		iCompt.verserInterets();
		System.out.println(iCompt.toString());

		List<ICompte> listTest = new ArrayList<ICompte>();
		listTest.add(fCompteASRmnr.create(TypeCompte.COURRANT, new Double(250), new Float(0.03), new Double(100)));
		listTest.add(fCompteASRmnr.create(TypeCompte.EPARGNE, new Double(265), new Float(0.036), new Double(150)));
		listTest.add(fCompteASRmnr.create(TypeCompte.COURRANT, new Double(200), new Float(0.035), new Double(50)));
		System.out.println(listTest.toString());

		// Comparator<ICompte> parSolde = ;
		listTest.sort((ICompte a1, ICompte a2) -> a1.getSolde().compareTo(a2.getSolde()));

		System.out.println(listTest.toString());
		long end = System.currentTimeMillis();
		System.out.println("Temps en ms :" + (end - start));

	}
}
