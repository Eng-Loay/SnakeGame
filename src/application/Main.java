package application;
	
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader= new FXMLLoader(getClass().getResource("main.fxml"));
			AnchorPane root = loader.load();
			Scene scene = new Scene(root);
			scene.getStylesheets().addAll(this.getClass().getResource("application.css").toExternalForm());
			Image icon= new Image("snake.png");
			primaryStage.getIcons().add(icon);
			Controller controller= loader.getController();
			scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
				@Override
				public void handle(KeyEvent event) {
					switch(event.getCode())
					{
					case W:
						controller.up(null);
						break;
					case S:
						controller.down(null);
						break;
					case A:
						controller.left(null);
						break;
					case D:
						controller.right(null);
						break;
					case ESCAPE:
						controller.pause(null);
					}
				}
			});	
			Player player= new Player("ENG-Loay",root);
			SnakeHead head= new SnakeHead(root);
			Fruit fruit= new Fruit(root);
			SnakeTail[] tail= new SnakeTail[50];
			GameBoard board= new GameBoard();
			player.LoadPlayer();
			board.setup(head,fruit,root,player);
			
			primaryStage.setResizable(false);
			primaryStage.setTitle("The Snake");
			primaryStage.setScene(scene);
			primaryStage.show();
			
			int x=100;
			Timeline timeline= new Timeline(new KeyFrame(Duration.millis(50),e->run(head,fruit,root,board,tail,player)));
			timeline.setCycleCount(Animation.INDEFINITE);
			timeline.setDelay(Duration.millis(x-=70));
			timeline.play();	
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	static void run(SnakeHead head, Fruit fruit, AnchorPane root,GameBoard board, SnakeTail[] tail,Player player)
	{
		if(GameBoard.IsPaused || GameBoard.IsOver)
		{
			if(GameBoard.IsOver && GameBoard.IsPaused) board.RestartGame(head,tail,fruit,root,player);
			else return;
		}
		else
		{	
			head.Logic();
			if(head.CheckIfOut()) board.GameOver(head,tail,fruit,player);
			if(fruit.CheckIfEaten(head))
			{
				if(fruit.apple.getImage()==fruit.apple2) {
					player.SetScore(player.GetScore()+50);
					for(int i=0;i<3;i++)
					{
						tail[SnakeTail.counter] = new SnakeTail(root);
						SnakeTail.counter++;
						SnakeTail.UpdateTail(head, tail, fruit);
					}
				}else {
					tail[SnakeTail.counter] = new SnakeTail(root);
					player.SetScore(player.GetScore()+5);
					SnakeTail.counter++;	
				}
				fruit.ChangeFruit(tail);	
			}
			if(SnakeTail.counter>0)
			{
				if(head.CheckIfCollapsed(tail)) board.GameOver(head,tail,fruit,player);
				else SnakeTail.UpdateTail(head, tail,fruit);
			}
			board.editsetup(head, tail, fruit,root, player);
			player.UpdateHS(root);
		}
}
	public static void main(String[] args) {
		launch(args);
	}
}
