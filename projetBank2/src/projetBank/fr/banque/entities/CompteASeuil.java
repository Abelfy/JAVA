package projetBank.fr.banque.entities;

import projetBank.fr.banque.BanqueException;

public class CompteASeuil extends Compte implements ICompteASeuil
{

	private Double seuil;

	public CompteASeuil(Long num, String label)
	{
		super(num, label);
	}

	/**
	 * @param type
	 * @param seuil
	 */
	public CompteASeuil(Long num, String label, Double seuil)
	{
		super(num, label, new Double(100));
		this.seuil = seuil;
	}

	/**
	 * @param type
	 * @param solde
	 * @param seuil
	 */
	public CompteASeuil(Long num, String label, Double solde, Double seuil)
	{
		super(num, label, solde);
		this.seuil = seuil;
	}

	@Override
	public Double getSeuil()
	{
		return this.seuil;
	}

	@Override
	public void setSeuil(Double seuil)
	{
		if (seuil.doubleValue() > 0)
		{
			this.seuil = seuil;
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length() - 1, builder.length());
		builder.append(", Seuil :");
		builder.append(this.getSeuil());
		builder.append(']');
		return builder.toString();
	}

	@Override
	public void retirer(Double uneValeur) throws BanqueException
	{
		if (this.seuil.doubleValue() >= uneValeur.doubleValue())
		{
			super.retirer(uneValeur);
		}
		else
		{
			throw new BanqueException("Retrait maximum autorisé : " + this.seuil);
		}
	}
}
