package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import model.Part;
import model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * Controller class that provides logic for the application's add product screen.
 *
 *
 */
public class AddProductController implements Initializable {

    /**
     * A list containing parts associated with the product.
     */
    private ObservableList<Part> assocParts = FXCollections.observableArrayList();

    /**
     * The associated parts table view.
     */
    @FXML
    private TableView<Part> assocPartTableView;

    /**
     * The part ID column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartIdColumn;

    /**
     * The part Name column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, String> assocPartNameColumn;

    /**
     * The inventory level column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Integer> assocPartInventoryColumn;

    /**
     * The price column for the associated parts table.
     */
    @FXML
    private TableColumn<Part, Double> assocPartPriceColumn;

    /**
     * The all parts table view.
     */
    @FXML
    private TableView<Part> partTableView;

    /**
     * The part ID column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partIdColumn;

    /**
     * The name column for the all parts table.
     */
    @FXML
    private TableColumn<Part, String> partNameColumn;

    /**
     * The inventory level column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    /**
     * The price column for the all parts table.
     */
    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    /**
     * The part search text field.
     */
    @FXML
    private TextField partSearchText;

    /**
     * The product ID text field.
     */
    @FXML
    private TextField productIdText;

    /**
     * The product name text field.
     */
    @FXML
    private TextField productNameText;

    /**
     * The product inventory level text field.
     */
    @FXML
    private TextField productInventoryText;

    /**
     * The product price text field.
     */
    @FXML
    private TextField productPriceText;

    /**
     * The product maximum level text field.
     */
    @FXML
    private TextField productMaxText;

    /**
     * The product minimum level text field.
     */
    @FXML
    private TextField productMinText;

    /**
     * Adds part object selected in the all parts table to the associated parts table.
     *
     * Displays error message if no part is selected.
     *
     * @param event Add button action.
     */
    @FXML
    void addButtonAction(ActionEvent event) {

        Part selectedPart = partTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            showAlert(5);
        } else {
            assocParts.add(selectedPart);
            assocPartTableView.setItems(assocParts);
        }
    }

    /**
     * Displays confirmation dialog and loads MainScreenController.
     *
     * @param event Cancel button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void cancelButtonAction(ActionEvent event) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Alert");
        alert.setContentText("Do you want discard changes and go back to the main screen?");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            returnToMainScreen(event);
        }
    }

    /**
     * Begins a search based on value in parts search text field and reloads the parts
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

        for (Part part : allParts) {
            if (String.valueOf(part.getId()).contains(searchString) ||
                    part.getName().contains(searchString)) {
                partsFound.add(part);
            }
        }

        partTableView.setItems(partsFound);

        if (partsFound.size() == 0) {
            showAlert(1);
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

        partIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        partTableView.setItems(Inventory.getAllParts());

        assocPartIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        assocPartNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        assocPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        assocPartPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Refreshes part table view to show all parts when parts search text field is empty.
     *
     * @param event Parts search text field key pressed.
     */
    @FXML
    void partSearchKeyPressed(KeyEvent event) {

        if (partSearchText.getText().isEmpty()) {
            partTableView.setItems(Inventory.getAllParts());
        }
    }

    /**
     * Displays confirmation dialog and removes selected part from associated parts table.
     *
     * Displays error message if no part is selected.
     *
     * @param event Remove button action.
     */
    @FXML
    void removeButtonAction(ActionEvent event) {

        Part selectedPart = assocPartTableView.getSelectionModel().getSelectedItem();

        if (selectedPart == null) {
            showAlert(5);
        } else {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Alert");
            alert.setContentText("Do you want to remove the selected part?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                assocParts.remove(selectedPart);
                assocPartTableView.setItems(assocParts);
            }
        }
    }

    /**
     * Adds new product to inventory and initiates MainScreenController.
     *
     * Text fields are validated with error messages displayed preventing empty and/or
     * invalid values.
     *
     * @param event Save button action.
     * @throws IOException From FXMLLoader.
     */
    @FXML
    void saveButtonAction(ActionEvent event) throws IOException {

        try {
            int id = 0;
            String name = productNameText.getText();
            double price = Double.parseDouble(productPriceText.getText());
            int stock = Integer.parseInt(productInventoryText.getText());
            int min = Integer.parseInt(productMinText.getText());
            int max = Integer.parseInt(productMaxText.getText());

            if (name.isEmpty()) {
                showAlert(7);
            } else {
                if (minValid(min, max) && inventoryValid(min, max, stock)) {

                    Product newProduct = new Product(id, name, price, stock, min, max);

                    for (Part part : assocParts) {
                        newProduct.addAssociatedPart(part);
                    }

                    newProduct.setId(Inventory.getNewProductId());
                    Inventory.addProduct(newProduct);
                    returnToMainScreen(event);
                }
            }
        } catch (Exception e){
            showAlert(1);
        }
    }

    /**
     * Loads MainScreenController.
     *
     * @param event Passed from parent method.
     * @throws IOException From FXMLLoader.
     */
    private void returnToMainScreen(ActionEvent event) throws IOException {

        Parent parent = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("../view/MainScreen.fxml")));
        Scene addProdscene = new Scene(parent);
        Stage addProdstage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        addProdstage.setScene(addProdscene);
        addProdstage.show();
    }

    /**
     * Validates that min is greater than 0 and less than max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @return Boolean indicating if min is valid.
     */
    private boolean minValid(int min, int max) {

        boolean isValid = true;

        if (min <= 0 || min >= max) {
            isValid = false;
            showAlert(3);
        }

        return isValid;
    }

    /**
     * Validates that inventory level is equal too or between min and max.
     *
     * @param min The minimum value for the part.
     * @param max The maximum value for the part.
     * @param stock The inventory level for the part.
     * @return Boolean indicating if inventory is valid.
     */
    private boolean inventoryValid(int min, int max, int stock) {

        boolean isValid = true;

        if (stock < min || stock > max) {
            isValid = false;
            showAlert(4);
        }

        return isValid;
    }

    /**
     * Displays alert messages.
     *
     * @param alertType Alert message selector.
     */
    private void showAlert(int alertType) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);

        switch (alertType) {
            case 1:
                alert.setTitle("Error");
                alert.setHeaderText("Error Adding Product");
                alert.setContentText("Form contains empty fields or incorrect values.");
                alert.showAndWait();
                break;
            case 2:
                alertInfo.setTitle("Information");
                alertInfo.setHeaderText("Part not found");
                alertInfo.showAndWait();
                break;
            case 3:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Min");
                alert.setContentText("Min must be a number greater than 0 and less than Max.");
                alert.showAndWait();
                break;
            case 4:
                alert.setTitle("Error");
                alert.setHeaderText("Invalid value for Inventory");
                alert.setContentText("Inventory must be a number equal to or between Min and Max");
                alert.showAndWait();
                break;
            case 5:
                alert.setTitle("Error");
                alert.setHeaderText("Part not selected");
                alert.showAndWait();
                break;
            case 7:
                alert.setTitle("Error");
                alert.setHeaderText("Name Empty");
                alert.setContentText("Name cannot be empty.");
                alert.showAndWait();
                break;
        }
    }


}