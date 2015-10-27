package fr.banque;

public interface ICompte {

	void setNumero(int numero);

	/**
	 * Donne acces au solde du compte. <br>
	 *
	 * @return le solde du compte
	 */
	double getSolde();

	/**
	 * Fixe le solde du compte. <br>
	 *
	 * @param unSolde
	 *            le nouveau solde du compte
	 */
	void setSolde(double unSolde);

	/**
	 * Donne acces au numero du compte. <br>
	 *
	 * @return le numero du compte
	 */
	int getNumero();

	/**
	 * Ajoute un montant au compte. <br>
	 *
	 * @param unMontant
	 *            le montant ajoute au compte
	 */
	void ajouter(double unMontant);

	/**
	 * Retire un montant du compte. <br>
	 *
	 * @param unMontant
	 *            le montant retire du compte
	 */
	void retirer(double unMontant);

}