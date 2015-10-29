package fr.io;

import java.io.IOException;
import java.util.List;

public class Run
{

	public static void main(String[] args)
	{
		long debut = System.currentTimeMillis();
		// TODO Auto-generated method stub
		UtilisateurWriter uW = new UtilisateurWriter("C:/Users/abelfy/Documents/Exercice/Exo 16/RessoucesFiles/utilisateurs.csv");
		NomPrenomReader nPR = new NomPrenomReader();
		try
		{
		List<String> desNoms = nPR.readNom();
		List<String> desPrenoms = nPR.readPrenom();
		uW.writeUtilisateur(desNoms, desPrenoms, 20000);
		}
		catch(IOException e )
		{
			e.printStackTrace();
		}
		long fin = System.currentTimeMillis();
		System.out.println(" Temps en ms : " + (fin-debut));
	}

}
