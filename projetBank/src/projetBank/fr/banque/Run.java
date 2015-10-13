package projetBank.fr.banque;

public class Run {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		Compte compte = new Compte();
		Compte compte2 = new Compte(TypeCompte.EPARGNE);
		CompteRemunere compte3 = new CompteRemunere();
		Compte compte4 = new Compte(TypeCompte.EPARGNE);
		Compte compte5 = new Compte(TypeCompte.EPARGNE);
		Compte compte6 = new Compte(TypeCompte.COURRANT);

		System.out.println(compte3);
		if(compte.equals(compte2))
		{
			System.out.println("Egaux");
		}
		else
		{
			System.out.println("Pas egaux");
		}

		System.out.println(compte.toString());
		System.out.println(compte2.toString());
		Client client1 = new Client("Adrien","Belfy",25);

		client1.ajouterCompte(compte);
		client1.ajouterCompte(compte2);
		client1.ajouterCompte(compte3);
		client1.ajouterCompte(compte4);
		client1.ajouterCompte(compte5);
		System.out.println(client1);
		client1.ajouterCompte(compte6);
		System.out.println(client1);



		Compte[] comptes =client1.getComptes();
		//Test commit
		comptes[0].ajouter(1000);
		comptes[0].retirer(10);
		comptes[1].ajouter(-800);
		comptes[1].retirer(-900);
		comptes[4].retirer(-900);

		System.out.println(client1.toString());

		long end = System.currentTimeMillis();

		System.out.println("Temps en ms : " + (end-start));

		System.out.println("Press Any Key To Continue...");
        new java.util.Scanner(System.in).nextLine();


	}
}
