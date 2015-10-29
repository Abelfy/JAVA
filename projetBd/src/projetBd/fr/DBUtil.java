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

import projetBank.fr.banque.BanqueException;
import projetBank.fr.banque.DateInvalid;
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
	private ComboPooledDataSource cpds = null;
	private Connection connexion = null;


	/**
	 * @param url
	 * @param login
	 * @param password
	 */
	public DBUtil(String url, String login, String password)
	{
		this.cpds = new ComboPooledDataSource();
		String nomDuDriver = "com.mysql.jdbc.Driver";
		try
		{
			this.cpds.setDriverClass(nomDuDriver);
			this.cpds.setUser(login);
			this.cpds.setPassword(password);
			this.cpds.setJdbcUrl(url);
			this.cpds.setMaxPoolSize(151);
			this.cpds.setCheckoutTimeout(10000);
		}
		catch (PropertyVetoException e)
		{
			e.printStackTrace();
		}
	}

	public void connexion()
	{
		try
		{
			this.connexion= this.cpds.getConnection();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void closeReaders( Statement ste ,ResultSet resultat) throws SQLException
	{
		if(resultat != null)
		{
			try
			{
				resultat.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
		if(ste != null)
		{
			try
			{
				ste.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}

	public void close()
	{
		if(this.connexion != null)
		{
			try
			{
				this.connexion.close();
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	public List<IClient> recupererClient() throws SQLException
	{
		SQLException ez = null;
		List<IClient> list = new ArrayList<IClient>();
		PreparedStatement ste = null;
		ResultSet resultat = null;;
		try
		{
			String requete = "SELECT id,nom,prenom,Year(now()) - Year(dateDeNaissance) +"
					+ " (Format(dateDeNaissance, 'mmdd') > Format(now(), 'mmdd')) as age"
					+ ", sex,derniereConnection,adresse,codePostal,telephone FROM banque.utilisateur;";

			ste = this.connexion.prepareStatement(requete);
			// 4 - Recuperer le resulat
			resultat = ste.executeQuery();

			while (resultat.next())
			{
				IClient clt = new Client(Long.valueOf(resultat.getLong("id")), resultat.getString("nom"),
						resultat.getString("prenom"), Integer.valueOf(resultat.getInt("age")));
				clt.setSexe(resultat.getInt("sex"));
				clt.setDerniereConnexion(resultat.getDate("derniereConnection"));
				clt.setAdresse(resultat.getString("adresse"));
				clt.setCodePostal(Integer.valueOf(resultat.getInt("codePostal")));
				clt.setTelephone(resultat.getString("telephone"));
				list.add(clt);
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			this.closeReaders(ste, resultat);

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
		PreparedStatement ste = null;
		ResultSet resultat = null;

		try
		{
			String requete = "SELECT id,libelle,solde,decouvert,taux FROM banque.compte;";
			ste = this.connexion.prepareStatement(requete);
			// 4 - Recuperer le resulat
			resultat = ste.executeQuery();
			while (resultat.next())
			{
				ICompte cmpt = null;
				String libelle = resultat.getString("libelle");
				Double solde = Double.valueOf(resultat.getDouble("solde"));
				Double decouvert = Double.valueOf(resultat.getDouble("decouvert"));
				if (resultat.wasNull())
				{
					Float taux = Float.valueOf(resultat.getFloat("taux"));
					if (resultat.wasNull())
					{
						cmpt = new Compte(Long.valueOf(resultat.getLong("id")), libelle, solde);
					}
					else
					{
						cmpt = new CompteRemunere(Long.valueOf(resultat.getLong("id")), libelle, solde, taux);
					}
				}
				else
				{
					Float taux = Float.valueOf(resultat.getFloat("taux"));
					if (resultat.wasNull())
					{
						cmpt = new CompteASeuil(Long.valueOf(resultat.getLong("id")), libelle, solde, decouvert);
					}
					else
					{
						cmpt = new CompteASeuilRemunere(Long.valueOf(resultat.getLong("id")), libelle, solde, taux,
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
			this.closeReaders(ste, resultat);
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
		PreparedStatement ste = null;
		ResultSet resultat = null;

		try
		{
			String requete = "SELECT id,libelle,solde,decouvert,taux FROM banque.compte WHERE id=?;";
			ste = this.connexion.prepareStatement(requete);
			ste.setInt(1, cpId);
			// 4 - Recuperer le resulat
			resultat = ste.executeQuery();
			while (resultat.next())
			{

				String libelle = resultat.getString("libelle");
				Double solde = Double.valueOf(resultat.getDouble("solde"));
				Double decouvert = Double.valueOf(resultat.getDouble("decouvert"));
				if (resultat.wasNull())
				{
					Float taux = Float.valueOf(resultat.getFloat("taux"));
					if (resultat.wasNull())
					{
						cmpt = new Compte(Long.valueOf(resultat.getLong("id")), libelle, solde);
					}
					else
					{
						cmpt = new CompteRemunere(Long.valueOf(resultat.getLong("id")), libelle, solde, taux);
					}
				}
				else
				{
					Float taux = Float.valueOf(resultat.getFloat("taux"));
					if (resultat.wasNull())
					{
						cmpt = new CompteASeuil(Long.valueOf(resultat.getLong("id")), libelle, solde, decouvert);
					}
					else
					{
						cmpt = new CompteASeuilRemunere(Long.valueOf(resultat.getLong("id")), libelle, solde, taux,
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
			if(resultat != null)
			{
				this.closeReaders(ste, resultat);
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
		ResultSet resultat = null;
		PreparedStatement ste = null;

		try
		{
			String requete = "SELECT id,libelle,solde,decouvert,taux FROM banque.compte WHERE utilisateurId=?;";
			ste = this.connexion.prepareStatement(requete);
			ste.setInt(1, userId);
			// 4 - Recuperer le resulat
			resultat = ste.executeQuery();
			while (resultat.next())
			{
				ICompte cmpt = null;
				String libelle = resultat.getString("libelle");
				Double solde = Double.valueOf(resultat.getDouble("solde"));
				Double decouvert = Double.valueOf(resultat.getDouble("decouvert"));
				if (resultat.wasNull())
				{
					Float taux = Float.valueOf(resultat.getFloat("taux"));
					if (resultat.wasNull())
					{
						cmpt = new Compte(Long.valueOf(resultat.getLong("id")), libelle, solde);
					}
					else
					{
						cmpt = new CompteRemunere(Long.valueOf(resultat.getLong("id")), libelle, solde, taux);
					}
				}
				else
				{
					Float taux = Float.valueOf(resultat.getFloat("taux"));
					if (resultat.wasNull())
					{
						cmpt = new CompteASeuil(Long.valueOf(resultat.getLong("id")), libelle, solde, decouvert);
					}
					else
					{
						cmpt = new CompteASeuilRemunere(Long.valueOf(resultat.getLong("id")), libelle, solde, taux,
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
			this.closeReaders(ste, resultat);
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
		ResultSet resultat = null;
		PreparedStatement ste = null;

		try
		{
			String requete = "SELECT id,libelle,montant,date FROM banque.operation;";

			ste = this.connexion.prepareStatement(requete);
			// 4 - Recuperer le resulat
			resultat = ste.executeQuery();

			while (resultat.next())
			{
				IOperation opr = new Operation(Long.valueOf(resultat.getLong("id")),
						resultat.getString("libelle"), Double.valueOf(resultat.getDouble("montant")),
						resultat.getDate("date"));
				list.add(opr);
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			this.closeReaders(ste, resultat);
		}
		if (ez != null)
		{
			throw ez;
		}
		return list;
	}

	public List<IOperation> rechercherOperation(int cpID, Date debut, Date fin, Boolean creditDebit) throws SQLException,BanqueException
	{
		SQLException ez = null;
		List<IOperation> list = new ArrayList<IOperation>();
		ResultSet resultat = null;
		PreparedStatement ste = null;
		String requete = "SELECT * FROM banque.operation WHERE compteId=? AND banque.operation.date AND  ? < banque.operation.date AND  banque.operation.date < ?";


		// si la date de début est supérieur à la date de fin on leve une exception.
		if ((debut != null) && (fin != null))
		{
			if(debut.after(fin))
			{
				throw new DateInvalid("Format de date invalide : Date début > date fin");
			}
		}
		// si la date de debut est null on leve une exception DateInvalid
		if (debut == null)
		{
			throw new DateInvalid("La date de début ne peut pas être null");
		}
		// si la date de fin est null alors la date de fin est aujourd'hui
		if (fin == null)
		{
			fin = new Date(System.currentTimeMillis());
		}
		// Si creditDebit est null on ne filtre pas credit/debit
		if (creditDebit == null)
		{
		}
		else
		{
			// Si creditDebit est true on ne filtre pas credit
			if (creditDebit.booleanValue())
			{
				requete += "AND montant>0";
			}
			// Si creditDebit est true on ne filtre pas debit
			else if (!creditDebit.booleanValue())
			{
				requete += "AND montant<0";
			}
		}


		try
		{
			//Preparation de la requete
			ste = this.connexion.prepareStatement(requete);
			ste.setInt(1, cpID);
			ste.setDate(2, debut);
			ste.setDate(3, fin);
			// 4 - Recuperer le resultat
			resultat = ste.executeQuery();

			while(resultat.next())
			{
			IOperation opr = new Operation(Long.valueOf(resultat.getLong("id")),
					resultat.getString("libelle"), Double.valueOf(resultat.getDouble("montant")),
					resultat.getDate("date"));
			list.add(opr);
			}
		}

		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			this.closeReaders(ste, resultat);
		}
		if (ez != null)
		{
			throw ez;
		}
		return list;
	}

	public List<IOperation> faireVirement(int cpSrc, int cpDest, double somme) throws SQLException,BanqueException
	{
		if(cpSrc == cpDest)
		{
			throw new BanqueException("Impossible de faire un virement d'un compte vers lui même");
		}
		SQLException ez = null;
		String updateRequete = "UPDATE banque.compte SET solde = solde + ? WHERE id=?";
		String insertRequete = "INSERT INTO banque.operation (libelle,montant,date,compteID) VALUES (?,?,?,?)";
		PreparedStatement updateCompte = null;
		PreparedStatement insertOperation = null;
		ResultSet resultat1 =null;
		ResultSet resultat2 =null;
		List<IOperation> list = null;
		try
		{
			this.connexion.setAutoCommit(false);
			Timestamp date = new Timestamp(System.currentTimeMillis());
			updateCompte = this.connexion.prepareStatement(updateRequete);
			insertOperation = this.connexion.prepareStatement(insertRequete, Statement.RETURN_GENERATED_KEYS);

			updateCompte.setDouble(1, somme);
			updateCompte.setInt(2, cpSrc);
			int rowCount = updateCompte.executeUpdate();
			if (rowCount < 1)
			{
				throw new BanqueException("Tentative de transaction avec un compte inexistant !");
			}
			else if(rowCount > 1)
			{
				throw new BanqueException("Il existe plusieurs compte avec le même identifiants !");
			}
			insertOperation.setString(1, "Transaction avec le compte " + cpDest);
			insertOperation.setDouble(2, somme);
			insertOperation.setTimestamp(3, date);
			insertOperation.setInt(4, cpSrc);
			insertOperation.executeUpdate();
			resultat1 = insertOperation.getGeneratedKeys();
			resultat1.next();
			int cle1 = resultat1.getInt("GENERATED_KEY");

			updateCompte.setDouble(1, -somme);
			updateCompte.setInt(2, cpDest);
			rowCount = updateCompte.executeUpdate();
			if (rowCount < 1)
			{
				throw new BanqueException("Tentative de transaction avec un compte inexistant !");
			}
			else if(rowCount > 1)
			{
				throw new BanqueException("Il existe plusieurs compte avec le même identifiants !");
			}
			insertOperation.setString(1, "Transaction avec le compte " + cpSrc);
			insertOperation.setDouble(2, -somme);
			insertOperation.setTimestamp(3, date);
			insertOperation.setInt(4, cpDest);
			insertOperation.executeUpdate();
			resultat2 = insertOperation.getGeneratedKeys();
			resultat2.next();
			int cle2 = resultat2.getInt("GENERATED_KEY");

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
			this.closeReaders(updateCompte , resultat1);
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
		ResultSet resultat = null;
		PreparedStatement ste = null;
		try
		{
			String requete = "SELECT id,nom,prenom,(Year(now()) - Year(dateDeNaissance)) as age "
					+ ", sex,derniereConnection,adresse,codePostal,telephone FROM banque.utilisateur WHERE login=? AND password=?;";
			ste = this.connexion.prepareStatement(requete);
			ste.setString(1, login);
			ste.setString(2, password);

			// 4 - Recuperer le resulat
			resultat = ste.executeQuery();
			while (resultat.next())
			{
				clt = new Client(Long.valueOf(resultat.getLong("id")), resultat.getString("nom"),
						resultat.getString("prenom"), Integer.valueOf(resultat.getInt("age")));
				clt.setSexe(resultat.getInt("sex"));
				clt.setDerniereConnexion(resultat.getDate("derniereConnection"));
				clt.setAdresse(resultat.getString("adresse"));
				clt.setCodePostal(Integer.valueOf(resultat.getInt("codePostal")));
				clt.setTelephone(resultat.getString("telephone"));
			}
		}
		catch (SQLException e)
		{
			ez = e;
		}
		finally
		{
			this.closeReaders( ste , resultat);
		}
		if (ez != null)
		{
			throw ez;
		}
		return clt;
	}
	public int getNbClient() throws SQLException
	{
		SQLException ez=null ;
		String selectCount = "SELECT Count(1) as nb FROM banque.utilisateur;";
		ResultSet resultat = null;
		Statement ste = null;
		int resu = -1;
		try
		{
			ste = this.connexion.createStatement();
			resultat = ste.executeQuery(selectCount);
			resultat.next();
			resu = resultat.getInt("nb");
		}
		catch(SQLException e )
		{
			ez = e;
		}
		finally
		{
			this.closeReaders(ste, resultat);
		}
		if(ez != null)
		{
			throw ez;
		}
		return resu;
	}
	public int getNbCompte() throws SQLException
	{
		String selectCount = "SELECT Count(1) as nb FROM banque.compte;";
		SQLException ez=null ;
		ResultSet resultat = null;
		Statement ste = null;
		int resu = -1;
		try
		{
			ste = this.connexion.createStatement();
			resultat = ste.executeQuery(selectCount);
			resultat.next();
			resu = resultat.getInt("nb");
		}
		catch(SQLException e )
		{
			ez = e;
		}
		finally
		{
			this.closeReaders(ste, resultat);
		}
		if(ez != null)
		{
			throw ez;
		}
		return resu;
	}
	public int getNbOperation() throws SQLException
	{
		String selectCount = "SELECT Count(1) as nb FROM banque.operation;";
		SQLException ez=null ;
		ResultSet resultat = null;
		Statement ste = null;
		int resu = -1;
		try
		{
			ste = this.connexion.createStatement();
			resultat = ste.executeQuery(selectCount);
			resultat.next();
			resu = resultat.getInt("nb");
		}
		catch(SQLException e )
		{
			ez = e;
		}
		finally
		{
			this.closeReaders(ste, resultat);
		}
		if(ez != null)
		{
			throw ez;
		}
		return resu;
	}
}
