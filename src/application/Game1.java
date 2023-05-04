package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Game1 implements Initializable {

    @FXML
    private TextArea textArea;

    private Stage stage, stage1 = new Stage(), stage2 = new Stage();
	private Scene scene;
	private Parent root;
    private final FileChooser fileChooser = new FileChooser();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text", "*.txt"),new FileChooser.ExtensionFilter("All Files", "*.*"));
    }

    public void init(Stage myStage) {
        this.stage = myStage;
    }

    @FXML
    public void save() throws Exception {
        try {
            fileChooser.setTitle("Save As");
            File file = fileChooser.showSaveDialog(stage);

            if (file != null) {
                PrintWriter savedText = new PrintWriter(file);
                BufferedWriter out = new BufferedWriter(savedText);
                out.write(textArea.getText());
                out.close();
                compile();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: " + e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    private void compile() throws Exception {
    	try {
    		Process process = Runtime.getRuntime().exec("cmd /c g++ code.cpp", null, new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src"));
			
			int exitVal = process.waitFor();
			
			if (exitVal == 0) {
				System.out.println("Success");
				Process process1 = Runtime.getRuntime().exec("cmd /c a", null, new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src"));
				simulation(stage2);
				System.exit(0);
			} 
			else {
				Image image = new Image("poError.png");
				ImageView imageView = new ImageView(image);
				Alert alert = new Alert(AlertType.ERROR);
				alert.initStyle(StageStyle.TRANSPARENT);
				alert.setGraphic(imageView);
				alert.setHeaderText("COMPILATION ERROR!");
				alert.setHeight(100);
				alert.setWidth(50);
				alert.showAndWait().ifPresent((buttonType) -> {
				    if (buttonType == ButtonType.OK) {
				    	alert.close();
				    }
				});
			}
			
    	} catch (IOException e) {
    		e.printStackTrace();
    	} catch (InterruptedException e) {
    		e.printStackTrace();
    	}
    }
    
    public void simulation(Stage stage2) throws Exception {
		try {
			Group root = new Group();
	        Scene scene = new Scene(root, 900, 700);
	        stage2.setTitle("Po and Boat");
	        stage2.setScene(scene);

	        Canvas canvas = new Canvas(1000, 1000);
	        root.getChildren().add(canvas);
	        GraphicsContext GC = canvas.getGraphicsContext2D();

            // Creating Boats :

	        Image nodi = new Image("nodi.jpg");

	        ArrayList<Sprite1> boats = new ArrayList<>();

	        Sprite1 boat1 = new Sprite1();
	        boat1.setPosition(0, 525);
	        boat1.setSpeed(12);
	        boat1.setImage("boat.png");
	        boat1.setStart(2);

	        Sprite1 boat2 = new Sprite1();
	        boat2.setPosition(0, 525);
	        boat2.setSpeed(11);
	        boat2.setImage("boat.png");
	        boat2.setStart(32);

	        Sprite1 boat3 = new Sprite1();
	        boat3.setPosition(0, 525);
	        boat3.setSpeed(9);
	        boat3.setImage("boat.png");
	        boat3.setStart(62);

	        Sprite1 boat4 = new Sprite1();
	        boat4.setPosition(0, 525);
	        boat4.setSpeed(8);
	        boat4.setImage("boat.png");
	        boat4.setStart(92);

	        Sprite1 boat5 = new Sprite1();
	        boat5.setPosition(0, 525);
	        boat5.setSpeed(7);
	        boat5.setImage("boat.png");
	        boat5.setStart(122);

	        Sprite1 boat6 = new Sprite1();
	        boat6.setPosition(0, 525);
	        boat6.setSpeed(6);
	        boat6.setImage("boat.png");
	        boat6.setStart(152);

	        Sprite1 boat7 = new Sprite1();
	        boat7.setPosition(0, 525);
	        boat7.setSpeed(5);
	        boat7.setImage("boat.png");
	        boat7.setStart(160);

	        Sprite1 boat8 = new Sprite1();
	        boat8.setPosition(0, 525);
	        boat8.setSpeed(10);
	        boat8.setImage("boat.png");
	        boat8.setStart(180);

	        Sprite1 blk = boat8;

	        // Input handling:
	        Scanner sc_input = null, sc_output = null;
	        int verdict = 1;
	        try {
	            File inputFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src\\input1.txt");
	            sc_input = new Scanner(inputFile);

	            File outputFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src\\output1.txt");
	            sc_output = new Scanner(outputFile);


	            int test_num = sc_input.nextInt();
	            for(int tc = 1; tc <= test_num; tc++) {
	                int N = sc_input.nextInt();
	                int[] velocity = new int[205];
	                for(int i=1; i<=N; i++) {
	                    velocity[i] = sc_input.nextInt();
	                }

	                int[] poOutput = new int[205];
	                int[] mark = new int[205];
	                for(int i=1; i<=N; i++) {
	                    mark[i] = 0;
	                }

	                for(int i=1; i<=N; i++) {
	                    poOutput[i] = sc_output.nextInt();
	                    if(poOutput[i] < 1 || poOutput[i] > N) {
	                        verdict = 0;
	                        break;
	                    }
	                    if(mark[poOutput[i]] == 1) {
	                        verdict = 2;
	                        break;
	                    }
	                    mark[poOutput[i]] = 1;
	                    if(i == 1)
	                        {continue;}
	                    if(velocity[poOutput[i]] > velocity[poOutput[i-1]]) {
	                        verdict = 2;
	                        break;
	                    }
	                }
	                if(verdict != 1) 
	                	break;
	            }

	        } catch(Exception e) {
	        	
	            verdict = 0;
	            System.out.println("An error happened.");
	            e.printStackTrace();
	        }
	        
	        sc_input.close();
            sc_output.close();

	/*      simulation dataset :
	        0 - exception
	        1 - ac
	        2 - wa
	        3 - same number twice
	*/
	        //verdict = 3;
	        boats.add(boat1);
	        boats.add(boat2);
	        boats.add(boat3);
	        boats.add(boat4);
	        boats.add(boat5);
	        if(verdict != 3) boats.add(boat6);
	        if(verdict == 2) boats.add(boat8);
	        if(verdict != 3) boats.add(boat7);

	        new AnimationTimer(){
	            public void handle(long currentNanoTime) {
	                boolean thamo= false;
	                GC.clearRect(0, 0, 1000, 1000);
	                GC.drawImage(nodi, 0, 0);

	                int cnt = 0;
	                for(Sprite1 boat : boats) {
	                    boat.moveNext();
	                    boat.render(GC);

	                    if(boat != blk) {
	                        if(boat.intersects(blk) && blk.getX() > 0) {
	                            thamo = true;
	                        }
	                    }

	                    if(boat.getX() < 1000) cnt += 1;

	                }
	                if(thamo == true || cnt == 0) {
	                    stop();
	                }
	            }
	        }.start();

	        Timer timer = new Timer();
	        if(verdict != 1) timer.schedule(new Task1(verdict), 5000);
	        else timer.schedule(new Task1(verdict), 10000);
	        stage2.setResizable(false);
	        stage2.showAndWait();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    public void switchToPage2(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/Page2.fxml"));
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
	}
}
