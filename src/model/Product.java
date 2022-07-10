package model;
// UML and Javadoc verified, OG
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Product with associated parts
 */
public class Product {

    /**
     *  ID for the product
     */
    private int id;

    /**
     *  Name of the product
     */
    private String name;

    /**
     * Price of the product
     */
    private double price;

    /**
     * Quantity of product available
     */
    private int stock;

    /**
     * Minimum quantity of product
     */
    private int min;

    /**
     * Maximum quantity of product
     */
    private int max;

    /**
     * A list of associated parts for the product
     */
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();

    /**
     * Constructor for a new instance of a product
     *
     * @param id  ID for the product
     * @param name  Name of the product
     * @param price Price of the product
     * @param stock Quantity of product available
     * @param min Minimum quantity of product
     * @param max Maximum quantity of product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;
    }

    /**
     * Getter for the id
     *
     * @return id of the product
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for the id
     *
     * @param id The id of the product
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for the name
     *
     * @return name of the product
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name
     *
     * @param name The name of the product
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for the price
     * @return price of the product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the price
     *
     * @param price Price of the product
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Getter for the stock
     *
     * @return Stock of the product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for the stock
     *
     * @param stock Quantity of product available
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter for the min
     *
     * @return min of the product
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for the min
     *
     * @param min Minimum level of the product
     */
    public void setMin(int min) {
        this.min = min;
    }

    /**
     * Getter for max
     *
     * @return Maximum level of the product
     */
    public int getMax() {
        return max;
    }

    /**
     * Setter for the max
     *
     * @param max Maximum level of the product
     */
    public void setMax(int max) {
        this.max = max;
    }

    /**
     * Adds part to associated parts list for the product.
     *
     * @param part Part to add
     */
    public void  addAssociatedPart(Part part) {
        associatedParts.add(part);
    }

    /**
     * Deletes part from associated parts list for the product.
     *
     * @param selectedAssociatedPart Part to delete
     * @return  Boolean showing result of part deletion
     */
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {
        if (associatedParts.contains(selectedAssociatedPart)) {
            associatedParts.remove(selectedAssociatedPart);
            return true;
        }
        else
            return false;
    }

    /**
     * Gets list of associated parts for the product.
     *
     * @return List of parts
     */
    public ObservableList<Part> getAllAssociatedParts() {return associatedParts;}
}