package projetBank.fr.banque;

import projetBank.fr.banque.entities.Factory;
import projetBank.fr.banque.entities.IClient;
import projetBank.fr.banque.entities.ICompte;
import projetBank.fr.banque.entities.ICompteASeuil;
import projetBank.fr.banque.entities.ICompteASeuilRemunere;
import projetBank.fr.banque.entities.ICompteRemunere;
import projetBank.fr.banque.entities.TypeCompte;

public class Run {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		// Instanciation des factories.
		Factory<IClient> fcc = new Factory<>(IClient.class);
		Factory<ICompteRemunere> fCmptRmnr = new Factory<>(ICompteRemunere.class);
		Factory<ICompteASeuil> fCompteAS = new Factory<>(ICompteASeuil.class);
		Factory<ICompteASeuilRemunere> fCompteASRmnr= new Factory<>(ICompteASeuilRemunere.class);

		//Création d'un client.
		Object[] objects=new Object[3];
		objects[0]="Belfy";
		objects[1]="Adrien";
		objects[2]=new Integer(25);
		IClient c0 = fcc.create(objects);


		System.out.println(c0.toString());

		//Ajoute 5 compte rémunérer au client.
		objects=new Object[3];
		for (int i = 0; i < 6; i++)
		{
			try
			{
				if((i%2)==0)
				{
					objects[0]=TypeCompte.COURRANT;
				}
				else
				{
					objects[0]=TypeCompte.EPARGNE;
				}

				objects[1]=new Double(2500+i);
				objects[2]=new Float(0.03f+(i/1000f)); /*On ajoute le d pour préciser que 1000
														est un nombre a virgule flottante, de fait la division
														n'est pas une division d'entier  ==> résultat flottant.*/
				ICompteRemunere cmptRmnr = fCmptRmnr.create(objects);
				c0.ajouterCompte(cmptRmnr);
			}
			catch(BanqueException e)
			{
				System.err.println(e.getMessage());
				break;
			}
		}

		System.out.println(c0.toString());

		for (ICompte cmptRmnr : c0.getComptes()) {
			if(cmptRmnr instanceof ICompteRemunere)
			{
				ICompteRemunere cTmp = (ICompteRemunere)cmptRmnr;
				cTmp.verserInterets();
			}
		}

		System.out.println(c0.toString());

		ICompteRemunere iCompt = fCmptRmnr.create(objects);
		objects=new Object[4];
		objects[0]=TypeCompte.COURRANT;
		objects[1]= new Double(250);
		objects[2] = new Float(0.03);
		objects[3] = new Double(100);
		ICompteASeuilRemunere iComptASRmnr = fCompteASRmnr.create(objects);

		try
		{
		iComptASRmnr.retirer(new Double(260));
		}
		catch(BanqueException e)
		{
			System.err.println(e.getMessage());
		}
		System.out.println(iComptASRmnr.toString());
		iCompt.verserInterets();
		System.out.println( iCompt.toString());



		long end = System.currentTimeMillis();
		System.out.println("Temps en ms :" + (end-start));
		System.out.println(new Object().toString());


 	}
}
