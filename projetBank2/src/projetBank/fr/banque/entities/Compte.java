package projetBank.fr.banque.entities;

	class Compte implements ICompte {

	private long numero;
	private TypeCompte type;
	private double solde;

	protected Compte(long num) {
		this(num,TypeCompte.COURRANT,100);
	}
	protected Compte(long num, TypeCompte type) {
		this(num,type,100);
	}
	protected Compte(long num,TypeCompte type, double solde) {
		this.setType(type);
		this.setSolde(solde);
		this.numero = num;
	}

	@Override
	public void ajouter(double unMontant){
		this.setSolde(this.getSolde()+unMontant);
	}
	@Override
	public void retirer(double unMontant){
		this.setSolde(this.getSolde()-unMontant);
	}
	@Override
	public long getNumero() {
		return this.numero;
	}
	@Override
	public TypeCompte getType() {
		return this.type;
	}
	@Override
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

