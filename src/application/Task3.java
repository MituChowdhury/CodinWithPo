package application;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.util.TimerTask;

public class Task3 extends TimerTask {
    int verdict = 1;
    Task3(int i) {
        verdict = i;
    }
    @Override
    public void run() {
        Platform.runLater(() -> {
            Image alertAC = new Image("Accepted.png");
            ImageView ac = new ImageView(alertAC);
            Image alertWA = new Image("Wa.png");
            ImageView wa = new ImageView(alertWA);

            String[] msg = new String[]{
                    "Your output is invalid",
                    "Ye! Po defeats Lord shen",
                    "126 level damage was required to defeat Lord Shen, Po did 63",
                    "Po's Strength became less than 0"
            };

            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(verdict == 1) alert.setGraphic(ac);
            else alert.setGraphic(wa);
            alert.setTitle("ERROR");
            alert.initStyle(StageStyle.TRANSPARENT);
            if(verdict == 1) alert.setTitle("SUCCESS");
            alert.setHeaderText("Po is happy.");
            if(verdict != 1) alert.setHeaderText("PO is sad.");
            //String out = output.toString();
            alert.setContentText(msg[verdict]);
            alert.showAndWait().ifPresent((buttonType) -> {
                if (buttonType == ButtonType.OK) {
                    alert.close();
                }
            });
        });
    }
}