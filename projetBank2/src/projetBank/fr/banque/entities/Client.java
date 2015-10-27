package projetBank.fr.banque.entities;

import java.sql.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

import projetBank.fr.banque.BanqueException;

public class Client implements IClient
{

	private String prenom;
	private String nom;
	private Integer age;
	private Long numero;
	private int sexe;
	private java.util.Date derniereConnexion;
	private String adresse;
	private Integer codePostal;
	private String telephone;
	Map<Long, ICompte> listeCompte;

	public static final int NB_MAX_COMPTE = 10;

	/**
	 *
	 */
	public Client(Long num)
	{
		this(num, "", "", new Integer(0));
	}

	public Client(Long num, String nom)
	{
		this(num, nom, "", new Integer(0));
	}

	public Client(Long num, String nom, String prenom)
	{
		this(num, nom, prenom, new Integer(0));
	}

	public Client(Long num, String nom, String prenom, Integer age)
	{
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setAge(age);
		this.numero = num;
	}

	@Override
	public String getPrenom()
	{
		return this.prenom;
	}

	@Override
	public String getNom()
	{
		return this.nom;
	}

	@Override
	public Integer getAge()
	{
		return this.age;
	}

	@Override
	public Long getNumero()
	{
		return this.numero;
	}

	@Override
	public String getAdresse()
	{
		return this.adresse;
	}

	@Override
	public int getSexe()
	{
		return this.sexe;
	}

	@Override
	public Date getDerniereConnexion()
	{
		return new Date(this.derniereConnexion.getTime());
	}

	@Override
	public Integer getCodePostal()
	{
		return this.codePostal;
	}

	@Override
	public String getTelephone()
	{
		return this.telephone;
	}

	@Override
	public void setNumero(Long id)
	{
		this.numero = id;
	}

	@Override
	public void setPrenom(String prenom)
	{
		this.prenom = prenom;
	}

	@Override
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	@Override
	public void setAge(Integer age)
	{
		this.age = age;
	}

	@Override
	public void setSexe(int sexe)
	{
		this.sexe = sexe;
	}

	@Override
	public void setDerniereConnexion(Date date)
	{
		if (date != null)
		{
			java.util.Date madate = new java.util.Date(date.getTime());
			this.derniereConnexion = madate;
		}
	}

	@Override
	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}

	@Override
	public void setCodePostal(Integer codePostal)
	{
		this.codePostal = codePostal;
	}

	@Override
	public void setTelephone(String telephone)
	{
		this.telephone = telephone;
	}

	@Override
	public void setComptes(ICompte[] comptes) throws BanqueException
	{
		for (ICompte iCompte : comptes)
		{
			this.ajouterCompte(iCompte);
		}
	}

	@Override
	public void ajouterCompte(ICompte unCompte) throws BanqueException
	{
		if (this.listeCompte == null)
		{
			this.listeCompte = new Hashtable<Long, ICompte>();
			this.listeCompte.put(unCompte.getNumero(), unCompte);
		}
		else if (this.listeCompte.size() < Client.NB_MAX_COMPTE)
		{
			if (this.listeCompte.put(unCompte.getNumero(), unCompte) != null)
			{
				StringBuilder builder = new StringBuilder();
				builder.append("Le compte ");
				builder.append(unCompte.getNumero());
				builder.append(" est déja relier au client : ");
				builder.append(this.getNumero());
				builder.append(" - ");
				builder.append(this.nom);
				builder.append(" ");
				builder.append(this.prenom);
				throw new BanqueException(builder.toString());
			}

		}
		else
		{
			throw new BanqueException("Le client possède déja " + Client.NB_MAX_COMPTE + " comptes");
		}
	}

	@Override
	public ICompte getCompte(Long numeroCompte)
	{
		return this.listeCompte.get(numeroCompte);
	}

	@Override
	public ICompte[] getComptes()
	{
		return this.listeCompte.values().toArray(new ICompte[this.listeCompte.size()]);
	}

	@Override
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		buff.append(this.getClass().getName());
		buff.append(" [Numero client  = ");
		buff.append(this.getNumero());
		buff.append(", prenom : ");
		buff.append(this.getPrenom());
		buff.append(", nom : ");
		buff.append(this.getNom());
		buff.append(", age : ");
		buff.append(this.getAge());
		buff.append(']');
		buff.append('\n');
		if (this.listeCompte != null)
		{

			// buff.append(this.listeCompte); <=== C'est moche
			Iterator<ICompte> iter = this.listeCompte.values().iterator();
			while (iter.hasNext())
			{
				ICompte compte = iter.next();
				buff.append(compte.toString());
				buff.append('\n');
			}
			// for (ICompte compte : this.listeCompte.values())
			// {
			// buff.append(compte.toString()+'\n');
			// }
		}

		return buff.toString();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (obj == null)
		{
			return false;
		}
		if (obj == this)
		{
			return true;
		}
		if (obj.getClass() == Client.class)
		{
			Client cTmp = (Client) obj;
			return cTmp.getNumero() == this.getNumero();
			// return (this.getNom() == cTmp.getNom()) ||
			// (this.getNom().equals(cTmp.getNom()) &&
			// (this.getPrenom() == cTmp.getPrenom())) ||
			// ((this.getPrenom().equals(cTmp.getPrenom()) &&
			// (this.getAge() == cTmp.getAge()) &&
			// (this. getNumeroDeType() == cTmp. getNumeroDeType())) &&
			// this.comptes.equals(cTmp.comptes));
		}

		return false;
	}

	@Override
	public int hashCode()
	{
		String b = this.getClass().getName() + "_" + this.toString();
		return b.hashCode();
	}

}
