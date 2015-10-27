package fr.banque;

public final class FactoryCompte {
	private static FactoryCompte instance;

	private int numero;

	private FactoryCompte() {
		super();
		this.numero = 1;
	}

	public static synchronized FactoryCompte getInstance() {
		if (FactoryCompte.instance == null) {
			FactoryCompte.instance = new FactoryCompte();
		}
		return FactoryCompte.instance;
	}

	public ICompte create(Object... args) {
		ICompte resultat = null;
		if (args.length == 0) {
			resultat = new Compte(this.numero, 0d);
		} else if (args.length == 1) {
			Class type = (Class) args[0];
			if (type == ICompteASeuil.class) {
				resultat = new CompteASeuil(this.numero, 0d, 0d);
			} else if (type == ICompteRemunere.class) {
				resultat = new CompteRemunere(this.numero, 0d, 0d);
			} else if (type == ICompte.class) {
				resultat = new Compte(this.numero, 0d);
			} else if (type == ICompteASeuilRemunere.class) {
				resultat = new CompteASeuilRemunere(this.numero, 0d, 0d, 0d);
			}
		} else if (args.length == 2) {
			Class type = (Class) args[0];
			Double solde = (Double) args[1];
			resultat = new Compte(this.numero, solde);
		} else if (args.length == 3) {
			Class type = (Class) args[0];
			Double solde = (Double) args[1];
			Double tauxSeuil = (Double) args[2];
			if (type == ICompteASeuil.class) {
				resultat = new CompteASeuil(this.numero, solde, tauxSeuil);
			} else if (type == ICompteRemunere.class) {
				resultat = new CompteRemunere(this.numero, solde, tauxSeuil);
			}
		} else if (args.length == 4) {
			Class type = (Class) args[0];
			Double solde = (Double) args[1];
			Double taux = (Double) args[2];
			Double seuil = (Double) args[3];
			resultat = new CompteASeuilRemunere(this.numero, solde, taux, seuil);
		}


		this.numero++;
		return resultat;
	}

}
