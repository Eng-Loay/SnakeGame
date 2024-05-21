package application;

import java.io.IOException;

import application.SnakeHead.direction;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class GameBoard {
	static public final  int rows=20;
	static public final int columns=20;
	static public boolean IsPaused=false;
	static public boolean IsOver=false;      
	static private  Text myname;
	static private Text myscore;
	static private Text Pause;
	private int X;
	private int Y;
	private Square cells[];
	public GameBoard()
	{
		X=1;
		Y=1;
	}
	public void setup(SnakeHead head, Fruit fruit, AnchorPane root, Player player)
	{
		cells= new Square[rows*columns];
		for(int i=0;i<rows*columns;i++) cells[i]= new Square(root);
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(head.GetX()==j && head.GetY() == i)
				{
					head.shape.setCenterX(X+15);
					head.shape.setCenterY(Y+15);
					head.shape.toFront();
				}
				else if(fruit.GetX()==j && fruit.GetY()==i)
				{
					fruit.apple.setX(X);
					fruit.apple.setY(Y);
					fruit.apple.toFront();
				}
				X+=30;
			}
			X=1;
			Y+=30;
		}
		myname= new Text();
		myscore= new Text();  
		Pause= new Text();
		root.getChildren().add(Pause);
		root.getChildren().add(myscore);
		root.getChildren().add(myname);
		
		myname.setText("Player: "+player.GetName());
		myname.setFont(Font.font("Impact",24));
		if(player.CheckLongName()) myname.setX(460-(player.GetName().length()-5)*14);
		else myname.setX(460);
		myname.setY(630);
		myname.setFill(Color.valueOf("#E0FFFF"));
		myscore.setText("Score: "+player.GetScore());
		myscore.setFont(Font.font("Impact",24));
		myscore.setX(0);
		myscore.setY(630);
		myscore.setFill(Color.valueOf("#E0FFFF"));
		Pause.setText("l l");
		Pause.setFill(Color.BLACK);
		Pause.setFont(Font.font("Impact",FontWeight.BOLD, 20));
		Pause.setX(8);
		Pause.setY(23);
		Pause.toBack();
		cells[0].Percede(false);
	}
	public void editsetup(SnakeHead head,SnakeTail[] tail,Fruit fruit,AnchorPane root,   Player player)
	{
		X=1;
		Y=1;
		for(int i=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++)
			{
				if(head.GetX()==j && head.GetY() == i)
				{	
					head.shape.setCenterX(X+15);
					head.shape.setCenterY(Y+15);
				}
				else if(fruit.GetX()==j && fruit.GetY()==i) 
				{
					fruit.apple.setX(X);
					fruit.apple.setY(Y);
					fruit.apple.toFront();
				}
				else
				{
					for(int m=0;m<SnakeTail.counter;m++)
					{
						if(tail[m].GetX() ==j&&tail[m].GetY()==i)
						{
							tail[m].shape.setCenterX(X+15);
							tail[m].shape.setCenterY(Y+15);
						}
					}
				}
				X+=30;
			}
			X=1;
			Y+=30;
		}
		myscore.setText("Score:"+player.GetScore());
	}
	public void GameOver(SnakeHead head, SnakeTail tail[],Fruit fruit,Player player)
	{
		fruit.apple.setOpacity(0);
		head.shape.setOpacity(0);
		IsOver =true;
		Timeline timeline;
		for(int i=0;i<SnakeTail.counter;i++) tail[i].shape.setOpacity(0);
		timeline = new Timeline(new KeyFrame(Duration.millis(100),e->AnimateOut()));
		timeline.setCycleCount(9);
		timeline.play();
		//Editing Pause text --> Restart
		Pause.setFill(Color.valueOf("#E0FFFF"));
		Pause.setText("⭯");
		Pause.setFont(Font.font("Impact",FontWeight.EXTRA_BOLD, 25));
		Pause.setX(5);	
		//Saving the players highscore
		try {
			player.SavePlayer();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	public void RestartGame(SnakeHead head, SnakeTail[] tail,Fruit fruit,AnchorPane root,Player player)
	{
		Timeline timeline;
		IsOver=false;
		//Bringing back the cells
		timeline = new Timeline(new KeyFrame(Duration.millis(100),e->AnimateBack()));
		timeline.setCycleCount(9);
		timeline.play();
		//Adjusting the head as default
		head.SetX(1);
		head.SetY(1);
		SnakeHead.dir= direction.down;
		//Adjusting fruit as default
		fruit.ChangeFruit(tail);
		//Resetting the tail
		for(int i=0;i<SnakeTail.counter;i++)
		{
			root.getChildren().remove(tail[i].shape);
			tail[i].shape =null;
			tail[i]=null;
		}
		SnakeTail.counter=0;
		
		//Adjusting Player
		player.SetScore(0);
		player.UpdateHS(root);
		IsPaused=false;
		
		editsetup(head,tail,fruit,root,player);
		fruit.apple.setOpacity(1);
		head.shape.setOpacity(1);	
		Pause.setFill(Color.BLACK);
		Pause.setText("l l");
		Pause.setFont(Font.font("Impact",FontWeight.BOLD, 20));
		Pause.setX(8);
	}
	public void AnimateOut()
	{
		for(int i=0,m=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++,m++)
			{
				if(((i==6||i==10) && (j==0||j==1||j==2||j==3))||j==0&&i==7||(i==8 && (j==0||j==2||j==3))||(i==9 && (j==0||j==3))) continue; //creates the letter G
				if(((i==10||i==9)&&(j==5||j==7))||((i==8||i==7)&&(j==5||j==7))||((i==8)&&(j==6))||(i==6)&&(j==6)) continue;//creates letter A
				if(((i==10||i==6||i==8) && (j==17||j==18||j==19))||(i==9||i==7)&&(j==17))continue;//creates letter  E
				if(((i==9||i==10)&&(j==9||j==15))||((i==8||i==7)&&(j==9||j==15))||(i==6&&(j==10||j==14))||(i==7&&(j==11||j==13))||(i==8&&(j==12)))continue;//create letter M
				cells[m].setSize(cells[m].getSize()-5);
			}
		}
	}
	public void AnimateBack()
	{
		for(int i=0,m=0;i<rows;i++)
		{
			for(int j=0;j<columns;j++,m++)
			{
				if(((i==6||i==10) && (j==0||j==1||j==2||j==3))||j==0&&i==7||(i==8 && (j==0||j==2||j==3))||(i==9 && (j==0||j==3))) continue; //creates the letter G
				if(((i==10||i==9)&&(j==5||j==7))||((i==8||i==7)&&(j==5||j==7))||((i==8)&&(j==6))||(i==6)&&(j==6)) continue;//creates letter A
				if(((i==10||i==6||i==8) && (j==17||j==18||j==19))||(i==9||i==7)&&(j==17))continue;//creates letter  E
				if(((i==9||i==10)&&(j==9||j==15))||((i==8||i==7)&&(j==9||j==15))||(i==6&&(j==10/*||j==9||j==15*/||j==14))||(i==7&&(j==11||j==13))||(i==8&&(j==12)))continue;//create letter M
				cells[m].setSize(cells[m].getSize()+5);
			}
		}
	}
}
