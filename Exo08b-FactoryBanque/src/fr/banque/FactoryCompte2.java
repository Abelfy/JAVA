package fr.banque;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Factory utilisant les generics/template. <br/>
 * Elle ne peut plus être singloton, les template et le static ne faisant pas
 * bon ménage. <br/>
 *
 * Important : cette factory a besoin pour fonctionner que les classes concretes
 * soient accessibles. <br/>
 *
 * @param <T>
 *            l'interface representant le type d'objet fabriqué (héritié de
 *            ICompte)
 * @param <C>
 *            la classe concrete associee au type fabrique (héritié de Compte)
 *
 */
public final class FactoryCompte2<T extends ICompte, C extends Compte> {

	private int numero;
	private Class<C> typeConcret;

	/**
	 * Constructeur.
	 *
	 * @param unTypeConcret
	 *            la classe concrete
	 */
	public FactoryCompte2(Class<C> unTypeConcret) {
		super();
		this.numero = 1;
		this.typeConcret = unTypeConcret;
	}


	/**
	 * Fabrique un objet en fonction des arguments données.
	 *
	 * @param args
	 *            les arguments à destination du constructeur
	 * @return l'objet demandé (un C)
	 * @throws NoSuchMethodException
	 *             si un probleme survient
	 * @throws SecurityException
	 *             si un probleme survient
	 * @throws InstantiationException
	 *             si un probleme survient
	 * @throws IllegalAccessException
	 *             si un probleme survient
	 * @throws IllegalArgumentException
	 *             si un probleme survient
	 * @throws InvocationTargetException
	 *             si un probleme survient
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public T create(Object... args) throws NoSuchMethodException, SecurityException, InstantiationException,
	IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Class[] paramTypes = null;
		Constructor<C> constructor = null;
		Object[] args2 = null;

		if (args.length == 0) {
			paramTypes = new Class[1];
			paramTypes[0] = int.class;
			args2 = new Object[1];
			args2[0] = this.numero;
		} else {
			// Tableau qui contiendra tous les types de parametres
			// +1 car on gere le numero
			paramTypes = new Class[args.length + 1];
			paramTypes[0] = int.class;
			for (int i = 1; i < paramTypes.length; i++) {
				paramTypes[i] = args[i - 1].getClass();
				// Le boxing nous donne un java.lang.Double.class plutot qu'un
				// double.class
				if (paramTypes[i] == Double.class) {
					paramTypes[i] = double.class;
				}
			}
			// +1 car on injecte le numero directement
			args2 = new Object[args.length + 1];
			args2[0] = this.numero;
			for (int i = 0; i < args2.length; i++) {
				if (i > 0) {
					args2[i] = args[i - 1];
				}
			}
		}
		// On cherche un constructeur dans la classe concrete qui répond à notre
		// liste de parametres
		constructor = this.typeConcret.getConstructor(paramTypes);
		T resultat = (T) constructor.newInstance(args2);
		// On a indique que T est obligatoirement un herition de ICompte lors de
		// la declaration de la classe on peut donc utiliser la methode associee
		// a l'interface :
		this.numero++;
		return resultat;
	}

	/**
	 * Exemple d'utilisation. <br/>
	 *
	 * Important : cette factory a besoin pour fonctionner que les classes
	 * concretes soient accessibles. <br/>
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		FactoryCompte2<ICompte, Compte> fCpt = new FactoryCompte2<ICompte, Compte>(Compte.class);
		FactoryCompte2<ICompteASeuilRemunere, CompteASeuilRemunere> fCpt2 = new FactoryCompte2<ICompteASeuilRemunere, CompteASeuilRemunere>(
				CompteASeuilRemunere.class);
		try {
			ICompte c1 = fCpt.create();
			ICompte c2 = fCpt.create(500d);
			ICompte c3 = fCpt2.create(500d, 0.05d, 200d);
			ICompte c4 = fCpt2.create();
			System.out.println(c1);
			System.out.println(c2);
			System.out.println(c3);
			System.out.println(c4);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

