import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Chronometer;
import model.ChronometerDAO;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class PodioController implements Initializable {

    private List<ChronometerDAO> listaPodio;
    private ChronometerDAO c;
    public PodioController() {
        c = new ChronometerDAO();
        this.listaPodio = c.getAll();
    }
    @FXML
    private TableView Podio;
    @FXML
    private TableColumn<Chronometer, LocalDate> Fecha;
    @FXML
    private TableColumn <Chronometer, String> Tiempo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Chronometer> items = FXCollections.observableArrayList(this.listaPodio);
        this.Podio.setItems(items);
        Tiempo.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>((cellData.getValue().getTimeString()));
        });
        Fecha.setCellValueFactory(cellData -> {
            return new SimpleObjectProperty<>(cellData.getValue().getDate());
        });
    }
}
