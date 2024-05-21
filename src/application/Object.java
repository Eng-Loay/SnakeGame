package application;
 abstract public class Object {
	private int X;
	private int Y;
	public Object(int x, int y)
	{
		X=x;
		Y=y;
	}
	public Object()
	{
		this(1, 1);
	}
	public void SetX(int x)
	{
		X=x;
	}
	public void SetY(int y)
	{
		Y=y;
	}
	public int GetX()
	{
		return X;
	}
	public int GetY()
	{
		return Y;
	}
	public  abstract void PrintStatus();
}

