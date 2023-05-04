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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Game3 implements Initializable {

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
	        stage2.setTitle("Po and Spells");
	        stage2.setScene(scene);

	        Canvas canvas = new Canvas(1000, 1000);
	        root.getChildren().add(canvas);
	        GraphicsContext GC = canvas.getGraphicsContext2D();

	    //  creating boxes

	        ArrayList<Sprite3> boxes = new ArrayList<Sprite3>();
	        ArrayList<Sprite3> perm = new ArrayList<Sprite3>();

	        Image img = new Image("scroll.png");
	        Image bat = new Image("battleground.jpg");

	        Sprite3 box1 = new Sprite3();
	        box1.setPosition(35, 480);
	        box1.dam = 9;
	        box1.lav = 12;
	        box1.setImage("scroll.png");
	        perm.add(box1);

	        Sprite3 box2 = new Sprite3();
	        box2.setPosition(175, 480);
	        box2.dam = 11;
	        box2.lav = 15;
	        box2.setImage(img);
	        perm.add(box2);

	        Sprite3 box3 = new Sprite3();
	        box3.setPosition(325, 480);
	        box3.dam = 20;
	        box3.lav = 30;
	        box3.setImage(img);
	        perm.add(box3);

	        Sprite3 box4 = new Sprite3();
	        box4.setPosition(475, 480);
	        box4.dam = 40;
	        box4.lav = 50;
	        box4.setImage(img);
	        perm.add(box4);

	        Sprite3 box5 = new Sprite3();
	        box5.setPosition(625, 480);
	        box5.dam = 39;
	        box5.lav = 99;
	        box5.setImage(img);
	        perm.add(box5);

	        Sprite3 box6 = new Sprite3();
	        box6.setPosition(775, 480);
	        box6.dam = 4;
	        box6.lav = 1;
	        box6.setImage(img);
	        perm.add(box6);

	    //  Box ends here


	    //  Creatig moving po:

	        Sprite3 PoGo = new Sprite3();
	        PoGo.setPosition(0, 300);
	        PoGo.setImage("BatPo.png");
	        PoGo.dam = 59;

	    // Creating Battle po

	        Sprite3 PoBat = new Sprite3();
	        PoBat.setPosition(0, 420);
	        PoBat.setImage("BatPo.png");
	        PoBat.setSpeed(3);


	    // Creating Battle shen

	        Sprite3 ShenBat = new Sprite3();
	        ShenBat.setPosition(800, 420);
	        ShenBat.setImage("BatShen.png");
	        ShenBat.setSpeed(-3);

	    //  Creating Bomb

	        Sprite3 bomb = new Sprite3();
	        bomb.setPosition(300, 200);




	        //      Input handling:
	        int verdict = 1;
	        try {
	            File inputFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src\\input3.txt");
	            Scanner sc_input = new Scanner(inputFile);

	            File outputFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src\\output3.txt");
	            Scanner sc_output = new Scanner(outputFile);

	            File solutionFile = new File("C:\\Users\\ASUS\\eclipse-workspace\\merge\\src\\solution3.txt");
	            Scanner sc_solput = new Scanner(solutionFile);

	            int test_num = sc_input.nextInt();
	            for(int tc = 1; tc <= test_num; tc++) {
	                int N = sc_input.nextInt();
	                int taka = sc_input.nextInt();
	                int[] val = new int[205];
	                int[] cost = new int[205];

	                for(int i=1; i<=N; i++) {
	                    cost[i] = sc_input.nextInt();
	                }
	                for(int i=1; i<=N; i++) {
	                    val[i] = sc_input.nextInt();
	                }

	                int ans = sc_solput.nextInt();
	                int p_ans = sc_output.nextInt();
	                int cur = 0;

	                int[] path = new int[205];
	                int n = sc_output.nextInt();
	                for(int i=1; i<=n; i++) {
	                    path[i] = sc_output.nextInt();

	                    if(path[i] < 1 || path[i] > N) {
	                        verdict = 0;
	                        break;
	                    }
	                    taka -= cost[path[i]];
	                    cur += val[path[i]];
	                    if(i == 1) continue;
	                    if(path[i] < path[i-1]) {
	                        verdict = 0;
	                        break;
	                    }
	                }

	                if(taka < 0) {
	                    verdict = 3;
	                    break;
	                }
	                if(cur != ans || p_ans != ans) {
	                    verdict = 2;
	                    break;
	                }


	                int[] poOutput = new int[205];
	                int[] mark = new int[205];
	                for(int i=1; i<=N; i++) {
	                    mark[i] = 0;
	                }

	            }

	        } catch(Exception e) {
	            verdict = 0;
	            System.out.println("An error happened.");
	            e.printStackTrace();
	        }


	    /*  Creating Dataset
	        1 - AC
	        2 - WA - kom nise
	        3 - WA - beshi khoroch korse
	        4 - exception
	    */

	        //verdict = 0;

	        PoBat.verdict = verdict;

	        if(verdict == 1) {
	            boxes.add(box1);
	            boxes.add(box2);
	            boxes.add(box5);
	        } else if(verdict == 2) {
	            boxes.add(box1);
	            boxes.add(box4);
	            boxes.add(box6);
	        } else if(verdict == 3) {
	            boxes.add(box1);
	            boxes.add(box2);
	            boxes.add(box4);
	        }

	        Font theFont = Font.font( "Helvetica", FontWeight.BOLD, 20 );
	        GC.setFont( theFont );
	        GC.setFill( Color.GREEN );
	        GC.setStroke( Color.BLACK.brighter() );
	        GC.setLineWidth(1);

	        Timer timer = new Timer();
	        if(verdict == 0) {
	            timer.schedule(new Task3(verdict), 500);
	            return;
	        }
	        else if(verdict == 1) timer.schedule(new Task3(verdict), 10000);
	        else if(verdict == 2) timer.schedule(new Task3(verdict), 10000);
	        else timer.schedule(new Task3(verdict), 6000);

	        new AnimationTimer(){
	            public void handle(long currentNanoTime) {
	                //Iterator<Sprite3> iter = boxes.iterator();
	                boolean thamo= false;



	                //Iterator<Sprite3> iter = boxes.iterator();
	                GC.clearRect(0, 0, 1000, 1000);


	                PoGo.moveNext();
	                boolean sesh = true;
	                if(PoGo.getX() > 900) sesh = false;

	                if(sesh == false) {
	                    GC.drawImage(bat, 0, 0);
	                    GC.drawImage(PoBat.image, PoBat.getX(), PoBat.getY());
	                    GC.drawImage(ShenBat.image, ShenBat.getX(), ShenBat.getY());
	                    PoBat.moveNext();
	                    ShenBat.moveNext();

	                    if(PoBat.speed == 0)
	                        stop();

	                    if(PoBat.intersects(ShenBat)) {
	                        PoBat.setSpeed(0);
	                        ShenBat.setSpeed(0);
	                        if(PoBat.verdict == 1) {
	                            ShenBat.setImage("UltaShen.png");
	                        } else {
	                            PoBat.setImage("UltaPO.png");
	                        }
	                    }

	                } else {

	                    GC.drawImage(bat, 0, 0);
	                    for (Sprite3 baksho : perm) {
	                        baksho.render(GC, true);
	                    }

	                    boolean ba = false;
	                    // System.out.println(PoGo.getX());
	                    //System.out.println(boxes.size());
	                    for (Sprite3 sp : boxes) {
	                        //System.out.println(sp.getX() + " " + PoGo.getX());
	                        if (PoGo.movx == true && PoGo.chk(sp.getX(), sp.getY()) == true) ba = true;
	                    }
	                    if (ba == true) {
	                        PoGo.movd = true;
	                        PoGo.movx = false;
	                    }
	                    boolean bb = false;
	                    for (Sprite3 sp : boxes) {
	                        if (PoGo.movd == true && PoGo.intersects(sp)) bb = true;
	                    }
	                    if (bb == true) {
	                        PoGo.movu = true;
	                        PoGo.movd = false;
	                    }

	                    boolean bc = false;
	                    int i = 0;
	                    for (Sprite3 sp : boxes) {
	                        if (PoGo.movu == true && PoGo.getY() <= 300) bc = true;
	                        if (PoGo.getX() >= sp.getX()-PoGo.ex) i += 1;
	                    }
	                    if (bc == true) {
	                        PoGo.movx = true;
	                        PoGo.movu = false;
	                        Sprite3 temp = boxes.get(i - 1);
	                        PoGo.dam -= temp.dam;
	                        PoGo.lav += temp.lav;
	                    }
	                    if (PoGo.dam < 0) {
	                        stop();
	                    }
	                    PoGo.render(GC, false);
	                }


	            }
	        }.start();
	        
	        

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