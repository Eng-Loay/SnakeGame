package application;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
public class SnakeHead extends Object {
	private int beforeX;
	private int beforeY;	
	public Circle shape;
	public enum direction
	{
		up,
		down,
		left,
		right
	}
	public static direction dir;
	public SnakeHead(AnchorPane root)
	{
		super(1,1);
		Lighting light= new Lighting();
		shape= new Circle();
		shape.setFill(Color.valueOf("#0FFF50"));
		shape.setRadius(15);
		shape.setEffect(light);
		root.getChildren().add(shape);
	}
	public int GetbX()
	{
		return beforeX;
	}
	public void SetbX(int x)
	{
		beforeX=x;
	}
	public int GetbY()
	{
		return beforeY;
	}
	public void SetbY(int y)
	{
		beforeY=y;
	}
	public void Logic()
	{
		beforeX= GetX();
		beforeY= GetY();
		if(dir == direction.up) SetY(GetY()-1);
		if(dir ==direction.down) SetY(GetY()+1);
		if(dir ==direction.right) SetX(GetX()+1);
		if(dir ==direction.left) SetX(GetX()-1);
	}
	public boolean CheckIfOut()
	{
		if(GetX()>20 || GetX()<0 || GetY()>20||GetY()<0) return true;
		return false;
	}
	public boolean CheckIfCollapsed(Object[] tail) //Polymorphed since i only need the X and Y coord of tail which is inherited from Object class
	{
		for(int i=0;i<SnakeTail.counter;i++)
		{
			if(GetX()==tail[i].GetX()&&GetY()==tail[i].GetY()) return true;
		}
		return false;
	}
	@Override
	public void PrintStatus()
	{
		System.out.print("SnakeHead printed");
	}
}
	
