import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

    /**
     * JavaFX App
     */
    public class App extends Application {

        private static Scene scene;

        @Override
        public void start(Stage stage) throws IOException {
            scene = new Scene(loadFXML("MainController"), 600, 465);
            stage.setScene(scene);
            stage.show();
        }

        static void setRoot(String fxml) throws IOException {
            scene.setRoot(loadFXML(fxml));
        }

        protected static Parent loadFXML(String fxml) throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
            return fxmlLoader.load();
        }
        public static void loadScene(Stage stage, String fxml, String title, boolean SaW, boolean isResizable) throws IOException {
            stage.setScene(new Scene(loadFXML(fxml)));
            stage.setTitle(title);
            stage.setResizable(isResizable);
            //new JMetro(Style.LIGHT).setScene(stage.getScene());
            if (SaW)
                stage.showAndWait();
            else
                stage.show();
        }
        public static void closeScene(Stage stage) {
            stage.close();
        }


        public static void main(String[] args) {
            launch();
        }

    }
