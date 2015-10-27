/**
 * Copyright : Ferret Renaud 2002 <br/>
 *
 * @version 1.0<br/>
 */
package fr.banque;

/**
 * Ceci est la classe Compte a seuil. <br/>
 * Il n'est pas possible de descendre en dessous du seuil.
 */
class CompteASeuil extends Compte implements ICompteASeuil {
	private double seuil;

	/**
	 * Constructeur. <br/>
	 * Le seuil par defaut est de 0
	 */
	public CompteASeuil() {
		this(-1, 0, 0);
	}

	public CompteASeuil(int unNumero) {
		this(unNumero, 0d, 0d);
	}
	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero
	 *            le numero du compte
	 * @param unSoldeInitial
	 *            le solde initial du compte
	 * @param unSeuil
	 *            un seuil
	 */
	public CompteASeuil(int unNumero, double unSoldeInitial, double unSeuil) {
		super(unNumero, unSoldeInitial);
		this.setSeuil(unSeuil);
	}

	@Override
	public double getSeuil() {
		return this.seuil;
	}

	@Override
	public void setSeuil(double unSeuil) {
		this.seuil = unSeuil;
	}

	@Override
	public String toString() {
		return super.toString() + " Seuil=" + this.getSeuil();
	}

	@Override
	public void retirer(double unMontant) {
		double simu = this.getSolde() - unMontant;
		if (simu <= this.getSeuil()) {
			// Pas bon, on ne fait rien, pour l'instant
		} else {
			super.retirer(unMontant);
		}
	}
}
