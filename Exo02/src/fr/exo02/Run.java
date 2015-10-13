package fr.exo02;

public class Run {
	public static void main(String... args) throws CloneNotSupportedException {

		Point2D tab[] = new Point2D[20];

		for(int i = 0;i<tab.length;i++)
		{
			tab[i]=new Point2D();
		}

		Point2D p1 = new Point2D(15,0);
		Point2D p2 = new Point2D();
		Point2D p3 = new Point2D();
		Point2D p4 = new Point2D();
		Point3D p10 = new Point3D();


		System.out.println(p1.toString());
		p2.translater(5, 16);
		p2.toString();
		p2=p3;
		p3.toString();
		p3.translater(16,5);
		p4=p3;
		p4.translater(2, 6);
		Point2D p5 = p4;
		p5.toString();
		p4.toString();

		System.out.println(p1);
		System.out.println(p2);

		p2 = (Point2D)p1.clone();

		System.out.println(p1);
		System.out.println(p2);

		p1.toString();
		p10.translater(10, 5, 66);
		System.out.println(p10.toString());


		for(int i = 0 ;i<tab.length;i += 2)
		{
			tab[i].translater(5, 12);
		}

		for(int i = 0;i<tab.length;i++)
		{
			tab[i].toString();
		}

		int var1 = 5;
		switch (var1){
		case 0:
			System.out.println("zero");
			break;
		case 5:
			System.out.println("Cinq");
			 break;
		case 10:
			System.out.println("dix");
			break;

		default:
			System.out.println("Ne sais pas !");

			p10.toString();
		}
	}
}
