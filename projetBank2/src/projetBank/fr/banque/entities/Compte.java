package projetBank.fr.banque.entities;

import projetBank.fr.banque.BanqueException;

class Compte implements ICompte {

	private Long numero;
	private TypeCompte type;
	private Double solde;

	protected Compte(Long num) {
		this(num,TypeCompte.COURRANT,new Double(100));
	}
	protected Compte(Long num, TypeCompte type) {
		this(num,type,new Double(100));
	}
	protected Compte(Long num,TypeCompte type, Double solde) {
		this.setType(type);
		this.setSolde(solde);
		this.numero = num;
	}

	@Override
	public void ajouter(Double unMontant){
		this.setSolde(Double.valueOf(this.getSolde().doubleValue()+unMontant.doubleValue()));
	}
	@Override
	public void retirer(Double unMontant) throws BanqueException{
		this.setSolde(Double.valueOf(this.getSolde().doubleValue()-unMontant.doubleValue()));
	}
	@Override
	public Long getNumero() {
		return this.numero;
	}
	@Override
	public TypeCompte getType() {
		return this.type;
	}
	@Override
	public Double getSolde() {
		return this.solde;
	}
	private void setType(TypeCompte type) {
		this.type = type;
	}
	private void setSolde(Double solde) {
		this.solde = solde;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName());
		builder.append(" [");
		builder.append("Numero compte = ");
		builder.append(this.getNumero());
		if (this.type != null) {
			builder.append(", type=");
			builder.append(this.type);
		}
		builder.append(", solde=");
		builder.append(this.solde);
		builder.append("]");
		return builder.toString();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj == null)
		{
			return false;
		}
		if(obj== this){
			return true;
		}
		if(obj instanceof Compte){
			Compte cTmp = (Compte)obj;
			return cTmp.getNumero() == this.getNumero();
		}

		return false;
	}

	@Override
	public int hashCode() {
		String b = this.getClass().getName()+"_"+this.toString();
		return b.hashCode();
	}




}

