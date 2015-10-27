package projetBank.fr.banque.entities;

import java.util.Date;

public interface IOperation
{

	public Long getNumero();

	public String getLibelle();

	public Double getMontant();

	public Date getDateOperation();

	public void setNumero(Long numero);

	public void setLibelle(String libelle);

	public void setMontant(Double montant);

	public void setDateOperation(Date dateOperation);

}
