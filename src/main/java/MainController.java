import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import model.Chronometer;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Runnable, Initializable {
    private Chronometer c;
    public MainController() {
        c = new Chronometer();
    }
        @FXML
        private Label label;

        @FXML
        private Button startButton;

        @FXML
        private Button stopButton;
        @FXML
        private Button resetButton;
        @FXML
        private ImageView imgPlay;
        @FXML
        private ImageView imgStop;
        @FXML
        private ImageView imgPause;


        @FXML
        void exitButtonAction() {
            System.exit(0);
        }

        @FXML
        void resetButtonAction() {
            c.setHours(0);
            c.setMinutes(0);
            c.setSeconds(0);
            c.setMilliseconds(0);
            c.setStop(true);
            this.label.setText("00:00:00:00");
        }

        @FXML
        void startButtonAction() {
            c.setStop(false);
            Thread t = new Thread(this);
            t.start();
            this.startButton.setDisable(true);
            this.stopButton.setDisable(false);
            this.resetButton.setDisable(true);
            this.imgPlay.setVisible(false);
            this.imgPause.setVisible(true);
            this.imgPlay.setDisable(true);
            this.imgPause.setDisable(false);
            this.imgStop.setVisible(false);

        }

        @FXML
        void stopButtonAction() {
            c.setStop(true);
            this.startButton.setDisable(false);
            this.stopButton.setDisable(true);
            this.resetButton.setDisable(false);
            this.imgPlay.setVisible(true);
            this.imgPause.setVisible(false);
            this.imgPlay.setDisable(false);
            this.imgPause.setDisable(true);
            this.imgStop.setVisible(true);
        }

        @Override
        public void run() {
            while(!c.stop){
                try {
                    Thread.sleep(1);
                    c.setMilliseconds(c.getMilliseconds() + 1);
                    if(c.getMilliseconds() == 1000){
                        c.setMilliseconds(0);
                        c.setSeconds(c.getSeconds() + 1);
                    }
                    if(c.getSeconds() == 60){
                        c.setSeconds(0);
                        c.setMinutes(c.getMinutes() + 1);
                    }
                    if(c.getMinutes() == 60){
                        c.setMinutes(0);
                        c.setHours(c.getHours() + 1);
                    }
                    Platform.runLater(() -> label.setText(String.format("%02d:%02d:%02d:%02d", c.getHours(), c.getMinutes(), c.getSeconds(), c.getMilliseconds())));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.imgPlay.setVisible(true);
        this.imgPlay.setDisable(false);
        this.imgPause.setDisable(true);
        this.imgStop.setVisible(false);
        this.imgPause.setVisible(false);
        this.startButton.opacityProperty().set(0);
        this.startButton.setDisable(false);
        this.stopButton.setDisable(true);
        this.resetButton.setDisable(true);

    }
}
