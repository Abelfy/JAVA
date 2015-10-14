package projetBank.fr.banque;

import projetBank.fr.entities.Client;
import projetBank.fr.entities.Compte;
import projetBank.fr.entities.CompteASeuilRemunere;
import projetBank.fr.entities.CompteRemunere;
import projetBank.fr.entities.Factory;
import projetBank.fr.entities.ICompteASeuil;
import projetBank.fr.entities.ICompteRemunere;
import projetBank.fr.entities.TypeCompte;

public class Run {

	public static void main(String[] args) {

		Factory<Client> fcc = new Factory<>(Client.class);
		Factory<CompteRemunere> fCmptRmnr = new Factory<>(CompteRemunere.class);
		Factory<CompteASeuilRemunere> fCompteASRmnr = new Factory<>(CompteASeuilRemunere.class);


		Object[] objects=new Object[3];
		objects[0]="Belfy";
		objects[1]="Adrien";
		objects[2]=25;
		Client c0 = fcc.create(objects);

		System.out.println(c0.toString());

		//Ajoute 5 compte rémunérer au client.
		for (int i = 0; i < 5; i++)
		{
			objects=new Object[3];
			objects[0]=TypeCompte.COURRANT;
			objects[1]=2500.0+i;
			objects[2]=(0.03f+(i/1000f)); // On ajoute le d pour préciser que 1000 est un nombre a virgule flottante, de fait la division
			// n'est pas une division d'entier  ==> résultat flottant.
			CompteRemunere cmptRmnr = fCmptRmnr.create(objects);
			c0.ajouterCompte(cmptRmnr);
		}

		for (Compte cmptRmnr : c0.getComptes()) {
			if(cmptRmnr.getClass() == CompteRemunere.class)
			{
				CompteRemunere cTmp = (CompteRemunere)cmptRmnr;
				cTmp.verserInterets();
			}
		}

		System.out.println(c0.toString());

		ICompteRemunere iCompt = fCmptRmnr.create(objects);
		objects=new Object[2];
		objects[0]=TypeCompte.COURRANT;
		objects[1]=250d;
		ICompteRemunere iComptASRmnr = fCompteASRmnr.create(objects);
		ICompteASeuil iComptASRmnr2 = fCompteASRmnr.create(objects);


		iComptASRmnr.verserInterets();
		System.out.println( iComptASRmnr.toString());
		iComptASRmnr2.retirer(200);
		System.out.println(iComptASRmnr2.toString());
		iCompt.verserInterets();
		System.out.println( iCompt.toString());


	}
}
