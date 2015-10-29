package fr.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NomPrenomReader
{
	private String pathToNom = "C:/Users/abelfy/Documents/Exercice/Exo 16/RessoucesFiles/nom.txt";
	private String pathToPrenom = "C:/Users/abelfy/Documents/Exercice/Exo 16/RessoucesFiles/prenom.txt";

	/**
	 *
	 */
	public NomPrenomReader()
	{
	}



	/**
	 * @param pathToNom
	 * @param pathToPrenom
	 */
	public NomPrenomReader(String pathToNom, String pathToPrenom)
	{
		this.setPathToNom(pathToNom);
		this.setPathToPrenom(pathToPrenom);
	}


	public List<String> readNom() throws IOException
	{
		List<String> list = null;
		File file = new File(this.pathToNom);
		if(file.exists() && file.canRead())
		{
			try(FileReader fr = new FileReader(file); BufferedReader br =  new BufferedReader(fr))
			{
				list = new ArrayList<String>();
				String ligne = null;
				while((ligne = br.readLine()) != null)
				{
					list.add(ligne);
				}
			}
			 catch(IOException e)
			{
				 throw e;
			}
		}
		return list;
	}

	public List<String> readPrenom() throws IOException
	{
		List<String> list = null;
		File file = new File(this.pathToPrenom);
		if(file.exists() && file.canRead())
		{
			try(FileReader fr = new FileReader(file); BufferedReader br =  new BufferedReader(fr))
			{
				list = new ArrayList<String>();
				String ligne = null;
				while((ligne = br.readLine()) != null)
				{
					list.add(ligne);
				}
			}
			 catch(IOException e)
			{
				 throw e;
			}
		}

		return list;
	}

	public String getPathToNom()
	{
		return this.pathToNom;
	}



	public String getPathToPrenom()
	{
		return this.pathToPrenom;
	}

	public void setPathToNom(String pathToNom)
	{
		this.pathToNom = pathToNom;
	}



	public void setPathToPrenom(String pathToPrenom)
	{
		this.pathToPrenom = pathToPrenom;
	}
}
