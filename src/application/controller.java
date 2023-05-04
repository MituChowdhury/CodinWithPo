package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class controller {
	private  Stage stage;
	private  Scene scene;
	private  Parent root;
	
	public void switchToPage1(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/Page1.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	public void switchToPage2(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/Page2.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void playGame1(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/SceneGame1.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void playGame2(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/Game2Scene.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	public void playGame3(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/SceneGame3.fxml"));
		stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
}
