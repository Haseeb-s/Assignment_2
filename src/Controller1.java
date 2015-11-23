import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller1 implements Initializable {
    public Controller cont = new Controller();
    public TextField nameField, priceField, descField, quantityField;
    public CheckBox specialChoice;
    public Button addFoodButton;
    public ChoiceBox sizeSelection;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    fillData();
        setSelections();
    }

    public void setSelections() {
        String[] items = {"Large", "Medium", "Small"};
        sizeSelection = new ChoiceBox();
        sizeSelection.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));
    }

    public void fillData(){
        List<String> list = new ArrayList<>();
        list.add("Large");
        list.add("Medium");
        list.add("Small");
        ObservableList<String> oList = FXCollections.observableList(list);
        sizeSelection.setItems(oList);
    }

    public void newWindowGui(){
        try {
            Controller cont = new Controller();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("addFoodFXML.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root1));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void catagoryNew() {
        boolean t = true;
        priceField.setDisable(t);
        descField.setDisable(t);
        quantityField.setDisable(t);
        specialChoice.setDisable(t);
        sizeSelection.setDisable(t);

    }

    public void pushCatagory() {
        boolean f = false;
        FoodItemClass newItem = new FoodItemClass();
        Node newNode = new Node(newItem);
        String catagory = nameField.getText();
        CatagoryNode newCatNode = new CatagoryNode(newNode);
        newCatNode.setCatagory(catagory);
        cont.fcList.insert(newCatNode);
        priceField.setDisable(f);
        descField.setDisable(f);
        quantityField.setDisable(f);
        specialChoice.setDisable(f);
        sizeSelection.setDisable(f);
        cont.stage.close();
    }

    public void AddNew() throws IOException{
        String name = nameField.getText();
        double price = Double.parseDouble(priceField.getText());
        int quantity = Integer.parseInt(quantityField.getText());
        String desc = descField.getText();
        String size = Objects.toString(sizeSelection.getValue());
        boolean specialO = specialChoice.isSelected();
        FoodItemClass newItem = new FoodItemClass(name, price, quantity, desc, size, specialO);
        String catagory = "Bakery";
        Node newNode = new Node(newItem);
        CatagoryNode newCatNode = new CatagoryNode(newNode);
        newCatNode.setCatagory(catagory);
        cont.fcList.insert(newCatNode);
        cont.stage.close();
        Controller cont = new Controller();
        cont.saveData();

        ObservableList update = cont.getFoodClass();
        System.out.println(update.isEmpty());
        System.out.println("should" + update.toString());

        System.out.println("what" + cont.fcList.toString());
    //    cont.table.setItems(update);
    }
}
