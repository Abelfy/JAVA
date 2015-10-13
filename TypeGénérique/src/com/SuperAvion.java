package com;


public class SuperAvion<T>{

	private T[] soute;

	/**
	 * Constructeur sans paramètre. <br/>
	 * Par défaut, 25 cases dans la soute.
	 */
	SuperAvion(){
		this(25);
	}
	SuperAvion(int taille){
		this.soute = (T[]) new Object[taille];
	}
	public void charger(int place, T unElement){
		this.soute[place]=unElement;
	}

	public T decharger(int place){
		return this.soute[place];
	}
}
