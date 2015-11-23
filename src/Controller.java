import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.*;
import java.util.ResourceBundle;
import java.util.*;
import java.net.URL;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class Controller implements Initializable {
    public List importedList = new ArrayList<>();
    public FoodCategoryLinkedList fcList = new FoodCategoryLinkedList();
    public TextField FoodField;
    @FXML
    public ChoiceBox<String> categorySelector;
    public ChoiceBox selectionbox;
    public TableColumn<FoodItemClass, String> sizeCol, nameCol, quantCol, descCol, specialCol, priceCol;
    public TableView table;
    public TextField foodItem;
    public ArrayList<String> catagoryList = new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        inputExcel();
        inputFile();
        populateLists();
        fillData();
        setCatagory();
    }

    public void setCatagory() {
        categorySelector.setItems(FXCollections.observableArrayList("Bakery", "FastFood"));
        categorySelector.setValue("Bakery");
    }

    public void DeleteFood(String Food) {
        boolean found = false;
        for (Iterator<ArrayList> pi = importedList.iterator(); pi.hasNext(); ) {
            List array = pi.next();
            boolean exit = false;
            for (Iterator<Object> xi = array.iterator(); exit; ) {
                String temp = xi.next().toString();
                temp = xi.next().toString();
                if (temp.equalsIgnoreCase(Food))
                    found = true;
            }
            if (found == true)
                fcList.delete(pi);
        }
    }

    public void EditFood() throws IOException {
        String editFood = foodItem.getText();
        Controller1 cont1 = new Controller1();
        cont1.AddNew();
        DeleteFood(editFood);

    }

    public void DeleteCatagory(String Catagory) {
        boolean found = false;
        for (Iterator<ArrayList> pi = importedList.iterator(); pi.hasNext(); ) {
            List array = pi.next();
            boolean exit = false;
            for (Iterator<Object> xi = array.iterator(); exit; ) {
                String temp = xi.next().toString();
                if (temp.equalsIgnoreCase(Catagory))
                    found = true;
            }
            if (found == true)
                fcList.delete(pi);
        }
    }

    public void addCatagory() {
        newWindowGui();
    }

    public void closeGUI() {
        stage.close();
    }

    public Stage stage = new Stage();

    public void newWindowGui() {
        Parent root;
        try {
            root = (AnchorPane) FXMLLoader.load(getClass().getResource("/fxml/addFoodFXML.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void populateLists() {
        String name = "", desc = "", size = "", catagory = "";
        double price = 0;
        int quantity = 0;
        boolean specialOrder = false;
        for (Iterator<ArrayList> pi = importedList.iterator(); pi.hasNext(); ) {
            List array = pi.next();
            int x = 0;
            for (Iterator<Object> xi = array.iterator(); xi.hasNext(); ) {
                String temp = xi.next().toString();
                switch (x) {
                    case 0:
                        catagory = temp;
                        break;
                    case 1:
                        name = temp;
                        break;
                    case 2:
                        price = Double.parseDouble(temp);
                        break;
                    case 3:
                        quantity = (int) Double.parseDouble(temp);
                        break;
                    case 4:
                        desc = temp;
                        break;
                    case 5:
                        size = temp;
                        break;
                    case 6:
                        specialOrder = Boolean.parseBoolean(temp);
                        break;
                    default:
                        break;
                }
                x++;
            }
            if (!array.isEmpty()) {
                FoodItemClass newItem = new FoodItemClass(name, price, quantity, desc, size, specialOrder);
                Node newNode = new Node(newItem);
                CatagoryNode newCatNode = new CatagoryNode(newNode);
                newCatNode.setCatagory(catagory);
                fcList.insert(newCatNode);
            }
        }
    }

    public void inputExcel() {
        String filename = "Book1.xls";
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            HSSFWorkbook workbook = new HSSFWorkbook(fis);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                HSSFRow row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                List data = new ArrayList<>();
                while (cells.hasNext()) {

                    HSSFCell cell = (HSSFCell) cells.next();
                    data.add(cell);
                }
                importedList.add(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void inputFile() {
        try {
            FileInputStream fstream = new FileInputStream("foodInfo.txt");
            BufferedReader read = new BufferedReader(new InputStreamReader(fstream));
            int fileLength = countLines("foodInfo.txt");
            List array = new ArrayList<>();
            Object temp;
            int j = 0;
            for (int i = 0; i <= fileLength; i++) {
                temp = read.readLine();
                array.add(temp);

                j++;
                if (j == 7) {
                    j = 0;
                    importedList.add(array);
                    array = new ArrayList<>();
                }

            }
        } catch (IOException e) {
            System.out.println("Error Msg: " + e.getMessage());
        }
    }

    public static int countLines(String filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];
            int count = 0;
            int readChars = 0;
            boolean empty = true;
            while ((readChars = is.read(c)) != -1) {
                empty = false;
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
            }
            return (count == 0 && !empty) ? 1 : count;
        } finally {
            is.close();
        }
    }

    public ObservableList<FoodItemClass> getFoodClass() {
        ObservableList<FoodItemClass> dispensers = FXCollections.observableArrayList();
        String name = "", desc = "", size = "", catagory = "";
        double price = 0;
        int quantity = 0;
        boolean specialOrder = false;
        for (Iterator<ArrayList> pi = importedList.iterator(); pi.hasNext(); ) {
            List array = pi.next();
            int x = 0;
            for (Iterator<Object> xi = array.iterator(); xi.hasNext(); ) {
                System.out.print("Yes");
                String temp = xi.next().toString();
                switch (x) {
                    case 0:
                        catagory = temp;
                        break;
                    case 1:
                        name = temp;
                        break;
                    case 2:
                        price = Double.parseDouble(temp);
                        break;
                    case 3:
                        quantity = (int) Double.parseDouble(temp);
                        break;
                    case 4:
                        desc = temp;
                        break;
                    case 5:
                        size = temp;
                        break;
                    case 6:
                        specialOrder = Boolean.parseBoolean(temp);
                        break;
                    default:
                        break;
                }
                x++;
            }
            if (!array.isEmpty()) {
                dispensers.add(new FoodItemClass(name, price, quantity, desc, size, specialOrder));
            }
        }
        return dispensers;
    }

    public void fillData() {

        sizeCol.setCellValueFactory(new PropertyValueFactory<>("size"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        quantCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("desc"));
        specialCol.setCellValueFactory(new PropertyValueFactory<>("specialOrder"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        table.setItems(getFoodClass());
    }

    public void saveData() {
        try {
            File file = new File("CurrentInfo.txt");
            FileOutputStream fop = new FileOutputStream(new File("CurrentInfo.txt"));
            file.createNewFile();

            ObjectOutputStream oos = new ObjectOutputStream(fop);
            for (Iterator<ArrayList> pi = importedList.iterator(); pi.hasNext(); ) {
                List array = pi.next();
                for (Iterator<Object> xi = array.iterator(); xi.hasNext(); ) {
                    oos.writeObject(xi);
                }
            }
            oos.close();
            fop.close();
        } catch (Exception E) {
            System.out.print("NPE");
        }
    }
}


