import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Chronometer;
import model.ChronometerDAO;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Runnable, Initializable {
    private Chronometer c;
    private List<ChronometerDAO> Marcas;
    private Thread t;
    public MainController() {
        c = new Chronometer();
        Marcas = new ArrayList<ChronometerDAO>();
    }
        @FXML
        private Label label;
        @FXML
        private Button startButton,stopButton,resetButton,vueltaButton,saveLapButton,podioButton;
        @FXML
        private ImageView imgPlay,imgStop,imgPause,imgSave,imgPodio,imgVuelta;
        @FXML
        private TableView <Chronometer> Vueltas;
        @FXML
        private TableColumn <Chronometer, LocalDate> numeroVuelta;
        @FXML
        private TableColumn <Chronometer, String> tiempoVuelta;



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
            this.t = new Thread(this);
            t.start();
            this.startButton.setDisable(true);
            this.stopButton.setDisable(false);
            this.resetButton.setDisable(true);
            this.imgPlay.setVisible(false);
            this.imgPause.setVisible(true);
            this.imgPlay.setDisable(true);
            this.imgPause.setDisable(false);
            this.imgStop.setVisible(false);
            this.imgVuelta.setVisible(true);
            this.vueltaButton.setDisable(false);
            this.imgVuelta.setDisable(false);

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
            this.imgVuelta.setVisible(false);
            this.vueltaButton.setDisable(true);
            this.imgVuelta.setDisable(true);
        }

        @Override
        public void run() {
            while(!c.stop){
                try {
                    Thread.sleep(1);
                    c.setMilliseconds(c.getMilliseconds() + 2);
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
                    Platform.runLater(() -> label.setText(String.format("%02d:%02d:%02d:%03d", c.getHours(), c.getMinutes(), c.getSeconds(), c.getMilliseconds())));
                } catch (InterruptedException e) {
                    ChronometerDAO aux = new ChronometerDAO(-1,c.getHours(), c.getMinutes(), c.getSeconds(), c.getMilliseconds(), LocalDate.now());
                    c.setMilliseconds(0);
                    c.setSeconds(0);
                    c.setMinutes(0);
                    c.setHours(0);
                    this.Vueltas.setVisible(true);
                    this.Marcas.add(aux);
                    Marcas.sort(Comparator.comparing(Chronometer::getSeconds));
                    this.CompleteTable();
                    Platform.runLater(() -> {
                        this.label.setText("00:00:00:00");
                    });
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
        this.startButton.setDisable(false);
        this.stopButton.setDisable(true);
        this.resetButton.setDisable(true);
        this.imgVuelta.setVisible(false);
        this.vueltaButton.setDisable(true);
        this.imgVuelta.setDisable(true);

    }
    @FXML
    public void vueltaAction(){
        t.interrupt();
    }
    public void CompleteTable(){
        ObservableList <Chronometer> items = FXCollections.observableArrayList(this.Marcas);
        this.Vueltas.setItems(items);
        tiempoVuelta.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>((cellData.getValue().getTime()));
        });
        numeroVuelta.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getDate());
        });
    }
    public void saveLap(){
            if(!Marcas.isEmpty()){
                Marcas.sort(Comparator.comparing(Chronometer::getSeconds));
                Marcas.get(0).insert();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("No hay vueltas");
                alert.setContentText("No hay vueltas para guardar");
                alert.showAndWait();
            }
    }
    public void showPodio() throws IOException {
        App.loadScene(new Stage(), "Podio","Podio",true,false);
    }
}
