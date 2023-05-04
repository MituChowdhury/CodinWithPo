package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
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

public class Game2 implements Initializable {

    @FXML
    private TextArea textArea;

    private Stage stage;
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
    private Stage stage2 = new Stage();

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
	        Scene scene = new Scene(root,900,700);
	        
	        Sprite2 Home = new Sprite2();
	        Home.setImage("pahar1.png");
	        stage2.setScene(scene);
	        stage2.setTitle("Po and Cities"); 
	        stage2.setResizable(false);
	        
	        Canvas canvas = new Canvas(900, 700);
	        root.getChildren().add( canvas );
	        GraphicsContext gc = canvas.getGraphicsContext2D();
	        Image home = new Image("pahar1.png");
	        Home.setImage("pahar3.jpg");

	        ArrayList<Sprite2> homes = new ArrayList<Sprite2>();
	        ArrayList<Sprite2> stayHomes = new ArrayList<>();

	        Sprite2 poThePanda = new Sprite2();

	        poThePanda.setPosition(47, 475);
	        poThePanda.setImage("PO.png");

	        Sprite2 tempHome1 = new Sprite2();
	        tempHome1.setPosition(50, 475);
	        tempHome1.setImage("pahar3.jpg");

	        Sprite2 tempHome2 = new Sprite2();
	        tempHome2.setPosition(100, 175);
	        tempHome2.setImage("pahar3.jpg");

	        Sprite2 tempHome3 = new Sprite2();
	        tempHome3.setPosition(100, 275);
	        tempHome3.setImage("pahar3.jpg");

	        Sprite2 tempHome4 = new Sprite2();
	        tempHome4.setPosition(240, 75);
	        tempHome4.setImage("pahar3.jpg");

	        Sprite2 tempHome5 = new Sprite2();
	        tempHome5.setPosition(435, 75);
	        tempHome5.setImage("pahar3.jpg");

	        Sprite2 tempHome8 = new Sprite2();
	        tempHome8.setPosition(300, 575);
	        tempHome8.setImage("pahar3.jpg");

	        Sprite2 tempHome6 = new Sprite2();
	        tempHome6.setPosition(650, 425);
	        tempHome6.setImage("pahar3.jpg");

	        Sprite2 tempHome7 = new Sprite2();
	        tempHome7.setPosition(1000, 1000);
	        tempHome7.setImage("pahar3.jpg");


	        //adding block :

	        ArrayList<Sprite2> blocks = new ArrayList<Sprite2>();
	        Image shen = new Image("shen.png");
	        Image bolok = new Image("block.png");
	        Image bolok2 = new Image("block2.png");

	        Sprite2 block1 = new Sprite2();
	        block1.setImage("block2.png");
	        block1.setPosition(495, 550);
	        blocks.add(block1);

	        Sprite2 block2 = new Sprite2();
	        block2.setImage("block.png");
	        block2.setPosition(350, 325);
	        blocks.add(block2);

	        for(Sprite2 house : homes) {
	            stayHomes.add(house);
	        }
	        Image grass = new Image("kingdom.png");
	        Image PoPo = new Image("PO.png");
	        

	        //Input handling section 
	        
	        Scanner sc_inp = null, sc_out = null, sc_sol = null;
	        try {
	        	File inpFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src\\input2.txt");
	            sc_inp = new Scanner(inpFile);

	            File outFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src\\output2.txt");
	            sc_out = new Scanner(outFile);

	            File solFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src\\solution2.txt");
	            sc_sol = new Scanner(solFile);
	        } catch (Exception e) {
	            System.out.println("An error occured");
	            e.printStackTrace();
	        }
	 
	        int verdict = 1;

	        /*
	            verdict meaning
	            1 - OK
	            2 - longer than shortest path
	            3 - access non existing path
	            4 - starts on wrong node
	            5 - ends on wrong node
	            0 - input data doesn't match

	         */
	        
