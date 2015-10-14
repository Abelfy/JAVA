package projetBank.fr.banque;

public class Run {

	public static void main(String[] args) {
		//long start = System.currentTimeMillis();


		Factory<Client> fcc = new Factory<>(Client.class);
		Factory<Compte> fcmpt = new Factory<>(Compte.class);

		Client c = fcc.create();

		Object[] objects=new Object[2];
		objects[0]=TypeCompte.COURRANT;
		objects[1]=(double)25;

		Compte cmpt = fcmpt.create(objects);

		objects=new Object[3];
		objects[0]="Belfy";
		objects[1]="Adrien";
		objects[2]=25;
		Client c0 = fcc.create(objects);

		System.out.println(cmpt.toString());
		System.out.println(c0.toString());

		/*FactoryClient fC = FactoryClient.getInstance();
		FactoryCompte fCmpt = FactoryCompte.getInstance();
		Client c4 = fC.getClient("BELFY");
		Client c5 = fC.getClient("BELFY","Adrien");
		Client c6 = fC.getClient("Belfy","Adrien", 25);
		System.out.println(c4.toString());
		System.out.println(c5.toString());
		System.out.println(c6.toString());
		Compte cmpt1 = fCmpt.createCompte(TypeCompte.COURRANT);
		Compte cmpt2 = fCmpt.createCompte(TypeCompte.EPARGNE,200);
		System.out.println(cmpt1.toString());
		System.out.println(cmpt2.toString());*/


		/*System.out.println("Press Any Key To Continue...");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        sc.close();*/




	}
}
