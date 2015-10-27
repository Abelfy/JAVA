package projetBank.fr.banque.entities;

import java.util.Date;

public class Operation implements IOperation
{

	private Long numero;
	private String libelle;
	private Double montant;
	private Date dateOperation;

	/**
	 *
	 */
	public Operation()
	{
	}

	/**
	 * @param numero
	 * @param libelle
	 * @param montant
	 * @param dateOperation
	 */
	public Operation(Long numero, String libelle, Double montant, Date dateOperation)
	{
		this.numero = numero;
		this.libelle = libelle;
		this.montant = montant;
		this.dateOperation = dateOperation;
	}

	@Override
	public Long getNumero()
	{
		return this.numero;
	}

	@Override
	public String getLibelle()
	{
		return this.libelle;
	}

	@Override
	public Double getMontant()
	{
		return this.montant;
	}

	@Override
	public Date getDateOperation()
	{
		return this.dateOperation;
	}

	@Override
	public void setNumero(Long numero)
	{
		this.numero = numero;
	}

	@Override
	public void setLibelle(String libelle)
	{
		this.libelle = libelle;
	}

	@Override
	public void setMontant(Double montant)
	{
		this.montant = montant;
	}

	@Override
	public void setDateOperation(Date dateOperation)
	{
		this.dateOperation = dateOperation;
	}

	@Override
	public String toString()
	{
		StringBuffer buff = new StringBuffer();
		buff.append(this.getClass().getName());
		buff.append(" [Numero operation  = ");
		buff.append(this.getNumero());
		buff.append(", libelle : ");
		buff.append(this.getLibelle());
		buff.append(", montant : ");
		buff.append(this.getMontant());
		buff.append(", date : ");
		buff.append(this.getDateOperation());
		buff.append(']');
		buff.append('\n');

		return buff.toString();
	}

}
