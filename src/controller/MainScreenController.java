package controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TreeItemPropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that provides the logic for the application's main screen.
 *
 *
 *
 */
public class MainScreenController implements Initializable {

    /**
     * The part object selected in the table view by the user.
     */
    private static Part partToModify;

    /**
     * The product object selected in the table view by the user.
     */
    private static Product productToModify;

    /**
     * The text field for the parts search.
     */
    @FXML
    private TextField partSearchText;

    /**
     * Table view for the parts.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * The ID column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * The part name column for the parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * The inventory column for the parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    /**
     * The price column for parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * Table text field for the product search.
     */
    @FXML
    private TextField productSearchText;

    /**
     * Table view for the products.
     */
    @FXML
    private TableView<Product> productTableView;

    /**
     * The ID column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productIdColumn;

    /**
     * The name column for the product table.
     */
    @FXML
    private TableColumn<Product, String> productNameColumn;

    /**
     * The inventory column for the product table.
     */
    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    /**
     * The price column for the product table.
     */
    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    /**
     *  RUNTIME ERROR occurs if a part is not chosen when a user clicks the modify button.
     * This happens due to a null value being passed to the initialize method of the
     * ModifyPartController. One way to prevent this error from occurring
     * is to prevent null from being passed to the partModifyAction() method
     *
     * Gets the part object selected by the user in the part table.
     *
     * @return A part object, null if no part selected.
     */
    public static Part getPartToModify() {
        return partToModify;
    }

    /**
     * Gets the product object selected by the user in the product table.
     *
     * @return A product object, null if no product selected.
     */
    public static Product getProductToModify() {
        return productToModify;
    }

    /**
     * Exits the program.
     *
     * @param event Exit button action.
     */
    @FXML
    void exitButtonAction(ActionEvent event) {

        System.exit(0);
    }

    /**
     * Loads the AddPartController.
     *
     * @param event Add button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void partAddAction(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/AddPartScreen.fxml")));
        Scene mainscreenscene = new Scene(parent);
        Stage mainscreenstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainscreenstage.setScene(mainscreenscene);
        mainscreenstage.show();
    }

    /**
     * Deletes the part selected by the user in the part table.
     *
     * The method displays an error message if no part is selected and a confirmation
     * dialog before deleting the selected part.
     *
     * @param event Part delete button action.
     */
    @FXML
    void partDeleteAction(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            displayAlert(3);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(selectedPart);
            }
        }
    }

    /**
     * Loads the ModifyPartController.
     *
     * The method displays an error message if no part is selected.
     *
     * @param event Part modify button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void partModifyAction(ActionEvent event) throws IOException {

        partToModify = partTableView.getSelectionModel().getSelectedItem();

        if (partToModify == null) {
            displayAlert(3);
        } else {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/ModifyPartScreen.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * FUTURE ENHANCEMENT It would extend the functionality of the table, if we could search by other means apart from ID and Name. i.e price
     * Initiates a search based on value in parts search text field and refreshes the parts
     * table view with search results.
     *
     * Parts can be searched for by ID or name.
     *
     * @param event Part search button action.
     */
    @FXML
    void partSearchBtnAction(ActionEvent event) {

        ObservableList<Part> allParts = Inventory.getAllParts();
        ObservableList<Part> partsFound = FXCollections.observableArrayList();
        String searchString = partSearchText.getText();

        for (Part parts : allParts) {
            if (String.valueOf(parts.getId()).contains(searchString) ||
                    parts.getName().contains(searchString)) {
                partsFound.add(parts);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            displayAlert(1);
        }
    }

    /**
     * Refreshes part table view to show all parts when parts search text field is empty.
     *
     * @param event Parts search text field key pressed.
     */
    @FXML
    void partSearchTextKeyPressed(KeyEvent event) {

        if (partSearchText.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }

    }

    /**
     * Loads AddProductController.
     *
     * @param event Add product button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void productAddAction(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/AddProductScreen.fxml")));
        Scene scene = new Scene(parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Deletes the product selected by the user in the product table.
     *
     * The method displays an error if no product is chosen and a confirmation
     * dialog before deleting the chosen product. Stops user from deleting
     * a product with one or more associated parts.
     *
     * @param event Product delete button action.
     */
    @FXML
    void productDeleteAction(ActionEvent event) {

        Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();

        if (selectedProduct == null) {
            displayAlert(4);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to delete the selected product?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {

                ObservableList<Part> associatedParts = selectedProduct.getAllAssociatedParts();

                if (associatedParts.size() >= 1) {
                    displayAlert(5);
                } else {
                    Inventory.deleteProduct(selectedProduct);
                }
            }
        }
    }

    /**
     * Loads ModifyProductController.
     *
     * The method displays an error message if no product is chosen.
     *
     * @param event Product modify button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void productModifyAction(ActionEvent event) throws IOException {

        productToModify = productTableView.getSelectionModel().getSelectedItem();

        if (productToModify == null) {
            displayAlert(4);
        } else {
            Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/ModifyProductScreen.fxml")));
            Scene scene = new Scene(parent);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        }
    }

    /**
     * Begins a search based on the values in products search text field and reloads the products
     * table view with search results.
     *
     * @param event Part search button action.
     */
    @FXML
    void productSearchBtnAction(ActionEvent event) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();
        ObservableList<Product> productsFound = FXCollections.observableArrayList();
        String searchString = productSearchText.getText();

        for (Product product : allProducts) {
            if (String.valueOf(product.getId()).contains(searchString) ||
                    product.getName().contains(searchString)) {
                productsFound.add(product);
            }
        }

        productTableView.setItems(productsFound);

        if (productsFound.size() == 0) {
            displayAlert(2);
        }
    }

    /**
     * Refreshes product table view to show all products when products search text field is empty.
     *
     * @param event Products search text field key pressed.
     */
    @FXML
    void productSearchTextKeyPressed(KeyEvent event) {

        if (productSearchText.getText().isEmpty()) {
            productTableView.setItems(Inventory.getAllProducts());
        }
    }

    /**
     * Displays alert messages.
     *
     * @param alertType Alert message selector.
     */
    private void displayAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        Alert alertError = new Alert(Alert.AlertType.ERROR);

        switch (alertType) {
            case 1:
                alert.setTitle("Information");
                alert.setHeaderText("Part not found");
                alert.showAndWait();
                break;
            case 2:
                alert.setTitle("Information");
                alert.setHeaderText("Product not found");
                alert.showAndWait();
                break;
            case 3:
                alertError.setTitle("Error");
                alertError.setHeaderText("Part not selected");
                alertError.showAndWait();
                break;
            case 4:
                alertError.setTitle("Error");
                alertError.setHeaderText("Product not selected");
                alertError.showAndWait();
                break;
            case 5:
                alertError.setTitle("Error");
                alertError.setHeaderText("Parts Associated");
                alertError.setContentText("All parts must be removed from product before deletion.");
                alertError.showAndWait();
                break;
        }
    }

    /**
     * Starts controller and populates table views.
     *
     * @param location The location used to resolve relative paths for the root object
     * @param resources The resources used to localize the root object
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Populate parts table view
        partTableView.setItems(Inventory.getAllParts());
        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        //Populate products table view
        productTableView.setItems(Inventory.getAllProducts());
        productIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
}