package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Fruit extends Object{
	public Image apple1;
	public Image apple2;
	public ImageView apple;
	public Fruit(AnchorPane root)
	{
		super((int)((Math.random()*19)+1),(int)((Math.random()*19)+1));
		 apple1= new Image("apple.png", 30,30,true,true);
		apple2=new Image("apple2.png",30,30,true,true);
		apple = new ImageView(apple1);
		root.getChildren().add(apple);
	}
	public boolean CheckIfEaten(Object head)//Polymorphed since i only need the X and Y coord of head which is inherited from Object class
	{
		if(GetX()==head.GetX()&&GetY()==head.GetY()) return true;
		else return false;
	}
	public void ChangeFruit(Object[] tail)//Polymorphed since i only need the X and Y coord of tail which is inherited from Object class
	{
		if(SnakeTail.counter%10==0&&SnakeTail.counter!=0)
		{
			apple.setImage(apple2);
		}
		else 
		{
			apple.setImage(apple1);
		}
		int x, y;
		boolean flag=true;
		while(true) 
		{	
			x=(int)((Math.random()*19)+1);
			y=(int)((Math.random()*19)+1);
			flag=true;
			for(int i=0;i<SnakeTail.counter;i++)
			{
				if(x== tail[i].GetX()&&y== tail[i].GetY())
				{                                                                                 
                    flag=false;
					break;
				}
			}
			if(flag) 
			{
				break;
			}
		}
		SetX(x);
		SetY(y);
		
	}
	@Override
	public void PrintStatus()
	{
		System.out.print("Fruit printed");
	}
}
