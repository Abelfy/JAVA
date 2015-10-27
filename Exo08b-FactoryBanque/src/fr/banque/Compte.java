/**
 * Copyright : Ferret Renaud 2002 <br/>
 *
 * @version 1.0<br/>
 */
package fr.banque;

/**
 * Ceci est la classe Compte. <br>
 */
class Compte implements ICompte, Comparable<ICompte> {
	private double solde;
	private int numero;

	/**
	 * Constructeur de l'objet. <br>
	 * Le numero par defaut sera -1.
	 */
	public Compte() {
		this(-1, 0d);
	}

	public Compte(int unNumero) {
		this(unNumero, 0d);
	}

	/**
	 * Constructeur de l'objet. <br>
	 *
	 * @param unNumero
	 *            le numero du compte
	 * @param unSoldeInitial
	 *            le solde initial du compte
	 */
	public Compte(int unNumero, double unSoldeInitial) {
		super();
		// On a choisit de ne pas faire de methode setNumero
		this.numero = unNumero;
		this.setSolde(unSoldeInitial);
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompte#setNumero(int)
	 */
	@Override
	public void setNumero(int numero) {
		this.numero = numero;
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompte#getSolde()
	 */
	@Override
	public double getSolde() {
		return this.solde;
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompte#setSolde(double)
	 */
	@Override
	public void setSolde(double unSolde) {
		this.solde = unSolde;
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompte#getNumero()
	 */
	@Override
	public int getNumero() {
		return this.numero;
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompte#ajouter(double)
	 */
	@Override
	public void ajouter(double unMontant) {
		this.setSolde(this.getSolde() + unMontant);
	}

	/* (non-Javadoc)
	 * @see fr.banque.ICompte#retirer(double)
	 */
	@Override
	public void retirer(double unMontant) {
		this.setSolde(this.getSolde() - unMontant);
	}

	/**
	 * Formatage du compte sous forme de String utilisable directement par
	 * System.out.println(..);. <br>
	 *
	 * @return une representation chainee de l'objet
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer(128);
		sb.append(this.getClass().getName());
		sb.append(" N°=");
		sb.append(this.getNumero());
		sb.append(" Solde=");
		sb.append(this.getSolde());
		return sb.toString();
	}

	/**
	 * Indique si deux comptes sont egaux. <br>
	 *
	 * Deux comptes sont egaux si ils ont le meme numero d'identification.
	 *
	 * @param obj
	 *            l'objet qui sera compare a this
	 * @return <code>true</code> si les deux sont egaux et <code>false</code>
	 *         sinon.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj instanceof Compte) {
			ICompte c = (ICompte) obj;
			return this.getNumero() == c.getNumero();
		}
		return false;
	}

	@Override
	public int compareTo(ICompte o) {
		if (this.equals(o)) {
			return 0;
		}
		if (this.getNumero() < o.getNumero()) {
			return -1;
		}
		return 1;
	}
}
