package projetBank.fr.banque;

public class Compte {

	private TypeCompte type;
	private double solde;

	/**
	 *
	 */
	public Compte() {
		this(TypeCompte.COURRANT);
	}
	/**
	 * @param type
	 */
	public Compte(TypeCompte type) {
		super();
		this.type = type;
	}
	/**
	 * @param type
	 * @param solde
	 */
	public Compte(TypeCompte type, double solde) {
		super();
		this.type = type;
		this.solde = solde;
	}

	public void ajouter(double unMontant){

		this.setSolde(this.getSolde()+unMontant);
	}
	public void retirer(double unMontant){

		this.setSolde(this.getSolde()-unMontant);
	}

	public TypeCompte getType() {
		return this.type;
	}
	public double getSolde() {
		return this.solde;
	}
	private void setType(TypeCompte type) {
		this.type = type;
	}
	private void setSolde(double solde) {
		this.solde = solde;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName());
		builder.append(" [");
		if (this.type != null) {
			builder.append("type=");
			builder.append(this.type);
			builder.append(", ");
		}
		builder.append("solde=");
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
			String moi = this.toString();
			String lui = cTmp.toString();
			return moi.equals(lui);

			//return (this.getNom() == cTmp.getNom()) || (this.getNom().equals(cTmp.getNom()) &&
			//		(this.getPrenom() == cTmp.getPrenom())) || ((this.getPrenom().equals(cTmp.getPrenom()) &&
			//		(this.getAge() == cTmp.getAge()) &&
			//		(this. getNumeroDeType() == cTmp. getNumeroDeType())) &&
			//		this.comptes.equals(cTmp.comptes));
		}

		return false;
	}




}

