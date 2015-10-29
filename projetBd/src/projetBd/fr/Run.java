package projetBd.fr;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import projetBank.fr.banque.BanqueException;
import projetBank.fr.banque.entities.IClient;
import projetBank.fr.banque.entities.ICompte;
import projetBank.fr.banque.entities.IOperation;

public class Run
{

	public static void main(String[] args)
	{

		Properties mesProperties = new Properties();

		try(InputStream is = ClassLoader.getSystemResourceAsStream("projetBd/fr/mesPreferences.properties"))
		{
			mesProperties.load(is);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
//		File file = new File("C:/Users/abelfy/Documents/Exercice/JAVA/projetBd/src/projetBd/fr/mesPreferences.properties");
//		if(file.exists() && file.canRead())
//		{
//			try(FileReader fr = new FileReader(file)){
//
//				mesProperties.load(fr);
//				}
//			catch(IOException e)
//			{
//				e.printStackTrace();
//			}
//		}
//		else
//		{
//			System.err.println("Fichier'" + file + "'pas trouve");
//		}



	DBUtil util = new DBUtil(mesProperties.getProperty("db.url"),
								mesProperties.getProperty("db.login"),
								mesProperties.getProperty("db.password"));


	Properties pS = System.getProperties();
	Iterator<Map.Entry<Object,Object>> iter = pS.entrySet().iterator();
	while(iter.hasNext())
	{
		Map.Entry<Object, Object> entry = iter.next();
		System.out.println(entry.getKey() + " = " +entry.getValue());
	}


	util.connexion();

			//authentification
			try
			{
			IClient clt = util.authentifier("df", "df");
			System.out.println(clt);
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				System.err.println("Erreur d'authentification");
			}

			//récupèration de la liste de client.
			List<IClient> listclient;
			try
			{
				listclient = util.recupererClient();
				System.out.println(listclient.toString());
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				System.err.println("Erreur à la récupèration des Clients");
			}

			//Récupèration de la liste des comptes
			List<ICompte> listcomptes;
			try
			{
				listcomptes = util.recupererCompte();
				System.out.println(listcomptes.toString());
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				System.err.println("Erreur à la récupèration des Comptes");
			}

			//Récupèration de la liste des opérations
			List<IOperation> listoperation;
			try
			{
				listoperation = util.recupererOperation();
				System.out.println(listoperation.toString());
				listoperation=null;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				System.err.println("Erreur à la récupèration des Operations");
			}

			//récupèration d'un compte
			ICompte cmpt = null;
			try
			{
				cmpt = util.recupererCompteByID(3);
				if(cmpt != null)
				{
				System.out.println(cmpt.toString());
				}
				else
				{
					System.out.println("Compte non trouvé");
				}
			}
			catch (SQLException e)
			{
				System.err.println("Impossible de trouver le compte");
			}

			//Recherche d'opération sur un compte entre une date de début et  une date de fin , boolean == true => crédit ,null tous , faux == false
			try
			{
				Calendar cal1 = Calendar.getInstance();
				cal1.set(Calendar.YEAR, 2014);
				cal1.set(Calendar.MONTH, 12);
				cal1.set(Calendar.DAY_OF_MONTH, 15);
				Calendar cal2 = Calendar.getInstance();
				cal2.set(Calendar.YEAR, 2015);
				cal2.set(Calendar.MONTH, 12);
				cal2.set(Calendar.DAY_OF_MONTH, 15);

				listoperation = util.rechercherOperation(15,new Date(cal1.getTimeInMillis()), new Date(cal2.getTimeInMillis()), Boolean.TRUE);
				System.out.println(listoperation.toString());
			}
			catch (SQLException e)
			{
				e.printStackTrace();
				System.err.println("Erreur à la recherche des operations du compte");
			}
			catch (BanqueException e)
			{
				e.printStackTrace();
				System.err.println("Erreur à la recherche des operations du compte");
			}

			// Virements
			try
			{
				listoperation = util.faireVirement(13, 15, -500);
				if(listoperation != null)
				{
					System.out.println(listoperation.toString());
			}
			}
			catch (SQLException e)
			{
			e.printStackTrace();
				System.err.println("echec lors de l'opératiob");
			}
			catch(BanqueException e)
			{
				e.printStackTrace();
				System.err.println("Erreur sur les n° de comptes");
			}

				util.close();

	}

}
