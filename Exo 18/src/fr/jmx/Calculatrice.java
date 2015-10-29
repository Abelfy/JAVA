package fr.jmx;

public class Calculatrice implements CalculatriceMBean
{
	private double dernierResultat;

	public Calculatrice()
	{

	}


	/* (non-Javadoc)
	 * @see fr.jmx.CalculatriceMBean#getDernierResultat()
	 */
	@Override
	public double getDernierResultat()
	{
		return this.dernierResultat;
	}


	private void setDernierResultat(double dernierResultat)
	{
		this.dernierResultat = dernierResultat;
	}


	@Override
	public double add(double c1, double c2)
	{
		this.setDernierResultat(c1+c2);
		return this.getDernierResultat();
	}

	@Override
	public double sub(double c1, double c2)
	{
		this.setDernierResultat(c1-c2);
		return this.getDernierResultat();
	}

	@Override
	public double mult(double c1, double c2)
	{
		this.setDernierResultat(c1*c2);
		return this.getDernierResultat();
	}

	@Override
	public double div(double c1, double c2)
	{
		if(c2 != 0)
		{
			this.setDernierResultat(c1/c2);
		}
		return this.getDernierResultat();
	}
}
