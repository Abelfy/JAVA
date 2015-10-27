/**
 * Copyright : Ferret Renaud 2002 <br/>
 *
 * @version 1.0<br/>
 */
package fr;

import fr.banque.FactoryClient;
import fr.banque.FactoryCompte;
import fr.banque.IClient;
import fr.banque.ICompte;
import fr.banque.ICompteASeuilRemunere;
import fr.banque.ICompteRemunere;

/**
 * La classe de lancement. <br/>
 */
public class Run {

	/**
	 * Lancement des tests. <br>
	 *
	 * @param args
	 *            les arguments de lancement
	 */
	public static void main(String[] args) {

		IClient client = FactoryClient.getInstance().create("Dupont", "Jhon", 25);
		ICompte c0 = FactoryCompte.getInstance().create(ICompte.class, 200d);
		ICompte c1 = FactoryCompte.getInstance().create(ICompte.class, 1000d);
		ICompte c2 = FactoryCompte.getInstance().create(ICompteASeuilRemunere.class, 200d, 0.5d, 100d);
		ICompte c3 = FactoryCompte.getInstance().create(ICompteRemunere.class, 400d, 0.10);
		ICompte c4 = FactoryCompte.getInstance().create(ICompteRemunere.class, 600d, 0.50d);

		client.ajouterCompte(c0);
		client.ajouterCompte(c1);
		client.ajouterCompte(c2);
		client.ajouterCompte(c3);
		client.ajouterCompte(c4);
		System.out.println(client);
		client.getCompte(2).retirer(500);

		// Versement des interets sur tous les comptes remuneres
		ICompte[] cpts = client.getComptes();
		for (int i = 0; i < cpts.length; i++) {
			ICompte compte = cpts[i];
			// On regarde si l'objet est 'une forme de'
			if (compte instanceof ICompteRemunere) {
				((ICompteRemunere) compte).verserInterets();
			}
		}

	}
}