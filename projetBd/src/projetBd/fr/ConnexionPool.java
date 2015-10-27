package projetBd.fr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class ConnexionPool
{
	private String URL;
	private String user;
	private String passWord;
	private String nomDuDriver;
	private List<Connection> connexions;
	public final static  int MAX_CONCURRENT_CONNECTIONS = 151;


	public Connection getConnexion()
	{
		if(this.connexions.isEmpty())
		{
			try
			{
				return DriverManager.getConnection(this.URL, this.user, this.passWord);
			}
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	//Class.forName(nomDuDriver);
	//this.connexion = DriverManager.getConnection(this.getUrl(), this.getLogin(), this.getPassword());

}
