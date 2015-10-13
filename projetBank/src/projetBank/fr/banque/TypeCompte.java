package projetBank.fr.banque;

public enum TypeCompte {

	COURRANT(1,"Courrant"),
	EPARGNE(2,"Epargne");

	private final int numTypeCompte;
	private final String nomCompte;

	TypeCompte(int numTypeCompte,String nomCompte){
		this.numTypeCompte=numTypeCompte;
		this.nomCompte=nomCompte;
	}

	public int getNumTypeCompte() {
		return this.numTypeCompte;
	}

	public String getNomCompte() {
		return this.nomCompte;
	}
}
