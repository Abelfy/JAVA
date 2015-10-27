package projetBd.fr;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import projetBank.fr.banque.entities.Client;
import projetBank.fr.banque.entities.Compte;
import projetBank.fr.banque.entities.CompteASeuil;
import projetBank.fr.banque.entities.CompteASeuilRemunere;
import projetBank.fr.banque.entities.CompteRemunere;
import projetBank.fr.banque.entities.IClient;
import projetBank.fr.banque.entities.ICompte;
import projetBank.fr.banque.entities.IOperation;
import projetBank.fr.banque.entities.Operation;

public class DBUtil
{
	private String url = "jdbc:mysql://localhost:3306/banque";
	private String login = "root";
	private String password = "A130890b!!";
	Connection connexion = null;
	PreparedStatement ste = null;
	ResultSet resultat = null;

	/**
	 *
	 */
	public DBUtil()
	{
		// this("","","");
	}

	/**
	 * @param url
	 */
	public DBUtil(String url)
	{
		this.setUrl(url);
	}

	/**
	 * @param url
	 * @param login
	 * @param password
	 */
	public DBUtil(String url, String login, String password)
	{
		this.setUrl(url);
		this.setLogin(login);
		this.setPassword(password);
	}

	private String getLogin()
	{
		return this.login;
	}

	private String getPassword()
	{
		return this.password;
	}

	private String getUrl()
	{
		return this.url;
	}

	private void setLogin(String login)
	{
		this.login = login;
	}

	private void setPassword(String password)
	{
		this.password = password;
	}

	private void setUrl(String url)
	{
		this.url = url;
	}

