package fr.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UtilisateurWriter
{
	private String pathToDest;

	/**
	 * Constructeur par d�faut
	 */
	public UtilisateurWriter()
	{
	}

	/**
	 * Prend en param�tre le chemin vers le fichier � cr�er
	 *
	 * @param pathToDest
	 */
	public UtilisateurWriter(String pathToDest)
	{
		this.setPathToDest(pathToDest);
	}

	public void writeUtilisateur(List<String> desNoms, List<String> desPrenoms, int combien) throws IOException
	{

		File file = new File(this.getPathToDest());
		Random rdm = new Random();
		if (file.exists())
		{

			System.out.println("Le fichier existe d�ja, voulez vous le remplacez o/n?");
			Scanner clavier = new Scanner(System.in);
			String reponse = clavier.nextLine();
			clavier.close();

			if (reponse.equalsIgnoreCase("o"))
			{
				if (file.canWrite())
				{
					try (FileWriter fw = new FileWriter(file); BufferedWriter br = new BufferedWriter(fw))
					{
						for (int i = 0; i < combien; i++)
						{
							String genre = null;
							String nom = desNoms.get(rdm.nextInt(desNoms.size())).trim();
							String prenom = desPrenoms.get(rdm.nextInt(desPrenoms.size())).trim();
							if ((rdm.nextInt(combien) % 2) == 0)
							{
								genre = "Mr";
							}
							else
							{
								genre = "Mme";
							}

							StringBuilder builder = new StringBuilder();
							builder.append(i);
							builder.append(';');
							builder.append(genre);
							builder.append(';');
							builder.append(nom);
							builder.append(';');
							builder.append(prenom);

							String ligne = builder.toString();
							br.write(ligne);
							br.newLine();
						}

						System.out.println("ok, fichier remplac�");
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}

				}
				else
				{
					System.out.println("impossible d'�crire");
				}
			}
		}
		else
		{
			file.createNewFile();

			try (FileWriter fw = new FileWriter(file); BufferedWriter br = new BufferedWriter(fw))
			{
				for (int i = 0; i < combien; i++)
				{
					String genre = null;
					String nom = desNoms.get(rdm.nextInt(desNoms.size())).trim();
					String prenom = desPrenoms.get(rdm.nextInt(desPrenoms.size())).trim();
					if ((rdm.nextInt(combien) % 2) == 0)
					{
						genre = "Mr";
					}
					else
					{
						genre = "Mme";
					}

					StringBuilder builder = new StringBuilder();
					builder.append(i);
					builder.append(';');
					builder.append(genre);
					builder.append(';');
					builder.append(nom);
					builder.append(';');
					builder.append(prenom);

					String ligne = builder.toString();
					br.write(ligne);
					br.newLine();
				}

				System.out.println("ok, fichier cr�e");
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

		}
	}

	public String getPathToDest()
	{
		return this.pathToDest;
	}

	public void setPathToDest(String pathToDest)
	{
		this.pathToDest = pathToDest;
	}

}
