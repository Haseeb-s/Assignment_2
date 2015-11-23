import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
        import javafx.scene.Parent;
        import javafx.scene.Scene;
        import javafx.stage.Stage;

import java.net.URL;

public class FoodDriver extends Application {

    public static void main(String[] args) {launch(args);}

    @Override
    public void start(Stage primaryStage) throws Exception {
        Controller cont = new Controller();
        Parent root = FXMLLoader.load(getClass().getResource("MainInterface.fxml"));
        primaryStage.setTitle("Vending Machine");
        primaryStage.setScene(new Scene(root, 791, 472));

        primaryStage.show();
    }
}