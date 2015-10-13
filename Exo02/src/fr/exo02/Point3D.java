package fr.exo02;

public class Point3D extends Point2D
{
	private int z;

	public Point3D()
	{
		this(1,1,1);
	}
	public Point3D(int x,int y,int z)
	{
		super(x,y);
		this.setZ(z);
	}
	public int getZ() {
		return this.z;
	}
	public void setZ(int z) {
		this.z = z;
	}
	@Override
	public void afficher() {
		// TODO Auto-generated method stub
		System.out.print("Coordonnée Z :" + this.getZ() + " ");
		super.afficher();

	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		builder.append(super.toString());
		builder.append(this.getClass().getName());
		builder.append("Coodonnée Z :");
		builder.append(this.getZ());
		return builder.toString();
	}
	public void translater(int dX, int dY,int dZ) {
		// TODO Auto-generated method stub
		super.translater(dX, dY);
		this.setZ(this.getZ()+dZ);
	}
}
