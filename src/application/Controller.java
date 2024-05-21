package application;
import application.SnakeHead.direction;
import javafx.event.ActionEvent;

public class Controller {
	public void up(ActionEvent e)
	{
		if(!(SnakeHead.dir == direction.down))
		SnakeHead.dir= direction.up;
	}
	public void down(ActionEvent e)
	{ 
		if(!(SnakeHead.dir == direction.up))
		SnakeHead.dir= direction.down;
	}
	public void right(ActionEvent e)
	{
		if(!(SnakeHead.dir == direction.left))
		SnakeHead.dir=direction.right;
	}
	public void left(ActionEvent e)
	{
		if(!(SnakeHead.dir == direction.right))
		SnakeHead.dir=direction.left;
	}
	public void pause(ActionEvent e)
	{	
		if(GameBoard.IsPaused) GameBoard.IsPaused=false;
		else GameBoard.IsPaused =true;
	}
}
