package projetBank.fr.entities;

public class CompteRemunere extends Compte implements ICompteRemunere{

	private float taux;

	protected CompteRemunere(long num,TypeCompte type){
		super(num,type);
	}
	protected CompteRemunere(long num, TypeCompte type, float taux) {
		super(num, type);
		this.setTaux(taux);
	}
	protected CompteRemunere(long num, TypeCompte type,double solde, float taux) {
		super(num,type, solde);
		this.setTaux(taux) ;
	}

	@Override
	public double calculerInterets(){
		return (this.getTaux()*this.getSolde());
	}
	@Override
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
	@Override
	public float getTaux() {
		return this.taux;
	}
	@Override
	public void setTaux(float taux) {
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
