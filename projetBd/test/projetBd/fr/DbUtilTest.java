package projetBd.fr;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import projetBank.fr.banque.BanqueException;
import projetBank.fr.banque.entities.IClient;
import projetBank.fr.banque.entities.ICompte;
import projetBank.fr.banque.entities.IOperation;

public class DbUtilTest
{
	private static DBUtil util;

	@BeforeClass
	public static void Init()
	{
		DbUtilTest.util = new DBUtil("jdbc:mysql://localhost:3306/banque","root","A130890b!!");
		DbUtilTest.util.connexion();
	}

	@Test
	public void Authentifier()
	{
		IClient clt = null;

		try
		{
		// Bon login/password
			clt = DbUtilTest.util.authentifier("df", "df");
			Assert.assertNotNull("Client ne doit pas être",clt);
		}
		catch (SQLException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void AuthentifierBadLogPw()
	{
		IClient clt = null;
		try{
		// mauvais login/password
			clt = DbUtilTest.util.authentifier("fd", "fd");
			Assert.assertNull(" mauvais login/password - Client devrait être null",clt);
		}
		catch (SQLException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void AuthentifierGoodLogBadPw()
	{
		IClient clt = null;
		try{
			//Bon login  / mauvais password
			clt = DbUtilTest.util.authentifier("df", "fd");
			Assert.assertNull("Bon login  / mauvais password - Client devrait être null",clt);
		}
		catch (SQLException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void AuthentifierBadLogGoodPw()
	{
		IClient clt = null;
		try{
			//Mauvais login / bon password
			clt = DbUtilTest.util.authentifier("fd", "df");
			Assert.assertNull("Mauvais login / bon password - Client devrait être null",clt);
		}
		catch (SQLException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void AuthentifierLogNullPw()
	{
		IClient clt = null;
		try{
			//Password null
			clt = DbUtilTest.util.authentifier("fd", null);
			Assert.assertNull("Password null - Client devrait être null",clt);
		}
		catch (SQLException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void AuthentifierNullLogPw()
	{
		IClient clt = null;
		try{
			//Login null
			clt = DbUtilTest.util.authentifier(null, "fd");
			Assert.assertNull("Login null - Client devrait être null",clt);
		}
		catch (SQLException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void AuthentifierNullLogNullPw()
	{
		IClient clt = null;
		try{
			// login et password null
			clt = DbUtilTest.util.authentifier(null, null);
			Assert.assertNull("login et password null - Client devrait être null",clt);
		}
		catch (SQLException e)
		{
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void recupererClient()
	{
		List<IClient> list=null;
		try
		{
			list = DbUtilTest.util.recupererClient();
			//Le retour ne doit pas être null.
			Assert.assertNotNull("La liste de compte est null", list);
			//3 clients en base doit retourné une liste de 3 clients
			Assert.assertEquals("Pas récupèrer le bon nombre d'entrée", list.size(),DbUtilTest.util.getNbClient());
		}
		catch (SQLException e)
		{
			Assert.fail(e.getMessage());
		}
	}
	@Test
	public void recupererCompte()
	{
		List<ICompte> list=null;
		try
		{
			list = DbUtilTest.util.recupererCompte();
			//Le retour ne doit pas être null.
			Assert.assertNotNull("La liste de compte est null", list);
			//3 clients en base doit retourné une liste de 6 clients
			Assert.assertEquals("Pas récupèrer le bon nombre d'entrée", list.size(),DbUtilTest.util.getNbCompte());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}
	@Test
	public void recupererOperation()
	{
		List<IOperation> list=null;
		try
		{
			list = DbUtilTest.util.recupererOperation();
			//Le retour ne doit pas être null.
			Assert.assertNotNull("La liste de compte est null", list);
			//3 clients en base doit retourné une liste de 56 clients
			Assert.assertEquals("Pas récupèrer le bon nombre d'entrée", list.size(),DbUtilTest.util.getNbOperation());
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	@Test
	public void recupererCompteByIDNotNull()
	{
		int idCompte = 12;
		ICompte cmpt = null;
		try
		{
			cmpt = DbUtilTest.util.recupererCompteByID(idCompte);
		}
		catch(SQLException e)
		{
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("Compte ne doit pas être null",cmpt);
		Assert.assertEquals("Le compte récupèrer n'est pas le bon ",idCompte, (cmpt.getNumero()).intValue());
	}

	@Test
	public void recupererCompteUtilisateurNotNull()
	{
		int idUtil = 12;

		List<ICompte> lcmpt = null;
		try
		{
			lcmpt = DbUtilTest.util.recupererCompteUtilisateur(idUtil);
		}
		catch(SQLException e)
		{
			Assert.fail(e.getMessage());
		}
		for (ICompte iCompte : lcmpt)
		{
			//Assert.assertEquals("Le compte récupèré n'est pas le bon ",idUtil, (lcmpt.getNumero()).intValue());
		}
		Assert.assertNotNull("Compte ne doit pas être null",lcmpt);
	}

	@Test
	public void faireVirementTest()
	{
		List<IOperation> list = null;
		try
		{
			list = DbUtilTest.util.faireVirement(13, 14, 100000);
		}
		catch(SQLException e)
		{
			Assert.fail(e.getMessage());
		}
		catch(BanqueException e)
		{
			Assert.fail(e.getMessage());
		}
		Assert.assertNotNull("La liste retournée est null", list);
	}

	@Test(expected=BanqueException.class)
	public void faireVirementTestMemecompte() throws BanqueException
	{
		List<IOperation> list = null;
		try
		{
			list = DbUtilTest.util.faireVirement(16, 16, 250);
		}
		catch(SQLException e)
		{
			Assert.fail(e.getMessage());
		}
		Assert.fail("Ne doit pas arriver ici car compteid1 : 16 == compteid2 : 16");
	}
	@Test(expected=BanqueException.class)
	public void faireVirementTestCompteInexistant() throws BanqueException
	{
		List<IOperation> list = null;
		try
		{
			list = DbUtilTest.util.faireVirement(11, 16, 250);
		}
		catch(SQLException e)
		{
			Assert.fail(e.getMessage());
		}
		Assert.fail("Ne doit pas arriver ici car compteid1 : 11 n'existe pas");
	}

	@Test
	public void rechercheOperationTest()
	{
		List<IOperation> list = null;
		try
		{
			Calendar cal1 = Calendar.getInstance();
			cal1.set(Calendar.YEAR, 2014);
			cal1.set(Calendar.MONTH, 12);
			cal1.set(Calendar.DAY_OF_MONTH, 15);
			Calendar cal2 = Calendar.getInstance();
			cal2.set(Calendar.YEAR, 2015);
			cal2.set(Calendar.MONTH, 10);
			cal2.set(Calendar.DAY_OF_MONTH, 28);
			list = DbUtilTest.util.rechercherOperation(14, new Date(cal1.getTimeInMillis()), new Date(cal2.getTimeInMillis()), Boolean.TRUE);
		}
		catch(SQLException e)
		{
			Assert.fail(e.getMessage());
		}
		catch(BanqueException e)
		{

		}
		Assert.assertNotNull("La liste retournée est null", list);
	}


	@Test(expected=BanqueException.class)
	public void rechercheOperationTestDateNull() throws BanqueException
	{
		List<IOperation> list = null;
		try
		{
			Calendar cal2 = Calendar.getInstance();
			cal2.set(Calendar.YEAR, 2015);
			cal2.set(Calendar.MONTH, 10);
			cal2.set(Calendar.DAY_OF_MONTH, 28);
			list = DbUtilTest.util.rechercherOperation(14, null, new Date(cal2.getTimeInMillis()), Boolean.TRUE);
		}
		catch(SQLException e)
		{
			Assert.fail(e.getMessage());
			e.printStackTrace();
		}
		Assert.assertNotNull("La liste retournée est null", list);
	}

	@AfterClass
	public static void after()
	{
		if(DbUtilTest.util != null)
		{
				DbUtilTest.util.close();
		}
	}

}
