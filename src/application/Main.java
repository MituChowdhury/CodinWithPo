package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;


public class Main extends Application {
	
	/// merge
	public static void main(String[] args) {
		launch(args);
	}
	@Override
	public void start(Stage stage) throws Exception {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("/Page1.fxml"));
			Scene scene = new Scene(root);
			stage.setTitle("CodinWithPo");
			Image icon = new Image("po.jpg");
			stage.getIcons().add(icon);
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
