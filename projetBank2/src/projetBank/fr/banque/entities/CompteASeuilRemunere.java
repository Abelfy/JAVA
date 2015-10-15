package projetBank.fr.banque.entities;

import projetBank.fr.banque.BanqueException;

class CompteASeuilRemunere extends CompteRemunere implements ICompteASeuilRemunere
	{

	private Double seuil;



	/**
	 * @param num
	 * @param type
	 */
	public CompteASeuilRemunere(Long num, TypeCompte type) {
		super(num, type);
	}
	/**
	 * @param num
	 * @param type
	 * @param seuil
	 */
	public CompteASeuilRemunere(Long num, TypeCompte type,Double solde,Float Taux, Double seuil) {
		super(num, type, solde,Taux);
		this.seuil = seuil;
	}




	@Override
	public void ajouter(Double unMontant) {
		super.ajouter(unMontant);
	}
	@Override
	public void retirer(Double unMontant) throws BanqueException
	{
		if(this.seuil.doubleValue() <= unMontant.doubleValue() )
		{
			super.retirer(unMontant);
		}
		else
		{
			throw new BanqueException("Retrait maximum autorisé :"+this.seuil );
		}
	}
	@Override
	public Long getNumero() {
		return super.getNumero();
	}
	@Override
	public TypeCompte getType() {
		return super.getType();
	}
	@Override
	public Double getSolde() {
		return super.getSolde();
	}
	@Override
	public Double getSeuil() {
		return this.seuil;
	}

	@Override
	public void setSeuil(Double seuil) {
		this.seuil = seuil;
	}
	@Override
	public Double calculerInterets() {
		return super.calculerInterets();
	}
	@Override
	public void verserInterets() {
		super.verserInterets();
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length()-1, builder.length());
		builder.append(", seuil=");
		builder.append(this.getSeuil());
		builder.append(']');
		return builder.toString();
	}
	@Override
	public Float getTaux() {
		return super.getTaux();
	}
	@Override
	public void setTaux(Float taux) {
		super.setTaux(taux);
	}
}
