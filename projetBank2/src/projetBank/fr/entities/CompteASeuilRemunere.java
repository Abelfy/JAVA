package projetBank.fr.entities;

public class CompteASeuilRemunere extends CompteRemunere implements ICompteASeuil
{

	private double seuil;



	/**
	 * @param num
	 * @param type
	 */
	public CompteASeuilRemunere(long num, TypeCompte type) {
		super(num, type);
	}
	/**
	 * @param num
	 * @param type
	 * @param seuil
	 */
	public CompteASeuilRemunere(long num, TypeCompte type, double seuil) {
		super(num, type);
		this.seuil = seuil;
	}




	@Override
	public void ajouter(double unMontant) {
		// TODO Auto-generated method stub
		super.ajouter(unMontant);
	}
	@Override
	public void retirer(double unMontant) {
		// TODO Auto-generated method stub
		super.retirer(unMontant);
	}
	@Override
	public long getNumero() {
		// TODO Auto-generated method stub
		return super.getNumero();
	}
	@Override
	public TypeCompte getType() {
		// TODO Auto-generated method stub
		return super.getType();
	}
	@Override
	public double getSolde() {
		// TODO Auto-generated method stub
		return super.getSolde();
	}
	@Override
	public double getSeuil() {
		return this.seuil;
	}

	@Override
	public void setSeuil(double seuil) {
		this.seuil = seuil;
	}
	@Override
	public double calculerInterets() {
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
	public float getTaux() {
		return super.getTaux();
	}
	@Override
	public void setTaux(float taux) {
		super.setTaux(taux);
	}
}
