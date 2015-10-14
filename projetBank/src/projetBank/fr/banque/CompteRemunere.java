package projetBank.fr.banque;

public class CompteRemunere extends Compte {

	private double taux;

	public CompteRemunere(long num,TypeCompte type){
		super(num,type);
	}
	public CompteRemunere(long num, TypeCompte type, double taux) {
		super(num, type);
		this.setTaux(taux);
	}
	public CompteRemunere(long num, TypeCompte type,double solde, double taux) {
		super(num,type, solde);
		this.setTaux(taux) ;
	}

	public double calculerInterets(){
		return (this.getTaux()*this.getSolde());
	}
	public void verserInterets()
	{
		this.ajouter(this.calculerInterets());
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length()-1, builder.length());
		builder.append(", taux=");
		builder.append(this.getTaux());
		builder.append(']');
		return builder.toString();
	}
	public double getTaux() {
		return this.taux;
	}
	public void setTaux(double taux) {
		if((taux < 1) && (taux > 0))
		{
			this.taux = taux;
		}
		else
		{
			this.taux = 0 ;
			System.err.println("Taux doit etre compris entre 0 et 1");
		}
	}
}
