package fr.banque;

public final class FactoryClient {
	private static FactoryClient instance;

	private int numero;

	private FactoryClient() {
		super();
		this.numero = 1;
	}

	public static synchronized FactoryClient getInstance() {
		if (FactoryClient.instance == null) {
			FactoryClient.instance = new FactoryClient();
		}
		return FactoryClient.instance;
	}

	public IClient create() {
		IClient resultat = new Client();
		resultat.setNumero(this.numero);
		this.numero++;
		return resultat;
	}

	public IClient create(String unNom, String unPrenom, int unAge) {
		IClient resultat = new Client(this.numero, unNom, unPrenom, unAge);
		this.numero++;
		return resultat;
	}

	private class Client implements IClient {
		private String nom;
		private String prenom;
		private int age;
		private int numero;
		private ICompte[] tabComptes;

		/**
		 * Constructeur de l'objet. <br/>
		 * Par defait le client aura un numero = -1 et un age de 0
		 */
		Client() {
			this(-1, null, null, 0);
		}

		/**
		 * Constructeur de l'objet. <br/>
		 *
		 * @param unNumero
		 *            un numero
		 * @param unNom
		 *            le nom du client
		 * @param unPrenom
		 *            le prenom du client
		 * @param unAge
		 *            l'age du client
		 */
		Client(int unNumero, String unNom, String unPrenom, int unAge) {
			super();
			this.setNom(unNom);
			this.setPrenom(unPrenom);
			this.setAge(unAge);
			// Par defaut dans un tableau j'ai des cases null (ou zero si
			// entier)
			this.tabComptes = new ICompte[5];
			this.setNumero(unNumero);
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#getAge()
		 */
		@Override
		public int getAge() {
			return this.age;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#getNom()
		 */
		@Override
		public String getNom() {
			return this.nom;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#getPrenom()
		 */
		@Override
		public String getPrenom() {
			return this.prenom;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#setAge(int)
		 */
		@Override
		public void setAge(int unAge) {
			this.age = unAge;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#setNom(java.lang.String)
		 */
		@Override
		public void setNom(String unNom) {
			this.nom = unNom;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#setNumero(int)
		 */
		@Override
		public final void setNumero(int unNumero) {
			this.numero = unNumero;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#getNumero()
		 */
		@Override
		public final int getNumero() {
			return this.numero;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#setPrenom(java.lang.String)
		 */
		@Override
		public void setPrenom(String unPrenom) {
			this.prenom = unPrenom;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#getComptes()
		 */
		@Override
		public ICompte[] getComptes() {
			return this.tabComptes;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#getCompte(int)
		 */
		@Override
		public ICompte getCompte(int unNumero) {
			for (int i = 0; i < this.tabComptes.length; i++) {
				if ((this.tabComptes[i] != null) && (this.tabComptes[i].getNumero() == unNumero)) {
					return this.tabComptes[i];
				}
			}
			return null;
		}

		/*
		 * (non-Javadoc)
		 *
		 * @see fr.banque.IClient#ajouterCompte(fr.banque.Compte)
		 */
		@Override
		public void ajouterCompte(ICompte unCompte) {
			ICompte[] tmp = this.getComptes();
			boolean ajoute = false;
			for (int i = 0; !ajoute && (i < tmp.length); i++) {
				if (tmp[i] == null) {
					tmp[i] = unCompte;
					ajoute = true;
				}
			}
			if (!ajoute) {
				throw new IllegalArgumentException("Plus de place dans le tableau");
			}
		}

		/**
		 * Formatage du client sous forme de String utilisable directement par
		 * System.out.println(..);. <br/>
		 *
		 * La methode toString() est heritee de la classe java.lang.Object, elle
		 * est tres pratique quand on veut debuguer un programme. Elle est
		 * automatiquement appellee quand on fait de la concatenation entre
		 * chaines de characteres : "a"+12+"b"+monClient. <br/>
		 *
		 * @return une representation chainee de l'objet
		 */
		@Override
		public String toString() {
			// L'utilisation du '+' entre chaine de charactere n'est pas tres
			// optimise
			// Il est prefereble d'utiliser un StringBuffer pour fabriquer une
			// chaine
			// de charactere et eviter ainsi la lourdeur d'execution liee au '+'
			// entre
			// chaine de charactere
			StringBuffer sb = new StringBuffer();
			sb.append(this.getClass().getName());
			sb.append(" Nom: ");
			sb.append(this.getNom());
			sb.append(" Prenom: ");
			sb.append(this.getPrenom());
			sb.append(" Age: ");
			sb.append(this.getAge());
			sb.append("\n");
			ICompte[] tmp = this.getComptes();
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i] != null) {
					sb.append(tmp[i]);
					sb.append("\n");
				}
			}
			return sb.toString();
		}

		/**
		 * Indique si deux clients sont egaux. <br/>
		 *
		 * Deux clients sont egaux si ils ont le meme numero d'identification.
		 *
		 * @param obj
		 *            l'objet qui sera compare a this
		 * @return <code>true</code> si les deux sont egaux et
		 *         <code>false</code> sinon.
		 */
		@Override
		public boolean equals(Object obj) {
			if (obj == null) {
				return false;
			}
			if (obj == this) {
				return true;
			}
			if (obj instanceof Client) {
				IClient c = (IClient) obj;
				return this.getNumero() == c.getNumero();
			}
			return false;
		}
	}
}