	        try {
	            int test_case = sc_inp.nextInt();

	            int[][] graph = new int[205][205];

	            while(test_case > 0) {
	                test_case -= 1;

	                //intializing graph
	                for(int i=0; i<205; i++) {
	                    for(int j=0; j<205; j++) {
	                        graph[i][j] = 0;
	                    }
	                }
	                int n = sc_inp.nextInt();
	                for(int i=1; i<n; i++) {
	                    int a = sc_inp.nextInt();
	                    int b = sc_inp.nextInt();
	                    graph[a][b] = 1;
	                    graph[b][a] = 1;
	                }
	                int correct_sp = sc_sol.nextInt();
	                int sp = sc_out.nextInt();
	                if(sp != correct_sp) {
	                    verdict = 3;
	                    break;
	                }
	                int[] shortest_path = new int[205];
	                boolean chk_ex = true;
	                for(int i=0; i<=sp; i++) {
	                    shortest_path[i] = sc_out.nextInt();
	                    if(shortest_path[i] > n || shortest_path[i] < 1) chk_ex = false;
	                }
	                if(chk_ex == false) {
	                    verdict = 0;
	                    break;
	                }
	                if(shortest_path[0] != 1) {
	                    verdict = 4;
	                    break;
	                }
	                if(shortest_path[sp] != n) {
	                    verdict = 5;
	                    break;
	                }
	                boolean chk = true;
	                for(int i=1; i<=sp; i++) {
	                    if(graph[shortest_path[i]][shortest_path[i-1]] == 0) chk = false;
	                }
	                if(chk == false) {
	                    verdict = 3;
	                    break;
	                }
	            }
	        } catch (Exception e) {
	            verdict = 0;
	        }
	        sc_inp.close();
            sc_out.close();
	       // Generating simulations

	       // verdict = 4;

	        String msg = new String();

	        if(verdict == 1) {
	            homes.add(tempHome1);
	            homes.add(tempHome3);
	            homes.add(tempHome4);
	            homes.add(tempHome5);
	            homes.add(tempHome6);
	            homes.add(tempHome7);
	            msg = "Ye! Success";
	        }

	        if(verdict == 3) {
	            homes.add(tempHome1);
	            homes.add(tempHome3);
	            homes.add(tempHome4);
	            homes.add(tempHome5);
	            homes.add(tempHome8);
	            homes.add(tempHome7);
	            msg = "Opps! You've accessed an wrong path";
	        }

	        if(verdict == 4) {
	            poThePanda.positionX = 100;
	            poThePanda.positionY = 275;
	            homes.add(tempHome3);
	            homes.add(tempHome7);
	            msg = "You started on a wrong city";
	        }

	        if(verdict == 5) {
	            homes.add(tempHome1);
	            homes.add(tempHome3);
	            homes.add(tempHome4);
	            homes.add(tempHome5);
	            msg = "You've finished on wrong city";
	        }

	        Image sos = new Image("soen.png");
	        
	        Timer timer = new Timer();
	        if(verdict == 1) timer.schedule(new Task2(verdict), 6000);
	        else if(verdict == 0) {
	        	timer.schedule(new Task2(verdict), 500);
	        	return;
	        }
	        else timer.schedule(new Task2(verdict), 5500);
	        
	        gc.drawImage(PoPo, poThePanda.positionX, poThePanda.positionY);
	        new AnimationTimer(){
	        	@Override
	            public void handle(long currentNanoTime) { 
	                Iterator<Sprite2> iter = homes.iterator();

	                boolean ok = true;
	                Sprite2 nxt = iter.next();
	                if(iter.hasNext()) {

	                    double nxtX = nxt.positionX+50;
	                    double nxtY = nxt.positionY;
	                    if(poThePanda.positionX > nxtX) ok = false;
	                    else ok = true;
	                    poThePanda.moveNext(nxtX, nxtY, ok);

	                    if(ok == true) {
	                        if (poThePanda.positionX >= nxtX) {
	                            iter.remove();

	                        }
	                    }
	                    else {
	                        if(poThePanda.positionX <= nxtX+10) {
	                            iter.remove();
	                        }
	                    }
	                }

	               // poThePanda.moveNext(targetX, targetY);
	                gc.clearRect(0,0,1000,1000);

	                for(Sprite2 block : blocks) {
	                    block.render(gc);
	                }

	                gc.drawImage(grass, 0, 0);
	                gc.drawImage(sos, 50, 475);
	                gc.drawImage(home, 100, 275);
	                gc.drawImage(home, 240, 75);
	                gc.drawImage(home, 435, 75);
	                gc.drawImage(sos, 650, 425);
	                gc.drawImage(home, 300, 575);
	                gc.drawImage(home, 750, 225);
	                gc.drawImage(bolok2, 495, 550);
	                gc.drawImage(bolok, 450, 325);

	                gc.drawImage(shen, 700, 425);
	                poThePanda.render(gc);

	                boolean thamo = false;

	                for(Sprite2 block : blocks) {
	                    if(poThePanda.intersects(block)) thamo = true;

	                }

	                if(thamo == true) {
	                    stop();
	                }
	            }
	        }.start();

	        

	        poThePanda.render(gc);

	        stage2.showAndWait();
	        
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
    }
    
    private Stage stage1;
	private Scene scene;
	private Parent root;
    public void switchToPage2(ActionEvent event) throws IOException {
		root = FXMLLoader.load(getClass().getResource("/Page2.fxml"));
		stage1 = (Stage)((Node)event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage1.setScene(scene);
		stage1.show();
	}
}