	public void connexion() throws SQLException, ClassNotFoundException
	{
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		String nomDuDriver = "com.mysql.jdbc.Driver";
		try
		{
			cpds.setDriverClass(nomDuDriver);
			cpds.setUser(this.getLogin());
			cpds.setPassword(this.getPassword());
			cpds.setJdbcUrl(this.getUrl());
			cpds.setMaxPoolSize(151);
			cpds.setCheckoutTimeout(10000);;
			this.connexion=cpds.getConnection();
		}
		catch (PropertyVetoException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void close() throws SQLException
	{
		SQLException ez = null;
		try
		{
			if (this.resultat != null)
			{
				this.resultat.close();
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}

		try
		{
			if (this.ste != null)
			{
				this.ste.close();
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}

		try
		{
			if (this.connexion != null)
			{
				this.connexion.close();
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		if (ez != null)
		{
			throw ez;
		}
	}

	public List<IClient> recupererClient() throws SQLException
	{
		SQLException ez = null;
		List<IClient> list = new ArrayList<IClient>();
		try
		{
			String requete = "SELECT id,nom,prenom,Year(now()) - Year(dateDeNaissance) +"
					+ " (Format(dateDeNaissance, 'mmdd') > Format(now(), 'mmdd')) as age"
					+ ", sex,derniereConnection,adresse,codePostal,telephone FROM banque.utilisateur;";

			this.ste = this.connexion.prepareStatement(requete);
			// 4 - Recuperer le resulat
			this.resultat = this.ste.executeQuery();

			while (this.resultat.next())
			{
				IClient clt = new Client(Long.valueOf(this.resultat.getLong("id")), this.resultat.getString("nom"),
						this.resultat.getString("prenom"), Integer.valueOf(this.resultat.getInt("age")));
				clt.setSexe(this.resultat.getInt("sex"));
				clt.setDerniereConnexion(this.resultat.getDate("derniereConnection"));
				clt.setAdresse(this.resultat.getString("adresse"));
				clt.setCodePostal(Integer.valueOf(this.resultat.getInt("codePostal")));
				clt.setTelephone(this.resultat.getString("telephone"));
				list.add(clt);
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			try
			{
				this.resultat.close();
				try
				{
					this.ste.close();
				}
				catch (SQLException e)
				{
					ez = e;
				}
			}
			catch (SQLException e)
			{
				ez = e;
			}

		}
		if (ez != null)
		{
			throw ez;
		}
		return list;

	}

	public List<ICompte> recupererCompte() throws SQLException
	{
		SQLException ez = null;
		List<ICompte> list = new ArrayList<ICompte>();

		try
		{
			String requete = "SELECT id,libelle,solde,decouvert,taux FROM banque.compte;";
			this.ste = this.connexion.prepareStatement(requete);
			// 4 - Recuperer le resulat
			this.resultat = this.ste.executeQuery();
			while (this.resultat.next())
			{
				ICompte cmpt = null;
				String libelle = this.resultat.getString("libelle");
				Double solde = Double.valueOf(this.resultat.getDouble("solde"));
				Double decouvert = Double.valueOf(this.resultat.getDouble("decouvert"));
				if (this.resultat.wasNull())
				{
					Float taux = Float.valueOf(this.resultat.getFloat("taux"));
					if (this.resultat.wasNull())
					{
						cmpt = new Compte(Long.valueOf(this.resultat.getLong("id")), libelle, solde);
					}
					else
					{
						cmpt = new CompteRemunere(Long.valueOf(this.resultat.getLong("id")), libelle, solde, taux);
					}
				}
				else
				{
					Float taux = Float.valueOf(this.resultat.getFloat("taux"));
					if (this.resultat.wasNull())
					{
						cmpt = new CompteASeuil(Long.valueOf(this.resultat.getLong("id")), libelle, solde, decouvert);
					}
					else
					{
						cmpt = new CompteASeuilRemunere(Long.valueOf(this.resultat.getLong("id")), libelle, solde, taux,
								decouvert);
					}
				}
				list.add(cmpt);
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			try
			{
				this.resultat.close();
				try
				{
					this.ste.close();
				}
				catch (SQLException e)
				{
					ez = e;
				}
			}
			catch (SQLException e)
			{
				ez = e;
			}

		}
		if (ez != null)
		{
			throw ez;
		}
		return list;
	}

	public ICompte recupererCompteByID(int cpId) throws SQLException
	{
		SQLException ez = null;
		ICompte cmpt = null;

		try
		{
			String requete = "SELECT id,libelle,solde,decouvert,taux FROM banque.compte WHERE id=?;";
			this.ste = this.connexion.prepareStatement(requete);
			this.ste.setInt(1, cpId);
			// 4 - Recuperer le resulat
			this.resultat = this.ste.executeQuery();
			while (this.resultat.next())
			{

				String libelle = this.resultat.getString("libelle");
				Double solde = Double.valueOf(this.resultat.getDouble("solde"));
				Double decouvert = Double.valueOf(this.resultat.getDouble("decouvert"));
				if (this.resultat.wasNull())
				{
					Float taux = Float.valueOf(this.resultat.getFloat("taux"));
					if (this.resultat.wasNull())
					{
						cmpt = new Compte(Long.valueOf(this.resultat.getLong("id")), libelle, solde);
					}
					else
					{
						cmpt = new CompteRemunere(Long.valueOf(this.resultat.getLong("id")), libelle, solde, taux);
					}
				}
				else
				{
					Float taux = Float.valueOf(this.resultat.getFloat("taux"));
					if (this.resultat.wasNull())
					{
						cmpt = new CompteASeuil(Long.valueOf(this.resultat.getLong("id")), libelle, solde, decouvert);
					}
					else
					{
						cmpt = new CompteASeuilRemunere(Long.valueOf(this.resultat.getLong("id")), libelle, solde, taux,
								decouvert);
					}
				}
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			try
			{
				this.resultat.close();
				try
				{
					this.ste.close();
				}
				catch (SQLException e)
				{
					ez = e;
				}
			}
			catch (SQLException e)
			{
				ez = e;
			}

		}
		if (ez != null)
		{
			throw ez;
		}

		return cmpt;
	}

	public List<ICompte> recupererCompteUtilisateur(int userId) throws SQLException
	{
		SQLException ez = null;
		List<ICompte> list = new ArrayList<ICompte>();

		try
		{
			String requete = "SELECT id,libelle,solde,decouvert,taux FROM banque.compte WHERE utilisateurId=?;";
			this.ste.setInt(1, userId);
			this.ste = this.connexion.prepareStatement(requete);
			// 4 - Recuperer le resulat
			this.resultat = this.ste.executeQuery();
			while (this.resultat.next())
			{
				ICompte cmpt = null;
				String libelle = this.resultat.getString("libelle");
				Double solde = Double.valueOf(this.resultat.getDouble("solde"));
				Double decouvert = Double.valueOf(this.resultat.getDouble("decouvert"));
				if (this.resultat.wasNull())
				{
					Float taux = Float.valueOf(this.resultat.getFloat("taux"));
					if (this.resultat.wasNull())
					{
						cmpt = new Compte(Long.valueOf(this.resultat.getLong("id")), libelle, solde);
					}
					else
					{
						cmpt = new CompteRemunere(Long.valueOf(this.resultat.getLong("id")), libelle, solde, taux);
					}
				}
				else
				{
					Float taux = Float.valueOf(this.resultat.getFloat("taux"));
					if (this.resultat.wasNull())
					{
						cmpt = new CompteASeuil(Long.valueOf(this.resultat.getLong("id")), libelle, solde, decouvert);
					}
					else
					{
						cmpt = new CompteASeuilRemunere(Long.valueOf(this.resultat.getLong("id")), libelle, solde, taux,
								decouvert);
					}
				}
				list.add(cmpt);
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			try
			{
				this.resultat.close();
				try
				{
					this.ste.close();
				}
				catch (SQLException e)
				{
					ez = e;
				}
			}
			catch (SQLException e)
			{
				ez = e;
			}

		}
		if (ez != null)
		{
			throw ez;
		}
		return list;
	}

	public List<IOperation> recupererOperation() throws SQLException
	{
		SQLException ez = null;
		List<IOperation> list = new ArrayList<IOperation>();
		try
		{
			String requete = "SELECT id,libelle,montant,date FROM banque.operation;";

			this.ste = this.connexion.prepareStatement(requete);
			// 4 - Recuperer le resulat
			this.resultat = this.ste.executeQuery();

			while (this.resultat.next())
			{
				IOperation opr = new Operation(Long.valueOf(this.resultat.getLong("id")),
						this.resultat.getString("libelle"), Double.valueOf(this.resultat.getDouble("montant")),
						this.resultat.getDate("date"));
				list.add(opr);
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			try
			{
				this.resultat.close();
				try
				{
					this.ste.close();
				}
				catch (SQLException e)
				{
					ez = e;
				}
			}
			catch (SQLException e)
			{
				ez = e;
			}

		}
		if (ez != null)
		{
			throw ez;
		}
		return list;
	}

	public List<IOperation> rechercherOperation(int cpID, Date debut, Date fin, Boolean creditDebit) throws SQLException
	{
		SQLException ez = null;
		List<IOperation> list = new ArrayList<IOperation>();
		try
		{

			String requete = "SELECT * FROM banque.operation WHERE compteId=? AND banque.operation.date AND  ? < banque.operation.date AND  banque.operation.date < ?";
			this.ste = this.connexion.prepareStatement(requete);
			this.ste.setInt(1, cpID);
			this.ste.setDate(2, debut);
			if (fin == null)
			{
				fin = new Date(System.currentTimeMillis());
			}
			this.ste.setDate(3, fin);
			if (creditDebit == null)
			{
			}
			else
			{
				if (creditDebit.booleanValue())
				{
					requete += "AND montant>0";
				}
				else if (!creditDebit.booleanValue())
				{
					requete += "AND montant<0";
				}
			}
			// 4 - Recuperer le resulat
			this.resultat = this.ste.executeQuery();

			this.resultat.next();

			IOperation opr = new Operation(Long.valueOf(this.resultat.getLong("id")),
					this.resultat.getString("libelle"), Double.valueOf(this.resultat.getDouble("montant")),
					this.resultat.getDate("date"));
			list.add(opr);
		}

		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			try
			{
				this.resultat.close();
				try
				{
					this.ste.close();
				}
				catch (SQLException e)
				{
					ez = e;
				}
			}
			catch (SQLException e)
			{
				ez = e;
			}

		}
		if (ez != null)
		{
			throw ez;
		}
		return list;
	}

	public List<IOperation> faireVirement(int cpSrc, int cpDest, double somme) throws SQLException
	{
		SQLException ez = null;
		String updateRequete = "UPDATE banque.compte SET solde = solde + ? WHERE id=?";
		String insertRequete = "INSERT INTO banque.operation (libelle,montant,date,compteID) VALUES (?,?,?,?)";
		PreparedStatement updateCompte = null;
		PreparedStatement insertOperation = null;
		List<IOperation> list = null;
		try
		{
			this.connexion.setAutoCommit(false);
			Timestamp date = new Timestamp(System.currentTimeMillis());
			updateCompte = this.connexion.prepareStatement(updateRequete);
			insertOperation = this.connexion.prepareStatement(insertRequete, Statement.RETURN_GENERATED_KEYS);

			updateCompte.setDouble(1, somme);
			updateCompte.setInt(2, cpSrc);
			updateCompte.executeUpdate();
			insertOperation.setString(1, "Transaction avec le compte " + cpDest);
			insertOperation.setDouble(2, somme);
			insertOperation.setTimestamp(3, date);
			insertOperation.setInt(4, cpSrc);
			insertOperation.executeUpdate();
			this.resultat = insertOperation.getGeneratedKeys();
			this.resultat.next();
			int cle1 = this.resultat.getInt("GENERATED_KEY");

			updateCompte.setDouble(1, -somme);
			updateCompte.setInt(2, cpDest);
			updateCompte.executeUpdate();
			insertOperation.setString(1, "Transaction avec le compte " + cpSrc);
			insertOperation.setDouble(2, -somme);
			insertOperation.setTimestamp(3, date);
			insertOperation.setInt(4, cpDest);
			insertOperation.executeUpdate();
			this.resultat = insertOperation.getGeneratedKeys();
			this.resultat.next();
			int cle2 = this.resultat.getInt("GENERATED_KEY");

			list = new ArrayList<IOperation>();
			list.add(new Operation(Long.valueOf(cle1), "Transaction avec le compte " + cpDest, Double.valueOf(somme),
					new java.util.Date(date.getTime())));
			list.add(new Operation(Long.valueOf(cle2), "Transaction avec le compte " + cpDest, Double.valueOf(-somme),
					new java.util.Date(date.getTime())));
			this.connexion.commit();
		}
		catch (SQLException e)
		{
			ez = e;
			try
			{
				this.connexion.rollback();
			}
			catch (SQLException e1)
			{
				e1.printStackTrace();
				System.err.println("Erreur lors du rollback");
			}

		}
		finally
		{
			try
			{
				this.resultat.close();
				try
				{
					this.ste.close();
				}
				catch (SQLException e)
				{
					ez = e;
				}
			}
			catch (SQLException e)
			{
				ez = e;
			}

		}
		if (ez != null)
		{
			throw ez;
		}
		return list;
	}

	public IClient authentifier(String login, String password) throws SQLException
	{

		SQLException ez = null;
		IClient clt = null;
		try
		{
			String requete = "SELECT id,nom,prenom,(Year(now()) - Year(dateDeNaissance)) as age "
					+ ", sex,derniereConnection,adresse,codePostal,telephone FROM banque.utilisateur WHERE login=? AND password=?;";
			this.ste = this.connexion.prepareStatement(requete);
			this.ste.setString(1, login);
			this.ste.setString(2, password);

			// 4 - Recuperer le resulat
			this.resultat = this.ste.executeQuery();
			while (this.resultat.next())
			{
				clt = new Client(Long.valueOf(this.resultat.getLong("id")), this.resultat.getString("nom"),
						this.resultat.getString("prenom"), Integer.valueOf(this.resultat.getInt("age")));
				clt.setSexe(this.resultat.getInt("sex"));
				clt.setDerniereConnexion(this.resultat.getDate("derniereConnection"));
				clt.setAdresse(this.resultat.getString("adresse"));
				clt.setCodePostal(Integer.valueOf(this.resultat.getInt("codePostal")));
				clt.setTelephone(this.resultat.getString("telephone"));
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			try
			{
				this.resultat.close();
				try
				{
					this.ste.close();
				}
				catch (SQLException e)
				{
					ez = e;
				}
			}
			catch (SQLException e)
			{
				ez = e;
			}

		}
		if (ez != null)
		{
			throw ez;
		}
		return clt;
	}
}
