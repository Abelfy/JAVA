package projetBank.fr.banque;

import projetBank.fr.banque.entities.Factory;
import projetBank.fr.banque.entities.IClient;
import projetBank.fr.banque.entities.ICompte;
import projetBank.fr.banque.entities.ICompteASeuil;
import projetBank.fr.banque.entities.ICompteRemunere;
import projetBank.fr.banque.entities.TypeCompte;

public class Run {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		Factory<IClient> fcc = new Factory<>(IClient.class);
		Factory<ICompteRemunere> fCmptRmnr = new Factory<>(ICompteRemunere.class);
		Factory<ICompteASeuil> fCompteASRmnr = new Factory<>(ICompteASeuil.class);


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

			if((i%2)==0)
			{
				objects[0]=TypeCompte.COURRANT;
			}
			else
			{
				objects[0]=TypeCompte.EPARGNE;
			}
			objects[1]=new Long(2500+i);
			objects[2]=new Float(0.03f+(i/1000f)); // On ajoute le d pour préciser que 1000 est un nombre a virgule flottante, de fait la division
			// n'est pas une division d'entier  ==> résultat flottant.
			ICompteRemunere cmptRmnr = fCmptRmnr.create(objects);
			try{
				c0.ajouterCompte(cmptRmnr);
			}
			catch(BanqueException e)
			{
				System.err.println(e.getMessage());
				break;
			}
		}

		for (ICompte cmptRmnr : c0.getComptes()) {
			if(cmptRmnr instanceof ICompteRemunere)
			{
				ICompteRemunere cTmp = (ICompteRemunere)cmptRmnr;
				cTmp.verserInterets();
			}
		}

		System.out.println(c0.toString());

		ICompteRemunere iCompt = fCmptRmnr.create(objects);

		objects=new Object[2];
		objects[0]=TypeCompte.COURRANT;
		objects[1]= new Double(250);


		ICompteASeuil iComptASRmnr2 = fCompteASRmnr.create(objects);

		try
		{
		iComptASRmnr2.retirer(new Double(260));
		}
		catch(BanqueException e)
		{
			System.err.println(e.getMessage());
		}
		System.out.println(iComptASRmnr2.toString());
		iCompt.verserInterets();
		System.out.println( iCompt.toString());

		long end = System.currentTimeMillis();
		System.out.println("Temps en ms :" + (end-start));


	}
}
