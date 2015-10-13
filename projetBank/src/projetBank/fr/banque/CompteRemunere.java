package projetBank.fr.banque;

public class CompteRemunere extends Compte {

	private double taux;


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(this.getClass().getSimpleName());
		builder.append(", taux :");
		builder.append(this.getTaux());
		return builder.toString();
	}

	public double getTaux() {
		return this.taux;
	}

	public void setTaux(double taux) {
		if((taux < 1) && (taux > 0)) {
			this.taux = taux;
		}
	}

}
