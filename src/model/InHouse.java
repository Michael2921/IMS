package model;
// UML, Javadoc & OG verified
/**
 *  Representation of an inHouse part.
 */
public class InHouse extends Part {

    /**
     * Machine ID for the part
     */
    private int machineId;

    /**
     * Constructor for an instance of an InHouse object.
     *
     * @param id ID for the part
     * @param name Name of the part
     * @param price Price of the part
     * @param stock Inventory level of the part
     * @param min Minimum level for the part
     * @param max Maximum level for the part
     * @param machineId Machine ID for the part
     */
    public InHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);
        this.machineId = machineId;
    }

    /**
     * Getter for the machineId
     *
     * @return machineId of the part
     */
    public int getMachineId() {
        return machineId;
    }

    /**
     * Setter for the machineId
     *
     * @param machineId  MachineId of the part
     */
    public void setMachineId(int machineId) {
        this.machineId = machineId;
    }
}