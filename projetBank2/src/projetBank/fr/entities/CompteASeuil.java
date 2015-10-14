package projetBank.fr.entities;

public class CompteASeuil extends Compte implements ICompteASeuil {

	private double seuil;

	protected CompteASeuil(long num,TypeCompte type){
		super(num,type);
	}
	/**
	 * @param type
	 * @param seuil
	 */
	protected CompteASeuil(long num,TypeCompte type, double seuil) {
		super(num,type,0);
		this.seuil = seuil;
	}

	/**
	 * @param type
	 * @param solde
	 * @param seuil
	 */
	protected CompteASeuil(long num,TypeCompte type, double solde, double seuil) {
		super(num,type, solde);
		this.seuil = seuil;
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
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length()-1, builder.length());
		builder.append(", Seuil :");
		builder.append(this.getSeuil());
		builder.append(']');
		return builder.toString();
	}
	@Override
	public void retirer(double uneValeur) {
		if(uneValeur <= this.seuil)
		{
			super.retirer(uneValeur);
		}
		else
		{
			System.err.println("Limite de retrait :" + this.seuil);
		}
	}
}
