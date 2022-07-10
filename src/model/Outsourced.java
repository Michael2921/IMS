package model;
// UML, Javadoc & OG verified

/**
 * Represents an outsourced part.

 */
public class Outsourced extends Part {

    /**
     * Company name for the part
     */
    private String companyName;

    /**
     * Constructor for an instance of an Outsourced part.
     *
     * @param id ID for the part
     * @param name Name of the part
     * @param price Price of the part
     * @param stock Inventory level of the part
     * @param min Minimum level for the part
     * @param max Maximum level for the part
     * @param companyName Company name for the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Getter for the companyName
     *
     * @return
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Setter for the companyName
     *
     * @param companyName
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}