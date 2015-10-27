package projetBank.fr.banque.entities;

public class CompteRemunere extends Compte implements ICompteRemunere
{

	private Float taux;

	public CompteRemunere(Long num, String label)
	{
		super(num, label);
	}

	public CompteRemunere(Long num, String label, Float taux)
	{
		super(num, label);
		this.setTaux(taux);
	}

	public CompteRemunere(Long num, String label, Double solde, Float taux)
	{
		super(num, label, solde);
		this.setTaux(taux);
	}

	@Override
	public Double calculerInterets()
	{
		return (Double.valueOf(this.getTaux().floatValue() * this.getSolde().doubleValue()));
	}

	@Override
	public void verserInterets()
	{
		this.ajouter(this.calculerInterets());
	}

	@Override
	public Float getTaux()
	{
		return this.taux;
	}

	@Override
	public void setTaux(Float taux)
	{
		if ((taux.floatValue() < 1) && (taux.floatValue() > 0))
		{
			this.taux = taux;
		}
		else
		{
			this.taux = new Float(0);
			System.err.println("Taux doit etre compris entre 0 et 1");
		}
	}

	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.delete(builder.length() - 1, builder.length());
		builder.append(", taux=");
		builder.append(this.getTaux());
		builder.append(']');
		return builder.toString();
	}
}
