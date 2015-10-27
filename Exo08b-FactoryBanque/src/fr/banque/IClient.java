package fr.banque;

public interface IClient {

	/**
	 * Retourne l'age du client. <br/>
	 *
	 * @return l'age du client
	 */
	int getAge();

	/**
	 * Retourne le nom du client. <br/>
	 *
	 * @return le nom du client
	 */
	String getNom();

	/**
	 * Retourne le prenom du client. <br/>
	 *
	 * @return le prenom du client
	 */
	String getPrenom();

	/**
	 * Fixe l'age du client. <br/>
	 *
	 * @param unAge
	 *            le nouvel age du client
	 */
	void setAge(int unAge);

	/**
	 * Fixe le nom du client. <br/>
	 *
	 * @param unNom
	 *            le nouveau nom du client
	 */
	void setNom(String unNom);

	/**
	 * Fixe le numero du client. <br/>
	 * final = on ne peut pas la surcharger / refaire <br/>
	 *
	 * @param unNumero
	 *            le nouveau numero du client
	 */
	void setNumero(int unNumero);

	/**
	 * Retourne le numero du client. <br/>
	 *
	 * @return le numero du client, -1 si ce client n'a pas de numero
	 */
	int getNumero();

	/**
	 * Fixe le prenom du client. <br/>
	 *
	 * @param unPrenom
	 *            le nouveau prenom du client
	 */
	void setPrenom(String unPrenom);

	/**
	 * Retourne tous les comptes du client sous forme d'iterateur. <br/>
	 *
	 * @return la liste des comptes du client
	 */
	ICompte[] getComptes();

	/**
	 * Retourne un compte particulier. <br/>
	 *
	 * @param unNumero
	 *            numero du compte
	 * @return le compte vise ou null si il n'existe pas
	 */
	ICompte getCompte(int unNumero);

	/**
	 * Ajoute un compte dans la liste des comptes de l'utilisateur. <br/>
	 *
	 * @param unCompte
	 *            le compte a ajouter
	 * @throws IllegalArgumentException
	 *             si plus de place dans le tableau
	 */
	void ajouterCompte(ICompte unCompte);

}