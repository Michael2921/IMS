package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Inventory;
import model.Outsourced;
import model.Product;

import java.util.Objects;

//Each class has its own javadoc. You can create a javadoc from IDEA or from CMD
/**
 * JavaDoc files are located in src/JavaDoc
 *
 *
 *
 *
 */
public class Main extends Application {

    /**
     * The start method creates the FXML stage and loads the initial scene.
     * RUNTIME ERROR Did not add Inventory.addProduct(ProductName) resulting in product not being shown on the productTableview, included the pieced of code, and the products showed up
     * FUTURE ENHANCEMENT It would extend the functionality of the table, if we could search by other means apart from ID and Name. i.e price
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/MainScreen.fxml")));
        stage.setTitle("Inventory Management System");
        stage.setScene(new Scene(root));
        stage.show();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        //Add sample parts
        int partId = Inventory.getNewPartId() + 1;
        InHouse brakes = new InHouse(partId,"Brakes", 15.00, 10, 1, 20,
                102);
        partId = Inventory.getNewPartId() + 1;
        InHouse wheels = new InHouse(partId,"Wheels", 11.00, 16, 1, 20,
                103);
        partId = Inventory.getNewPartId() + 1;
        InHouse seats = new InHouse(partId,"Seats", 15.00, 10, 1, 20,
                104);
        partId = Inventory.getNewPartId() + 1;
        Outsourced rims = new Outsourced(partId, "Rims",56.99, 15, 1,
                20, "RimsRUs");
        Inventory.addPart(brakes);
        Inventory.addPart(wheels);
        Inventory.addPart(seats);
        Inventory.addPart(rims);

        //Add sample products
        int productId = Inventory.getNewProductId() + 1000;
        Product giantBicycle = new Product(productId, "Giant Bicycle", 299.99, 5, 1,
                15);
        giantBicycle.addAssociatedPart(brakes);
        giantBicycle.addAssociatedPart(wheels);
        giantBicycle.addAssociatedPart(seats);
        giantBicycle.addAssociatedPart(rims);
        Inventory.addProduct(giantBicycle);

        // No outsourced products
        productId = Inventory.getNewProductId() + 1000;
        Product tricycle = new Product(productId, "Exoticycle", 999.99, 3, 1, 8);
        tricycle.addAssociatedPart(wheels);
        tricycle.addAssociatedPart(seats);
        tricycle.addAssociatedPart(rims);
        Inventory.addProduct(tricycle);

        launch(args);
    }
}