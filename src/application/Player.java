package application;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Player {
	private String name;
	private int score;
	private static int highscore=5;
	private static Text hs;
	private File file;
	private String path;
	public boolean CheckLongName()
	{
		if((name.length()-5)>0) return true;
		else return false;
	}
	public Player(String n,AnchorPane root)
	{
		name=n;
		score=0;
		hs= new Text("YOOO HIGH SCORE!");
		hs.setX(150);
		hs.setY(630);
		hs.setFont(Font.font("Impact",30));
		hs.setFill(Color.valueOf("#E0FFFF"));
		path = "D:/SnakeGameSaved/"+name+".txt";
		file= new File(path);
		try {
			if(!file.exists())
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		root.getChildren().add(hs);
	}
	public void SetName(String s)
	{
		name = s;
	}
	public String GetName()
	{
		return name;
	}
	public void SetScore(int x)
	{
		score =x;
	}
	public int GetScore()
	{
		return score;
	}
	public void UpdateHS(AnchorPane root)
	{
		if(highscore<=score)
		{
			highscore =score;
			hs.setOpacity(1);
		}
		else hs.setOpacity(0);
	}
	public void SavePlayer() throws IOException
	{
		String high= "HighScore:"+highscore;
		FileWriter filew= new FileWriter(path);
		filew.write(high);
		filew.close();
	}
	public void LoadPlayer() throws IOException
	{
		Scanner filer= new Scanner(file);
		String content= ""; 
		if(filer.hasNextLine())content=filer.nextLine();
		String number="";
		boolean reached=false;
		for(int i=0;i<content.length();i++)
		{
			if(content.charAt(i)==':')
			{
				reached=true;
				continue;
			}
			if(reached == true) number+=content.charAt(i);
		}
		filer.close();
		if(reached==true)highscore= Integer.parseInt(number);
	}
}
