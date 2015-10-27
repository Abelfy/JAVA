/**
 * Copyright : Ferret Renaud 2002 <br/>
 *
 * @version 1.0<br/>
 */
package fr.banque;

/**
 * Ceci est la classe Compte A seuil remunere. <br/>
 * Ceci est un exemple d'encapsulation, si on ne veut pas faire de
 * copier/coller, on fait de la delegation sur une instance de compte a seuil.
 */
public class CompteASeuilRemunere2 extends CompteRemunere implements
ICompteASeuil {

	private CompteASeuil compteASeuil;

	/**
	 * Constructeur. <br/>
	 * Le seuil par defaut est de 0
	 */
	public CompteASeuilRemunere2() {
		this(-1, 0, 0, 0);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero
	 *            le numero du compte
	 * @param unSoldeInitial
	 *            le solde initial du compte
	 * @param unTaux
	 *            un taux [0, 1[
	 * @param unSeuil
	 *            un seuil
	 */
	public CompteASeuilRemunere2(int unNumero, double unSoldeInitial,
			double unTaux, double unSeuil) {
		super(unNumero, unSoldeInitial, unTaux);
		this.compteASeuil = new CompteASeuil(unNumero, unSoldeInitial, unSeuil);
	}

	/**
	 * Recupere le seuil.
	 *
	 * @return le seuil
	 */
	@Override
	public double getSeuil() {
		return this.compteASeuil.getSeuil();
	}

	/**
	 * Modifie la valeur du seuil.
	 *
	 * @param seuil
	 *            le nouveau seuil
	 */
	@Override
	public void setSeuil(double unSeuil) {
		this.compteASeuil.setSeuil(unSeuil);
	}

	@Override
	public String toString() {
		return super.toString() + " Seuil=" + this.getSeuil();
	}

	@Override
	public void retirer(double unMontant) {
		// Il faut syncronizer les soldes
		this.compteASeuil.setSolde(this.getSolde());
		this.compteASeuil.retirer(unMontant);
		this.setSolde(this.compteASeuil.getSolde());
	}
}
