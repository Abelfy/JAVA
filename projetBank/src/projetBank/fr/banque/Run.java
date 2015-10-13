package projetBank.fr.banque;

public class Run {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();



		Client c1 = new Client();
		System.out.println("Client 1 crée : \n"+c1.toString());
		Client c2 = new Client("Adrien","BELFY", 25);
		System.out.println("Client 2 crée : \n"+c2.toString());
		Client c3 = new Client("Adrien","BELFY", 26);
		System.out.println("Client 3 crée : \n"+c3.toString());

		c1.setNom("Dupont");
		c1.setPrenom("John");
		c1.setAge(52);
		System.out.println("Client 1 modifié : \n"+c1.toString());

		Compte compte1 = new Compte();
		System.out.println("Compte 1 crée : \n"+compte1.toString());
		Compte compte2 = new Compte(TypeCompte.EPARGNE);
		System.out.println("Compte 2 crée : \n"+compte2.toString());
		CompteRemunere compte3 = new CompteRemunere();
		System.out.println("Compte 3 crée : \n"+compte3.toString());
		CompteASeuil compte4 = new CompteASeuil(TypeCompte.EPARGNE,100);
		System.out.println("Compte 4 crée : \n"+compte4.toString());
		Compte compte5 = new Compte(TypeCompte.EPARGNE,5000);
		System.out.println("Compte 5 crée : \n"+compte5.toString());
		Compte compte6 = new Compte(TypeCompte.COURRANT);
		System.out.println("Compte 6 crée : \n"+compte6.toString());

		c1.ajouterCompte(compte1);
		c1.ajouterCompte(compte2);
		c1.ajouterCompte(compte3);
		c1.ajouterCompte(compte4);
		c1.ajouterCompte(compte5);
		c1.ajouterCompte(compte6);

		System.out.println("Comptes ajoutés au client 1 : \n"+c1.toString());

		for (Compte compte : c1.getComptes()) {
			compte.ajouter(1000);
			compte.ajouter(100);
			compte.retirer(200);
		}
		System.out.println("modification des comptes de client 1 : \n"+c1.toString());

		compte3.setTaux(0.03);
		compte3.verserInterets();

		System.out.println("Calcul d'interets de client 1 : \n"+c1.toString());


		long end = System.currentTimeMillis();
		System.out.println("Temps en ms : " + (end-start));
		/*System.out.println("Press Any Key To Continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();*/




	}
}
