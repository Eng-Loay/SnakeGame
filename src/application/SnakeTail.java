package application;
import javafx.scene.effect.Lighting;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class SnakeTail extends Object{
	public static int counter=0;
	public Circle shape;
	public SnakeTail(AnchorPane root)
	{
		super(1,1);
		Lighting light= new Lighting();       
		shape= new Circle();
		shape.setFill(Color.valueOf("#98FB98"));
		shape.setRadius(15);
		shape.setEffect(light);
		root.getChildren().add(shape);
	}
	public static void UpdateTail(SnakeHead head, Object[] tail,Fruit fruit) //Polymorphed since i only need the X and Y coord of tail which is inherited from Object class
	{
		int prex,prey,pre2x,pre2y;
		prex= tail[0].GetX();
		prey=tail[0].GetY();
		if(tail[0].GetX()==fruit.GetX()&&tail[0].GetY()==fruit.GetY()) fruit.ChangeFruit(tail);
		tail[0].SetX(head.GetbX());
		tail[0].SetY(head.GetbY());
		for(int i=1;i<counter;i++)
		{
			if(tail[i].GetX()==fruit.GetX()&&tail[i].GetY()==fruit.GetY()) fruit.ChangeFruit(tail);
			pre2x=tail[i].GetX();
			pre2y=tail[i].GetY();
			tail[i].SetX(prex);
			tail[i].SetY(prey);
			prex= pre2x;
			prey=pre2y;
		}
	}
	@Override
	public void PrintStatus()
	{
		System.out.print("SnakeTailprinted");
	}
}
