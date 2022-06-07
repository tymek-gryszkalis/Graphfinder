package pl.edu.pw.ee.jimp.Application;

import java.io.FileInputStream;
import java.io.InputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Config.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Graphfinder");
            Image icon = new Image("https://thumbs.dreamstime.com/b/graph-icon-simple-outline-graph-vector-icon-white-background-graph-icon-simple-outline-graph-vector-icon-vector-illustration-130483368.jpg");
            stage.getIcons().add(icon);
            stage.setResizable(false);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}