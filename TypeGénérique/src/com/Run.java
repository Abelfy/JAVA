package com;

public class Run {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Marchandise m1 = new Marchandise();
		Marchandise m2 = new Marchandise();

		SuperAvion<Marchandise> avion = new SuperAvion();

		avion.charger(0,m1);
		avion.charger(1,m2);

		Marchandise m3 = avion.decharger(1);

	}

}
