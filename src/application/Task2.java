package application;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.StageStyle;

import java.util.TimerTask;

public class Task2 extends TimerTask {
    int verdict = 1;
    
    Task2(int i) {
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
                    "Ye! Success",
                    " ",
                    "Opps! You've accessed an wrong path",
                    "You started on a wrong city",
                    "You've finished on wrong city"
            };

            Alert alert = new Alert(Alert.AlertType.ERROR);
            if(verdict == 1) alert.setGraphic(ac);
            else alert.setGraphic(wa);
            alert.initStyle(StageStyle.TRANSPARENT);
            if(verdict == 1) alert.setTitle("SUCCESS");
            alert.setHeaderText("Po is happy.");
            if(verdict != 1) alert.setHeaderText("PO is sad.");
            alert.setContentText(msg[verdict]);
            alert.showAndWait().ifPresent((buttonType) -> {
                if (buttonType == ButtonType.OK) {
                    alert.close();
                }
            });
        });
    }
}
