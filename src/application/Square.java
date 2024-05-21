package application;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Square{
	static private int cellX=0;
	static private int cellY=0;
	private int column=1;
	private int row=1;
	private Rectangle square;
	public  Square(AnchorPane root)
	{
		column=cellX;
		row=cellY;
		square= new Rectangle();
		square.setX(1+column*30);
		square.setY(1+row*30);
		square.setHeight(30);
		square.setWidth(30);
		root.getChildren().add(square);
		square.toBack();
		if((cellX+cellY)%2==0)square.setFill(Color.valueOf("#E6E6FA"));
		else square.setFill(Color.valueOf("#CBC3E3"));
		if(cellX==GameBoard.columns-1) {
			cellY++;
			cellX=0;
		}
		else cellX++;
	}
	public void setVisiblity(boolean x)
	{
		if(x) square.setOpacity(1);
		else square.setOpacity(0);
	}
	public void Percede(boolean x)
	{
		if(x) square.toFront();
		else square.toBack();
	}
	public void setSize(int x)
	{
		square.setHeight(x);
		square.setWidth(x);
	}
	public int getSize()
	{
		return (int)square.getHeight();
	}
	public int getRow()
	{
		return row;
	}
	public int getColumn()
	{
		return column;
	}
}
