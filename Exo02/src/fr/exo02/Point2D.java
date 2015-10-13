package fr.exo02;


class Point2D implements Cloneable {

	private int x;
	private int y;

	private static int nbInstance;

	public Point2D(){
		this(1,1);

	}

	public Point2D(int X,int Y){
		this.setX(X);
		this.setY(Y);
		Point2D.nbInstance++;
	}

	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}


	public static int getNbInstance() {
		return Point2D.nbInstance;
	}

	public void afficher(){
		System.out.println( "Coordonnée X :" + this.getX() + " Coordonnée Y :" + this.getY());
	}

	public void translater(int dX,int dY){
		this.setX(this.getX()+dX);
		this.setY(this.getY()+dY);
	}
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		Point2D.nbInstance--;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.getClass().getName());
		builder.append("Coordonnée X : ");
		builder.append(this.getX());
		builder.append("Coordonnée Y : ");
		builder.append(this.getY());
		return builder.toString();
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

}
